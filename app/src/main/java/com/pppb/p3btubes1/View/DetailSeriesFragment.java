package com.pppb.p3btubes1.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.DatabaseSeries;
import com.pppb.p3btubes1.Model.Series;
import com.pppb.p3btubes1.R;
import com.pppb.p3btubes1.databinding.DetailSeriesFragmentBinding;

public class DetailSeriesFragment extends Fragment implements View.OnClickListener {

    private DetailSeriesFragmentBinding binding;
    private Series currentSeries;
    private DatabaseSeries db;
    private int id;
    private String status;
    private FragmentCreate fragmentPresenter;

    public DetailSeriesFragment(Series currentSeries, int id) {
        this.currentSeries = currentSeries;
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = DetailSeriesFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.binding.tvAddSeries.setText(this.currentSeries.getTitle());
        this.binding.tvStatusSeries.setText("Status: " + this.currentSeries.getStatus());
        this.binding.etRatingSeries.setText("" + this.currentSeries.getRating());
        this.binding.detailSynopsisSeries.setText(this.currentSeries.getSynopsis());
        this.binding.etEpisode.setText("" + this.currentSeries.getEpisode());
        this.binding.posterSeries.setImageBitmap(currentSeries.getPoster());

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
            if(!this.binding.detailSynopsisSeries.getText().toString().equalsIgnoreCase("") && !this.binding.etRatingSeries.getText().toString().equalsIgnoreCase("") && !this.status.equalsIgnoreCase("")) {
                id++;
                this.db.updateSeries(status, this.binding.detailSynopsisSeries.getText().toString(), Integer.parseInt(this.binding.etRatingSeries.getText().toString()), "" + id, Integer.parseInt(this.binding.etEpisode.getText().toString()));
                fragmentPresenter.createListFragmentSeries();
            }else{
                Toast toast = Toast.makeText(getContext(), "All field is required", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

}
