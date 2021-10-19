package com.pppb.p3btubes1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.pppb.p3btubes1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentPresenter.IMainActivity{

    private ActivityMainBinding binding;
    private MainFragment mainFragment;
    private ListFragment listFragment;
    private AddFragment addFragment;
    private FragmentPresenter presenter;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.setSupportActionBar(this.binding.toolbar);
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this,this.binding.drawerLayout,this.binding.toolbar,R.string.openDrawer,R.string.closeDrawer);
        abdt.syncState();

        this.listFragment = new ListFragment();
        this.mainFragment = new MainFragment();
        this.addFragment = new AddFragment();

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
        if(page == 1){
            if(this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }else{
                ft.add(this.binding.fragmentContainer.getId(), this.mainFragment);
            }
            if(this.listFragment.isAdded()){
                ft.hide(this.listFragment);
            }
            if(this.addFragment.isAdded()){
                ft.hide(this.addFragment);
            }
        }else if(page == 2){
            if(this.listFragment.isAdded()){
                ft.show(this.listFragment);
            }else{
                ft.add(this.binding.fragmentContainer.getId(), this.listFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
            if(this.addFragment.isAdded()){
                ft.hide(this.addFragment);
            }
        }else if(page == 3){
            if(this.addFragment.isAdded()){
                ft.show(this.addFragment);
            }else{
                ft.add(this.binding.fragmentContainer.getId(), this.addFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
            if(this.listFragment.isAdded()){
                ft.hide(this.listFragment);
            }
        }
        this.binding.drawerLayout.closeDrawers();
        ft.commit();
    }
}