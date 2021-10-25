package com.pppb.p3btubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.AddFragmentBinding;

import java.util.ArrayList;

public class AddFragment extends Fragment implements View.OnClickListener{
    private AddFragmentBinding binding;
    private FragmentCreate presenter;
    private DatabaseMovie databasePresenter;
    private MoviesAdapter moviesAdapter;
    private StoragePresenter storagePresenter;
    private ArrayList<Movies> arrayList;
    private MainActivity mainActivity;
    private String status;
    public AddFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = AddFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mainActivity = new MainActivity();
        presenter = new FragmentCreate(this.getParentFragmentManager());
        this.binding.addBtnMovie.setOnClickListener(this);
        this.storagePresenter = new StoragePresenter();
        this.arrayList = storagePresenter.loadData(getContext());
        this.databasePresenter = new DatabaseMovie(getContext());

        RadioGroup radioGroup = this.binding.radioGroupStatus;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rbDropped:
                        status = "dropped";
                        binding.layoutRating.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbFinished:
                        status = "finished";
                        binding.layoutRating.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbWaiting:
                        status = "waiting list";
                        binding.layoutRating.setVisibility(View.GONE);
                        binding.etRating.setText("" + 0);
                        break;
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.addBtnMovie){
            Movies movie = new Movies(this.binding.etTitle.getText().toString(), this.binding.etSynopsis.getText().toString(), Integer.parseInt(this.binding.etRating.getText().toString()), status);
            databasePresenter.addMovie(movie.getTitle(), movie.getSynopsis(), movie.getRating(), movie.getStatus());
            presenter.createListFragment();
        }
    }
}
