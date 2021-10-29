package com.pppb.p3btubes1.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.DatabaseMovie;
import com.pppb.p3btubes1.Presenter.ListFragmentPresenter;
import com.pppb.p3btubes1.Model.Movies;
import com.pppb.p3btubes1.databinding.ListFragmentBinding;

import java.util.ArrayList;

public class ListFragment extends Fragment implements View.OnClickListener, ListFragmentPresenter.Imovies{

    private ListFragmentBinding binding;
    private MoviesAdapter adapter;
    private FragmentCreate fragmentPresenter;
    private Context context;
    private ListFragmentPresenter listFragmentPresenter;
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
        this.binding.btnSearch.setOnClickListener(this);


        this.context = getContext();

        this.db = new DatabaseMovie(this.getActivity());
        this.listFragmentPresenter = new ListFragmentPresenter(this.db, this);
        this.adapter = new MoviesAdapter(this.getActivity(), getParentFragmentManager());

        listView.setAdapter(this.adapter);
        this.listFragmentPresenter.displayListMovie(false, false, false, "");
        this.adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.addButton){
            fragmentPresenter.createAddFragment();
        }else if(view == binding.btnSortAZ){
            this.listFragmentPresenter.displayListMovie(true, false, false, "");
        }else if(view == binding.btnSortRating){
            this.listFragmentPresenter.displayListMovie(false, true, false, "");
        }else if(view == binding.btnSearch){
            this.listFragmentPresenter.displayListMovie(false, false, true, this.binding.etSearch.getText().toString());
            InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void updateMovie(ArrayList<Movies> movie) {
        adapter.updateMovie(movie);
        adapter.notifyDataSetChanged();
    }
}
