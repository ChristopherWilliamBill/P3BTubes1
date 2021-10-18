package com.pppb.p3btubes1;

import java.util.ArrayList;

public class ListPresenter {
    protected ArrayList<Movies > movies;

    public ListPresenter(){
        this.loadData();
    }

    public void loadData(){
        this.movies = new ArrayList<Movies>();
        movies.add(new Movies("Free Guy","ini sinopsis", 3));
    }

    public void deleteList(int position){
        this.movies.remove(position);
    }
}
