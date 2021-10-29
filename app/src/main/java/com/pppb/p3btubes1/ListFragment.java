package com.pppb.p3btubes1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.ListFragmentBinding;

import java.util.ArrayList;

public class ListFragment extends Fragment implements View.OnClickListener, ListFragmentPresenter.Imovies{

    private ListFragmentBinding binding;
    private MoviesAdapter adapter;
    private FragmentCreate fragmentPresenter;
    private Context context;
    private StoragePresenter storagePresenter;
    private ListFragmentPresenter listFragmentPresenter;
//    private ArrayList<Movies> arrMovies;
    private DatabaseMovie db;

    public ListFragment(){
    }

    public static ListFragment newInstance(){
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = ListFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ListView listView = this.binding.lstMovie;
        this.fragmentPresenter = new FragmentCreate(getParentFragmentManager());
        binding.addButton.setOnClickListener(this);
        this.binding.btnSortAZ.setOnClickListener(this);
        this.binding.btnSortRating.setOnClickListener(this);

        this.context = getContext();

        this.db = new DatabaseMovie(this.getActivity());
        this.listFragmentPresenter = new ListFragmentPresenter(this.db, this);
        this.adapter = new MoviesAdapter(this.getActivity(), getParentFragmentManager());

        listView.setAdapter(this.adapter);
        this.listFragmentPresenter.displayListMovie(false, false);
        this.adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.addButton){
            fragmentPresenter.createAddFragment();
        }else if(view == binding.btnSortAZ){
            this.listFragmentPresenter.displayListMovie(true, false);
        }else if(view == binding.btnSortRating){
            this.listFragmentPresenter.displayListMovie(false, true);
        }
    }

    @Override
    public void updateMovie(ArrayList<Movies> movie) {
        adapter.updateMovie(movie);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void detailMovie() {

    }

    @Override
    public void addMovie() {

    }
}
