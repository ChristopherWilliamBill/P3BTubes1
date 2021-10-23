package com.pppb.p3btubes1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
        convertView = LayoutInflater.from(this.context).inflate(R.layout.movies_list, parent, false);
        ViewHolder vh = new ViewHolder(convertView);
        Movies current = this.getItem(i);
        vh.updateView(current);

        return convertView;
    }

    private class ViewHolder{
        protected TextView itemtitle;

        public ViewHolder(View view){
            this.itemtitle = view.findViewById(R.id.tvMovieTitle);
        }

        public void updateView(final Movies movie){
            this.itemtitle.setText(movie.getTitle());
        }
    }
//        binding = MoviesListBinding.inflate(LayoutInflater.from(this.context),parent,false);
//        ViewHolder viewHolder;
//        if(convertView == null){
//            convertView = binding.getRoot();
//            viewHolder = new ViewHolder(binding, i, fm);
//            convertView.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        viewHolder.updateView(this.getItem(i));
//        return convertView;


//    }

//    private class ViewHolder implements View.OnClickListener{
//        protected MoviesListBinding binding;
//        private FragmentPresenter fragmentPresenter;
//        private int i;
//
//        public ViewHolder(MoviesListBinding binding, int i, FragmentManager fm){
//            this.binding = binding;
//            this.fragmentPresenter = new FragmentPresenter(fm);
//            this.i = i;
//            this.binding.movie.setOnClickListener(this);
//        }
//
//        public void updateView(Movies movie){
//            this.binding.tvMovieTitle.setText(movie.getTitle());
//            this.binding.tvRating.setText(movie.getRating() + "/5");
//        }
//
//        @Override
//        public void onClick(View view) {
//            if(view == this.binding.movie){
//                fragmentPresenter.createDetailFragment(i);
//            }
//        }
//    }
}
