package com.pppb.p3btubes1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.DetailSeriesFragmentBinding;

public class DetailSeriesFragment extends Fragment implements View.OnClickListener {

    private DetailSeriesFragmentBinding binding;
    private Series currentSeries;
    private DatabaseSeries db;
    private int id;
    private String status;
    private FragmentCreate fragmentPresenter;

    private DetailSeriesFragment(){}

    public static DetailSeriesFragment newInstance(String title, String synopsis, int rating, String status, int index, int episodes){
        DetailSeriesFragment fragment = new DetailSeriesFragment();
        Bundle result = new Bundle();
        result.putString("title", title);
        result.putString("synopsis", synopsis);
        result.putInt("rating", rating);
        result.putString("status", status);
        result.putInt("index", index);
        result.putInt("episodes", episodes);
        fragment.setArguments(result);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = DetailSeriesFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        this.id = this.getArguments().getInt("index", 1);
        this.currentSeries = new Series(this.getArguments().getString("title", ""), this.getArguments().getString("synopsis", ""), this.getArguments().getString("status", ""), this.getArguments().getInt("rating", 0), this.getArguments().getInt("episodes", 0));
        Log.d("debug", currentSeries.getTitle());
        this.binding.tvAddSeries.setText(this.currentSeries.getTitle());
        this.binding.tvStatusSeries.setText("Status: " + this.currentSeries.getStatus());
        this.binding.etRatingSeries.setText("" + this.currentSeries.getRating());
        this.binding.detailSynopsisSeries.setText(this.currentSeries.getSynopsis());
        this.binding.etEpisode.setText("" + this.currentSeries.getEpisode());



        this.fragmentPresenter = new FragmentCreate(getParentFragmentManager());
        this.binding.saveChangesSeries.setOnClickListener(this);

        if(this.binding.tvStatusSeries.getText().toString().equals("Status: waiting list" )){
            this.binding.rbWaitingSeries.setChecked(true);
            binding.layoutRatingSeries.setVisibility(View.GONE);
        }else if(this.binding.tvStatusSeries.getText().toString().equals("Status: finished" )){
            this.binding.rbCompleteSeries.setChecked(true);
        }else if(this.binding.tvStatusSeries.getText().toString().equals("Status: dropped" )){
            this.binding.rbDroppedSeries.setChecked(true);
        }else if(this.binding.tvStatusSeries.getText().toString().equals("Status: ongoing" )){
            this.binding.rbOngoingSeries.setChecked(true);
        }

        this.db = new DatabaseSeries(getContext());

        if(this.binding.rbOngoingSeries.isChecked()){
            binding.numEpisodeSeries.setVisibility(View.VISIBLE);
        }

        if(this.binding.rbOngoingSeries.isChecked()){
            status = "ongoing";
        }else if(this.binding.rbCompleteSeries.isChecked()){
            status = "finished";
        }else if(this.binding.rbDroppedSeries.isChecked()){
            status = "dropped";
        }else if(this.binding.rbWaitingSeries.isChecked()){
            status = "waiting list";
        }

        RadioGroup radioGroup = this.binding.radioButtonStatusSeries;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rbCompleteSeries:
                        binding.layoutRatingSeries.setVisibility(View.VISIBLE);
                        binding.etRatingSeries.setText("");
                        binding.etEpisode.setText("" + 0);
                        status = "finished";
                        break;
                    case R.id.rbDroppedSeries:
                        binding.layoutRatingSeries.setVisibility(View.VISIBLE);
                        binding.etRatingSeries.setText("");
                        binding.etEpisode.setText("" + 0);
                        status = "dropped";
                        break;
                    case R.id.rbWaitingSeries:
                        binding.layoutRatingSeries.setVisibility(View.GONE);
                        binding.etRatingSeries.setText("" + 0);
                        binding.numEpisodeSeries.setVisibility(View.GONE);
                        binding.etEpisode.setText("" + 0);
                        status = "waiting list";
                        break;
                    case R.id.rbOngoingSeries:
                        binding.layoutRatingSeries.setVisibility(View.VISIBLE);
                        binding.etRatingSeries.setText("");
                        binding.numEpisodeSeries.setVisibility(View.VISIBLE);
                        status = "ongoing";
                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.saveChangesSeries){
            id++;
            this.db.updateSeries(status,this.binding.detailSynopsisSeries.getText().toString(),Integer.parseInt(this.binding.etRatingSeries.getText().toString()), "" + id , Integer.parseInt(this.binding.etEpisode.getText().toString()));
            fragmentPresenter.createListFragmentSeries();
        }
    }

}
