package com.pppb.p3btubes1.Presenter;

import com.pppb.p3btubes1.DatabaseMovie;
import com.pppb.p3btubes1.Model.Movies;

import java.util.ArrayList;

public class ListFragmentPresenter {
    private ArrayList<Movies> movie;
    private DatabaseMovie db;
    public Imovies ui;

    public ListFragmentPresenter(DatabaseMovie db, Imovies ui) {
        this.db = db;
        this.ui = ui;
    }

    public void displayListMovie(boolean ascending, boolean rating, boolean isSearch, String search) {
        this.movie = new ArrayList<>();
        ArrayList<Movies> moviesArrayList = db.loadMovie(ascending, rating, isSearch, search);
        this.movie.addAll(moviesArrayList);
        this.ui.updateMovie(this.movie);
    }

    public interface Imovies {
        void updateMovie(ArrayList<Movies> movie);
    }
}
