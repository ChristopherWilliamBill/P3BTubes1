package com.pppb.p3btubes1.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.NavigationFragmentBinding;

public class NavigationFragment extends Fragment implements View.OnClickListener{

    private NavigationFragmentBinding binding;
    private FragmentCreate presenter;

    public NavigationFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = NavigationFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        presenter = new FragmentCreate(this.getParentFragmentManager());
        binding.btnHome.setOnClickListener(this);
        binding.btnListMovie.setOnClickListener(this);
        binding.btnListSeries.setOnClickListener(this);
        binding.btnExit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == binding.btnHome){
            presenter.createMainFragment();
        }else if(view == binding.btnListMovie){
            presenter.createListFragment();
        }else if(view == binding.btnListSeries){
            presenter.createListFragmentSeries();
        }else if(view == binding.btnExit){
            getActivity().moveTaskToBack(true);
            getActivity().finish();
        }
    }
}
