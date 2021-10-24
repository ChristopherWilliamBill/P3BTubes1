package com.pppb.p3btubes1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.AddFragmentBinding;

import java.util.ArrayList;

public class AddSeriesFragment extends Fragment implements View.OnClickListener{
    private AddFragmentBinding binding;
    private FragmentPresenter presenter;
    private DatabaseSeries db;
    private MoviesAdapter moviesAdapter;
    private StoragePresenter storagePresenter;
    private ArrayList<Series> arrayList;
    private MainActivity mainActivity;
    private String status;
    public AddSeriesFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = AddFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mainActivity = new MainActivity();
        presenter = new FragmentPresenter(this.getParentFragmentManager());
        this.binding.rbMovie.setOnClickListener(this);
        this.binding.rbSeries.setOnClickListener(this);
        this.binding.addBtnSeries.setOnClickListener(this);
        this.storagePresenter = new StoragePresenter();
        this.db = new DatabaseSeries(getContext());

        RadioGroup radioGroup = this.binding.radioGroupStatus;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rbDropped:
                        status = "dropped";
                        break;
                    case R.id.rbFinished:
                        status = "finished";
                        break;
                    case R.id.rbWaiting:
                        status = "waiting list";
                        break;
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.rbMovie){
            this.binding.jumlahEpisodeSeries.setVisibility(View.GONE);
        }else if(view == this.binding.rbSeries){
            this.binding.jumlahEpisodeSeries.setVisibility(View.VISIBLE);
        }else if(view == this.binding.addBtnMovie){
            Series series = new Series(this.binding.etTitle.getText().toString(), this.binding.etSynopsis.getText().toString(), status, Integer.parseInt(this.binding.etRating.getText().toString()), Integer.parseInt(this.binding.numEpisode.getText().toString()));
            db.addSeries(series.getTitle(), series.getSynopsis(), series.getRating(), series.getStatus(), series.getEpisode());
            presenter.createListFragmentSeries();
        }
    }
}
