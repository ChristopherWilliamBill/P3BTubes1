package com.pppb.p3btubes1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.pppb.p3btubes1.databinding.SeriesListBinding;

import java.util.ArrayList;

public class SeriesAdapter extends BaseAdapter {
    private SeriesListBinding binding;
    private ArrayList<Series> arrSeries;
    private Context context;
    private FragmentManager fm;

    public SeriesAdapter(Context context, FragmentManager fm){
        this.context = context;
        this.arrSeries = new ArrayList<>();
        this.fm = fm;
    }

    @Override
    public int getCount() {
        return arrSeries.size();
    }

    @Override
    public Series getItem(int i) {
        return arrSeries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void updateSeries(ArrayList<Series> currentSeries){
        this.arrSeries = currentSeries;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        binding = SeriesListBinding.inflate(LayoutInflater.from(this.context), parent, false);
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = binding.getRoot();
            viewHolder = new ViewHolder(binding, i , this.fm, this.getItem(i));
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.updateView(this.getItem(i));

        return convertView;
    }

    private class ViewHolder implements View.OnClickListener{
        protected SeriesListBinding binding;
        private FragmentPresenter fragmentPresenter;
        private int i;
        private String temp;
        private Series currentSeries;

        public ViewHolder(SeriesListBinding binding, int i, FragmentManager fm, Series s){
            this.binding = binding;
            this.fragmentPresenter = new FragmentPresenter(fm);
            this.i = i;
            this.temp = "" + (i+1);
            this.binding.series.setOnClickListener(this);
            this.currentSeries = s;
        }

        public void updateView(Series series){
            this.binding.indexSeries.setText(temp);
            this.binding.tvSeriesTitle.setText(series.getTitle());
            this.binding.tvSeriesStatus.setText(series.getStatus());
            this.binding.tvSeriesRating.setText(series.getRating() + "/5");
            this.binding.tvSeriesEpisode.setText(series.getEpisode());
        }

        @Override
        public void onClick(View view) {
            if(view == this.binding.series){
                fragmentPresenter.createDetailFragmentSeries(currentSeries);
            }
        }
    }
}
