package com.pppb.p3btubes1;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.pppb.p3btubes1.databinding.MoviesListBinding;

import java.util.ArrayList;

public class MoviesAdapter extends BaseAdapter {
    private MoviesListBinding binding;
    private StoragePresenter storagePresenter;
    private ArrayList<Movies> arrMovies;
    private Context context;
    private Activity activity;
    private FragmentManager fm;

    public MoviesAdapter(Activity activity, Context context, FragmentManager fm){
        this.storagePresenter = new StoragePresenter();
        this.activity = activity;
        this.context = context;
        this.arrMovies = new ArrayList<Movies>();
        arrMovies.add(new Movies("Judul","Sinopsis",3));
        this.arrMovies = storagePresenter.loadData(context);
        this.fm = fm;
    }

    @Override
    public int getCount() {
        this.arrMovies = storagePresenter.loadData(context);
        return arrMovies.size();
    }

    @Override
    public Movies getItem(int i) {
        return arrMovies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void addMovie(Movies movie){
        this.arrMovies.add(movie);
        notifyDataSetChanged();
        this.storagePresenter.saveData(context, arrMovies);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        binding = MoviesListBinding.inflate(LayoutInflater.from(this.activity),parent,false);

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = binding.getRoot();
            viewHolder = new ViewHolder(binding, i, fm);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.updateView(this.getItem(i));
        return convertView;
    }

    private class ViewHolder implements View.OnClickListener{
        protected MoviesListBinding binding;
        private FragmentPresenter fragmentPresenter;
        private int i;

        public ViewHolder(MoviesListBinding binding, int i, FragmentManager fm){
            this.binding = binding;
            this.fragmentPresenter = new FragmentPresenter(fm);
            this.i = i;
            this.binding.movie.setOnClickListener(this);
        }

        public void updateView(Movies movie){
            this.binding.tvMovieTitle.setText(movie.getTitle());
            this.binding.tvRating.setText(movie.getRating() + "/5");

        }

        @Override
        public void onClick(View view) {
            if(view == this.binding.movie){
                fragmentPresenter.createDetailFragment(i);
            }
        }
    }
}