package com.pppb.p3btubes1;

import java.util.ArrayList;

public class ListFragmentPresenter {
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
