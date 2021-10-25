package com.pppb.p3btubes1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.AddSeriesFragmentBinding;

import java.util.ArrayList;

public class AddSeriesFragment extends Fragment implements View.OnClickListener{
    private AddSeriesFragmentBinding binding;
    private FragmentCreate presenter;
    private DatabaseSeries db;
    private MoviesAdapter moviesAdapter;
    private StoragePresenter storagePresenter;
    private ArrayList<Series> arrayList;
    private MainActivity mainActivity;
    private String status;
    public AddSeriesFragment(){}

    public static AddSeriesFragment newInstance() {
        AddSeriesFragment fragment = new AddSeriesFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = AddSeriesFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mainActivity = new MainActivity();
        presenter = new FragmentCreate(this.getParentFragmentManager());
        this.storagePresenter = new StoragePresenter();
        this.db = new DatabaseSeries(getContext());
        this.binding.addBtnSeries.setOnClickListener(this);

        RadioGroup radioGroup = this.binding.radioGroupStatusSeries;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rbDroppedSeries:
                        status = "dropped";
                        break;
                    case R.id.rbFinishedSeries:
                        status = "finished";
                        break;
                    case R.id.rbWaitingSeries:
                        status = "waiting list";
                        break;
                    case R.id.rbOngoing:
                        status = "ongoing";
                        break;
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.addBtnSeries){
            Log.d("test", "addbtnseries");
            Series series = new Series(this.binding.etTitle.getText().toString(), this.binding.etSynopsis.getText().toString(), status, Integer.parseInt(this.binding.etRating.getText().toString()), Integer.parseInt(this.binding.numEpisode.getText().toString()));
            db.addSeries(series.getTitle(), series.getSynopsis(), series.getRating(), series.getStatus(), series.getEpisode());
            presenter.createListFragmentSeries();
        }
    }
}
