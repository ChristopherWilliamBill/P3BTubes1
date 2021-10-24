package com.pppb.p3btubes1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.ListFragmentBinding;
import com.pppb.p3btubes1.databinding.ListFragmentSeriesBinding;

public class ListFragmentSeries extends Fragment implements View.OnClickListener {

    private ListFragmentSeriesBinding binding;
    private FragmentPresenter fragmentPresenter;
    //private SeriesAdapter adapter;
    private Context context;
    private DatabasePresenter db;


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
        this.fragmentPresenter = new FragmentPresenter(getParentFragmentManager());
        binding.addButtonSeries.setOnClickListener(this);

        this.context = getContext();

        this.db = new DatabasePresenter(this.getActivity());
        //this.listFragmentPresenter = new ListFragmentPresenter(this.db, this);
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
