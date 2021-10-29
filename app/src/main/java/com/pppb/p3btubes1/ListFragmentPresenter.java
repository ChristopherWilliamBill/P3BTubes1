package com.pppb.p3btubes1;

import android.util.Log;

import java.util.ArrayList;

public class ListFragmentPresenter {
    private ArrayList<Movies> movie;
    private DatabaseMovie db;
    public Imovies ui;

    public ListFragmentPresenter(DatabaseMovie db, Imovies ui) {
        this.db = db;
        this.ui = ui;
    }

    public void displayListMovie(boolean ascending, boolean rating) {
        this.movie = new ArrayList<>();
        ArrayList<Movies> moviesArrayList = db.loadMovie(ascending, rating);
        this.movie.addAll(moviesArrayList);
        this.ui.updateMovie(this.movie);
        Log.d("test", movie.get(0).getTitle());
    }

    public interface Imovies {
        void updateMovie(ArrayList<Movies> movie);

        void detailMovie();

        void addMovie();
    }
}
