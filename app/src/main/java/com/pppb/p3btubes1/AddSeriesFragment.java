package com.pppb.p3btubes1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.pppb.p3btubes1.databinding.AddSeriesFragmentBinding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AddSeriesFragment extends Fragment implements View.OnClickListener{
    private AddSeriesFragmentBinding binding;
    private FragmentCreate presenter;
    private DatabaseSeries db;
    private String status;
    private ActivityResultLauncher<Intent> intentLauncher;

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
        presenter = new FragmentCreate(this.getParentFragmentManager());
        this.db = new DatabaseSeries(getContext());
        this.binding.addBtnSeries.setOnClickListener(this);
        this.binding.btnAddImageSeries.setOnClickListener(this);

        RadioGroup radioGroup = this.binding.radioGroupStatusSeries;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rbDroppedSeries:
                        status = "dropped";
                        binding.layoutRating.setVisibility(View.VISIBLE);
                        binding.etRating.setText("");
                        break;
                    case R.id.rbFinishedSeries:
                        status = "finished";
                        binding.layoutRating.setVisibility(View.VISIBLE);
                        binding.etRating.setText("");
                        break;
                    case R.id.rbWaitingSeries:
                        status = "waiting list";
                        binding.layoutRating.setVisibility(View.GONE);
                        binding.etRating.setText("" + 0);
                        break;
                    case R.id.rbOngoing:
                        status = "ongoing";
                        binding.layoutRating.setVisibility(View.VISIBLE);
                        binding.etRating.setText("");
                        break;
                }
            }
        });

        this.intentLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent reply = result.getData();
                Uri uri = reply.getData();
                Log.d("test", uri.toString());

                try{
                    Bitmap posterBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    binding.ivPosterSeries.setImageBitmap(posterBitmap);
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.addBtnSeries){
            BitmapDrawable drawable = (BitmapDrawable) this.binding.ivPosterSeries.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            Series series = new Series(this.binding.etTitle.getText().toString(), this.binding.etSynopsis.getText().toString(), status, Integer.parseInt(this.binding.etRating.getText().toString()), Integer.parseInt(this.binding.numEpisode.getText().toString()), bitmap);
            db.addSeries(series.getTitle(), series.getSynopsis(), series.getRating(), series.getStatus(), series.getEpisode(), series.getPoster());
            presenter.createListFragmentSeries();
        }
        else if(view == this.binding.btnAddImageSeries){
            Intent addPosterSeries = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            if(addPosterSeries.resolveActivity(getActivity().getPackageManager()) != null){
                this.intentLauncher.launch(addPosterSeries);
            }
        }
    }
}
