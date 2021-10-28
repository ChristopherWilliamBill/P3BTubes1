package com.pppb.p3btubes1;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

public class FragmentCreate {

    private FragmentManager fragmentManager;

    public FragmentCreate(FragmentManager fm){
        fragmentManager = fm;
    }

    public void createMainFragment(){
        Bundle result = new Bundle();
        result.putInt("page", 1);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public void createListFragment(){
        Bundle result = new Bundle();
        result.putInt("page", 2);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public void createAddFragment(){
        Bundle result = new Bundle();
        result.putInt("page", 3);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public void createDetailFragment(Movies movie, int i){
        Bundle result = new Bundle();
        result.putString("title" , movie.getTitle());
        result.putString("synopsis" , movie.getSynopsis());
        result.putInt("rating" , movie.getRating());
        result.putInt("index" , i);
        result.putString("status" , movie.getStatus());
        result.putParcelable("poster", movie.getPoster());

        result.putInt("page", 4);
        fragmentManager.setFragmentResult("movieDetail", result);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public void createListFragmentSeries() {
        Bundle result = new Bundle();
        result.putInt("page", 5);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public void createDetailFragmentSeries(Series series, int i){
        Bundle result = new Bundle();
        result.putString("title" , series.getTitle());
        result.putString("synopsis" , series.getSynopsis());
        result.putInt("rating" , series.getRating());
        result.putString("status" , series.getStatus());
        result.putInt("episode", series.getEpisode());
        result.putInt("index" , i);


        result.putInt("page", 6);
        fragmentManager.setFragmentResult("seriesDetail", result);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public void createAddSeriesFragment(){
        Bundle result = new Bundle();
        result.putInt("page", 7);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public interface IMainActivity{
        void changePage(int page);
    }
}
