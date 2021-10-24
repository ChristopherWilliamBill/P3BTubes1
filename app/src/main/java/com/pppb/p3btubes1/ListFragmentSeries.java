package com.pppb.p3btubes1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.ListFragmentSeriesBinding;

import java.util.ArrayList;

public class ListFragmentSeries extends Fragment implements View.OnClickListener , ListFragmentSeriesPresenter.Iseries{

    private ListFragmentSeriesBinding binding;
    private SeriesAdapter adapter;
    private Context context;
    private DatabaseSeries db;
    private ListFragmentSeriesPresenter SeriesPresenter;


    public ListFragmentSeries(){

    }

    public static ListFragmentSeries newInstance(){
        ListFragmentSeries fragment = new ListFragmentSeries();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = ListFragmentSeriesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ListView listView = this.binding.lstSeries;
        this.SeriesPresenter = new ListFragmentSeriesPresenter(this.db, this);
        binding.addButtonSeries.setOnClickListener(this);

        this.context = getContext();

        this.db = new DatabaseSeries(this.getActivity());
        this.SeriesPresenter = new ListFragmentSeriesPresenter(this.db, this);
        this.adapter = new SeriesAdapter(this.getActivity(), getParentFragmentManager());

        listView.setAdapter(this.adapter);
        this.SeriesPresenter.displayListSeries();
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void updateSeries(ArrayList<Series> series) {
        adapter.updateSeries(series);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void detailSeries() {

    }

    @Override
    public void addSeries() {

    }
}
