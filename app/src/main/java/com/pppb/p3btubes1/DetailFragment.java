package com.pppb.p3btubes1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.DetailFragmentBinding;

public class DetailFragment extends Fragment implements View.OnClickListener{
    private DetailFragmentBinding binding;
    private Movies currentMovie;
    private DatabaseMovie db;
    private int id;
    private String status;
    private FragmentCreate fragmentPresenter;

    public DetailFragment(Movies currentMovie, int id){
        this.currentMovie = currentMovie;
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = DetailFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.binding.tvAddMovie.setText(this.currentMovie.getTitle());
        this.binding.status.setText("Status: " + this.currentMovie.getStatus());

        this.fragmentPresenter = new FragmentCreate(getParentFragmentManager());
        this.binding.saveChangesMovie.setOnClickListener(this);



        if(this.binding.status.getText().toString().equals("Status: waiting list" )){
            this.binding.rbWaiting.setChecked(true);
            binding.layoutRating.setVisibility(View.GONE);
        }else if(this.binding.status.getText().toString().equals("Status: finished" )){
            this.binding.rbComplete.setChecked(true);
        }else if(this.binding.status.getText().toString().equals("Status: dropped" )){
            this.binding.rbDropped.setChecked(true);
        }

        if(this.binding.rbWaiting.isChecked()){
            status = "waiting list";
        }else if(this.binding.rbDropped.isChecked()){
            status = "dropped";
        }else if(this.binding.rbComplete.isChecked()){
            status = "finished";
        }

        this.binding.etRating.setText("" + currentMovie.getRating());
        this.binding.detailSynopsis.setText("" + currentMovie.getSynopsis());
        //Log.d("test", currentMovie.getPoster().toString());
        this.binding.posterMovie.setImageBitmap(currentMovie.getPoster());


        this.db = new DatabaseMovie(getContext());

        RadioGroup radioGroup = this.binding.radioButtonStatus;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rbComplete:
                        binding.layoutRating.setVisibility(View.VISIBLE);
                        binding.etRating.setText("");
                        status = "finished";
                        break;
                    case R.id.rbDropped:
                        binding.layoutRating.setVisibility(View.VISIBLE);
                        binding.etRating.setText("");
                        status = "dropped";
                        break;
                    case R.id.rbWaiting:
                        binding.layoutRating.setVisibility(View.GONE);
                        binding.etRating.setText("" + 0);
                        status = "waiting list";
                        break;
                }
            }
        });

        return view;
    }


    @Override
    public void onClick(View view) {
        if(view == this.binding.saveChangesMovie){
            id++;
            this.db.updateMovie(status,this.binding.detailSynopsis.getText().toString(),Integer.parseInt(this.binding.etRating.getText().toString()),""+ id);
            fragmentPresenter.createListFragment();
        }
    }
}
