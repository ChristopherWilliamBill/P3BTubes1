package com.pppb.p3btubes1;

import java.util.ArrayList;

public class ListFragmentSeriesPresenter {
    private ArrayList<Series> series;
    private DatabaseSeries db;
    public Iseries ui;

    public ListFragmentSeriesPresenter(DatabaseSeries db, Iseries ui) {
        this.series = new ArrayList<>();
        this.db = db;
        this.ui = ui;
    }

    public void displayListSeries() {
        ArrayList<Series> seriesArrayList = db.loadSeries();
        this.series.addAll(seriesArrayList);
        this.ui.updateSeries(this.series);
    }

    public interface Iseries {
        void updateSeries(ArrayList<Series> series);

        void detailSeries();

        void addSeries();
    }
}
