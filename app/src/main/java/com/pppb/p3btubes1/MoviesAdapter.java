package com.pppb.p3btubes1;

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
    private ArrayList<Movies> arrMovies;
    private Context context;
    private FragmentManager fm;

    public MoviesAdapter(Context context, FragmentManager fm){
        this.context = context;
        this.arrMovies = new ArrayList<>();
        this.fm = fm;
    }

    @Override
    public int getCount() {
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

    public void updateMovie(ArrayList<Movies> currentList){
        this.arrMovies = currentList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        binding = MoviesListBinding.inflate(LayoutInflater.from(this.context), parent, false);
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = binding.getRoot();
            viewHolder = new ViewHolder(binding, i , this.fm, this.arrMovies.get(i));
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.updateView(this.getItem(i));

        return convertView;
    }

    private class ViewHolder implements View.OnClickListener{
        protected MoviesListBinding binding;
        private FragmentCreate fragmentPresenter;
        private String i;
        private String temp;
        private Movies currentMovie;

        public ViewHolder(MoviesListBinding binding, int i, FragmentManager fm, Movies m){
            this.binding = binding;
            this.fragmentPresenter = new FragmentCreate(fm);
            this.i = m.getId();
            this.binding.movie.setOnClickListener(this);
            this.currentMovie = m;
        }

        public void updateView(Movies movie){
            this.binding.index.setText(i + ".");
            this.binding.tvMovieTitle.setText(movie.getTitle());
            this.binding.tvMovieStatus.setText(movie.getStatus());
            if(movie.getRating() != 0){
                this.binding.tvRating.setText(movie.getRating() + "/5");
            }
            this.currentMovie = movie;
        }

        @Override
        public void onClick(View view) {
            if(view == this.binding.movie){
                fragmentPresenter.createDetailFragment(currentMovie);
            }
        }
    }
}
