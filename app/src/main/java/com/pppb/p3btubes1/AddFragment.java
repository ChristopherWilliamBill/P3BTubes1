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

import com.pppb.p3btubes1.databinding.AddFragmentBinding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AddFragment extends Fragment implements View.OnClickListener{
    private AddFragmentBinding binding;
    private FragmentCreate presenter;
    private DatabaseMovie databasePresenter;

    private String status;
    private ActivityResultLauncher<Intent> intentLauncher;

    public AddFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = AddFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        presenter = new FragmentCreate(this.getParentFragmentManager());
        this.binding.addBtnMovie.setOnClickListener(this);
        this.databasePresenter = new DatabaseMovie(getContext());
        this.binding.btnAddImageMovie.setOnClickListener(this);

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

        this.intentLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent reply = result.getData();
                Uri uri = reply.getData();
                Log.d("test", uri.toString());

                try{
                    Bitmap posterBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    binding.ivPosterMovie.setImageBitmap(posterBitmap);
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
        if(view == this.binding.addBtnMovie){
            BitmapDrawable drawable = (BitmapDrawable) this.binding.ivPosterMovie.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            Movies movie = new Movies(this.binding.etTitle.getText().toString(), this.binding.etSynopsis.getText().toString(), Integer.parseInt(this.binding.etRating.getText().toString()), status, bitmap, "");
            databasePresenter.addMovie(movie.getTitle(), movie.getSynopsis(), movie.getRating(), movie.getStatus(), movie.getPoster());


            presenter.createListFragment();
        }
        else if(view == this.binding.btnAddImageMovie){
            Intent addPosterMovie = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            if(addPosterMovie.resolveActivity(getActivity().getPackageManager()) != null){
                this.intentLauncher.launch(addPosterMovie);
            }
        }
    }
}
