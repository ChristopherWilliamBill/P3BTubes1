package com.pppb.p3btubes1;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentPresenter {
    private ArrayList<Movies> movie;
    private DatabasePresenter db;
    public Imovies ui;

    public ListFragmentPresenter(DatabasePresenter db, Imovies ui){
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
