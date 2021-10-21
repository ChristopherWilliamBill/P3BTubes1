package com.pppb.p3btubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.DetailFragmentBinding;

import java.util.ArrayList;

public class DetailFragment extends Fragment{
    private DetailFragmentBinding binding;
    private StoragePresenter storagePresenter;
    private ArrayList<Movies> moviesArrayList;
    private int index;

    public DetailFragment(int index){
        this.storagePresenter = new StoragePresenter();
        this.moviesArrayList = new ArrayList<Movies>();
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = DetailFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.moviesArrayList = storagePresenter.loadData(getContext());

        this.binding.tvAddMovie.setText(this.moviesArrayList.get(index).getTitle());

        return view;
    }



}
