package com.pppb.p3btubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.AddFragmentBinding;

public class AddFragment extends Fragment implements View.OnClickListener{
    private AddFragmentBinding binding;
    private FragmentPresenter presenter;

    public AddFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = AddFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        presenter = new FragmentPresenter(this.getParentFragmentManager());
        return view;
    }


    @Override
    public void onClick(View view) {

    }
}
