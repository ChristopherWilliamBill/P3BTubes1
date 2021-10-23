package com.pppb.p3btubes1;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.ListFragmentBinding;

import java.util.ArrayList;

public class ListFragment extends Fragment implements View.OnClickListener, ListFragmentPresenter.Imovies{

    private ListFragmentBinding binding;
    private MoviesAdapter adapter;
    private FragmentPresenter fragmentPresenter;
    private Context context;
    private StoragePresenter storagePresenter;
    private ListFragmentPresenter listFragmentPresenter;
//    private ArrayList<Movies> arrMovies;
    private DatabasePresenter db;

    public ListFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = ListFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ListView listView = this.binding.lstMovie;
        this.fragmentPresenter = new FragmentPresenter(getParentFragmentManager());
        binding.addButton.setOnClickListener(this);

        this.context = getContext();

        this.db = new DatabasePresenter(this.getActivity());
        this.listFragmentPresenter = new ListFragmentPresenter(this.db, this);
        this.adapter = new MoviesAdapter(this.getActivity(), getParentFragmentManager());

        listView.setAdapter(this.adapter);
        this.listFragmentPresenter.displayListMovie();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.addButton){
            fragmentPresenter.createAddFragment();
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
