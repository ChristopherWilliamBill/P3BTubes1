package com.pppb.p3btubes1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.AddFragmentBinding;

import java.util.ArrayList;

public class AddFragment extends Fragment implements View.OnClickListener{
    private AddFragmentBinding binding;
    private FragmentPresenter presenter;
    private DatabasePresenter databasePresenter;
    private MoviesAdapter moviesAdapter;
    private StoragePresenter storagePresenter;
    private ArrayList<Movies> arrayList;
    private MainActivity mainActivity;

    public AddFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = AddFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mainActivity = new MainActivity();
        presenter = new FragmentPresenter(this.getParentFragmentManager());
        this.binding.rbMovie.setOnClickListener(this);
        this.binding.rbSeries.setOnClickListener(this);
        this.binding.addBtnMovie.setOnClickListener(this);
        this.storagePresenter = new StoragePresenter();
        this.arrayList = storagePresenter.loadData(getContext());
        //moviesAdapter = new MoviesAdapter(getActivity(),getContext(), getParentFragmentManager());
        this.databasePresenter = new DatabasePresenter(getContext());
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.rbMovie){
            this.binding.jumlahEpisodeSeries.setVisibility(View.GONE);
        }else if(view == this.binding.rbSeries){
            this.binding.jumlahEpisodeSeries.setVisibility(View.VISIBLE);
        }else if(view == this.binding.addBtnMovie){
            Movies movie = new Movies(this.binding.etTitle.getText().toString(), this.binding.etSynopsis.getText().toString(), Integer.parseInt(this.binding.etRating.getText().toString()));
            databasePresenter.addMovie(movie.getTitle(), movie.getSynopsis(), movie.getRating());
            presenter.createListFragment();
        }
    }
}
