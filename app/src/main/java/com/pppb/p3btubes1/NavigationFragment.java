package com.pppb.p3btubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.ListFragmentBinding;
import com.pppb.p3btubes1.databinding.NavigationFragmentBinding;

public class NavigationFragment extends Fragment implements View.OnClickListener{

    private NavigationFragmentBinding binding;
    private FragmentPresenter presenter;

    public NavigationFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = NavigationFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        presenter = new FragmentPresenter(this.getParentFragmentManager());
        binding.btnHome.setOnClickListener(this);
        binding.btnList.setOnClickListener(this);
        binding.btnExit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == binding.btnHome){
            presenter.createMainFragment();
        }else if(view == binding.btnList){
            presenter.createListFragment();
        }
    }
}
