package com.pppb.p3btubes1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pppb.p3btubes1.databinding.ActivityMainBinding;
import com.pppb.p3btubes1.databinding.MainFragmentBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentPresenter.IMainActivity{

    private ActivityMainBinding binding;
    private MainFragment mainFragment;
    private ListFragment listFragment;
    private FragmentPresenter presenter;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.listFragment = new ListFragment();
        this.mainFragment = new MainFragment();

        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(this.binding.fragmentContainer.getId(), this.mainFragment).addToBackStack(null);
        ft.commit();

        this.getSupportFragmentManager().setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int page = result.getInt("page");
                changePage(page);
            }
        });
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page == 2){
            if(this.listFragment.isAdded()){
                ft.show(this.listFragment);
            }else{
                ft.add(this.binding.fragmentContainer.getId(), this.listFragment);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
        }
        ft.commit();
    }
}