package com.pppb.p3btubes1.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.MainFragmentBinding;

public class MainFragment extends Fragment implements View.OnClickListener{
    private MainFragmentBinding binding;
    private FragmentCreate presenter;

    public MainFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = MainFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        presenter = new FragmentCreate(this.getParentFragmentManager());
        binding.startBtn.setOnClickListener(this);
        binding.startBtnSeries.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.startBtn){
            presenter.createListFragment();
        }else if(view == this.binding.startBtnSeries){
            presenter.createListFragmentSeries();
        }
    }

}
