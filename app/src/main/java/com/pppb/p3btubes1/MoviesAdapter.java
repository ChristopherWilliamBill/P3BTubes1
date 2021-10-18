package com.pppb.p3btubes1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.recyclerview.widget.RecyclerView;

import com.pppb.p3btubes1.databinding.ActivityMainBinding;
import com.pppb.p3btubes1.databinding.ListFragmentBinding;
import com.pppb.p3btubes1.databinding.MoviesListBinding;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends BaseAdapter {
    private MoviesListBinding binding;
    private Activity activity;
    private ListPresenter presenter;

    public MoviesAdapter(Activity activity, ArrayList<Movies> movies, ListPresenter presenter){
        this.activity = activity;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return presenter.movies.size();
    }

    @Override
    public Movies getItem(int i) {
        return presenter.movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        binding = MoviesListBinding.inflate(LayoutInflater.from(this.activity),parent,false);

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = binding.getRoot();
            viewHolder = new ViewHolder(binding, i , presenter);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.updateView(this.getItem(i));
        return convertView;
    }

    private class ViewHolder{
        protected MoviesListBinding binding;
        private int i;
        private ListPresenter lPresenter;

        public ViewHolder(MoviesListBinding binding, int i, ListPresenter presenter){
            this.binding = binding;
            this.i = i;
            this.lPresenter = presenter;
        }

        public void updateView(Movies movie){
            this.binding.tvMovieTitle.setText(movie.getTitle());
        }
    }
}
