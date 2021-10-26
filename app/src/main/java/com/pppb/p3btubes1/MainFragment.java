package com.pppb.p3btubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.MainFragmentBinding;

import java.util.ArrayList;

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

    public static class ListFragmentPresenter {
        private ArrayList<Movies> movie;
        private DatabaseMovie db;
        public Imovies ui;

        public ListFragmentPresenter(DatabaseMovie db, Imovies ui){
            this.movie = new ArrayList<>();
            this.db = db;
            this.ui = ui;
        }

        public void displayListMovie(){
            ArrayList<Movies> moviesArrayList = db.loadMovie();
            this.movie.addAll(moviesArrayList);
            this.ui.updateMovie(this.movie);
        }

        public interface Imovies{
            void updateMovie(ArrayList<Movies> movie);
            void detailMovie();
            void addMovie();
        }
    }

    public static class ListFragmentSeriesPresenter {
        private ArrayList<Series> series;
        private DatabaseSeries db;
        public Iseries ui;

        public ListFragmentSeriesPresenter(DatabaseSeries db, Iseries ui){
            this.series = new ArrayList<>();
            this.db = db;
            this.ui = ui;
        }

        public void displayListSeries(){
            ArrayList<Series> seriesArrayList = db.loadSeries();
            this.series.addAll(seriesArrayList);
            this.ui.updateSeries(this.series);
        }

        public interface Iseries{
            void updateSeries(ArrayList<Series> series);
            void detailSeries();
            void addSeries();
        }
    }
}
