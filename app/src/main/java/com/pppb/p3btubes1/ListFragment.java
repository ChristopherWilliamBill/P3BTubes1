package com.pppb.p3btubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.ListFragmentBinding;

public class ListFragment extends Fragment implements View.OnClickListener{

    private ListFragmentBinding binding;
    private MoviesAdapter adapter;
    private ListPresenter presenter;
    private FragmentPresenter fragmentPresenter;

    public ListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = ListFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ListView listView = this.binding.lstMovie;
        this.presenter = new ListPresenter();
        this.adapter = new MoviesAdapter(getActivity(), this.presenter.movies, presenter);
        this.fragmentPresenter = new FragmentPresenter(getParentFragmentManager());
        binding.addButton.setOnClickListener(this);
        listView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onClick(View view) {
        if (view == binding.addButton){
            fragmentPresenter.createAddFragment();
        }
    }
}
