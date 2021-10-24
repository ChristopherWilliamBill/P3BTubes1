package com.pppb.p3btubes1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.DetailFragmentBinding;

import java.util.ArrayList;

public class DetailFragment extends Fragment{
    private DetailFragmentBinding binding;
    //private StoragePresenter storagePresenter;
    private ArrayList<Movies> moviesArrayList;
    private int index;
    private Movies currentMovie;
    private DatabasePresenter databasePresenter;

    public DetailFragment(Movies currentMovie){
        this.currentMovie = currentMovie;
        //this.storagePresenter = new StoragePresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = DetailFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.binding.tvAddMovie.setText(this.currentMovie.getTitle());
        this.binding.status.setText("Status: " + this.currentMovie.getStatus());


        return view;
    }



}
