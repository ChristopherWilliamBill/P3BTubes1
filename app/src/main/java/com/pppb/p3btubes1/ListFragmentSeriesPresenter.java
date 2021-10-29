package com.pppb.p3btubes1;

import java.util.ArrayList;

public class ListFragmentSeriesPresenter {
    private ArrayList<Series> series;
    private DatabaseSeries db;
    public Iseries ui;

    public ListFragmentSeriesPresenter(DatabaseSeries db, Iseries ui) {
        this.db = db;
        this.ui = ui;
    }

    public void displayListSeries(boolean ascending, boolean rating, boolean isSearch, String search) {
        this.series = new ArrayList<>();
        ArrayList<Series> seriesArrayList = db.loadSeries(ascending, rating, isSearch, search);
        this.series.addAll(seriesArrayList);
        this.ui.updateSeries(this.series);
    }

    public interface Iseries {
        void updateSeries(ArrayList<Series> series);
    }
}
