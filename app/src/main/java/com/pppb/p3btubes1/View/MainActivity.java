package com.pppb.p3btubes1.View;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.pppb.p3btubes1.Model.Movies;
import com.pppb.p3btubes1.Model.Series;
import com.pppb.p3btubes1.R;
import com.pppb.p3btubes1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentCreate.IMainActivity{

    private ActivityMainBinding binding;
    private MainFragment mainFragment;
    private ListFragment listFragment;
    private ListFragmentSeries listFragmentSeries;
    private AddFragment addFragment;
    private DetailFragment detailFragment;
    private AddSeriesFragment addSeriesFragment;
    private FragmentCreate presenter;
    private FragmentManager fragmentManager;
    private DetailSeriesFragment detailSeriesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.setSupportActionBar(this.binding.toolbar);
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this,this.binding.drawerLayout,this.binding.toolbar, R.string.openDrawer,R.string.closeDrawer);
        abdt.syncState();

        this.listFragment = ListFragment.newInstance();
        this.listFragmentSeries = ListFragmentSeries.newInstance();
        this.mainFragment = new MainFragment();
        this.addFragment = new AddFragment();
        this.addSeriesFragment = AddSeriesFragment.newInstance();

        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(this.binding.fragmentContainer.getId(), this.mainFragment).addToBackStack(null);
        ft.commit();

        this.getSupportFragmentManager().setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int page = result.getInt("page");
                changePage(page);
            }
        });

        this.getSupportFragmentManager().setFragmentResultListener("movieDetail", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("title");
                String synopsis = result.getString("synopsis");
                int rating  = result.getInt("rating");
                String status  = result.getString("status");
                int index = result.getInt("index");
                Bitmap poster = result.getParcelable("poster");
                Movies currentMovie = new Movies(title,synopsis,rating, status, poster, String.valueOf(index));
                detailFragment = new DetailFragment(currentMovie);
            }
        });
        this.getSupportFragmentManager().setFragmentResultListener("seriesDetail", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String title = result.getString("title");
                String synopsis = result.getString("synopsis");
                int rating  = result.getInt("rating");
                String status  = result.getString("status");
                int index = result.getInt("index");
                int episodes = result.getInt("episode");
                Bitmap poster = result.getParcelable("poster");
                Series currentSeries = new Series(title, synopsis, status, rating, episodes, poster);
                detailSeriesFragment = new DetailSeriesFragment(currentSeries, index);
            }
        });
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page == 1){
            ft.replace(this.binding.fragmentContainer.getId(), mainFragment).addToBackStack(null);
        }else if(page == 2){
            ft.replace(this.binding.fragmentContainer.getId(), listFragment).addToBackStack(null);
        }else if(page == 3){
            ft.replace(this.binding.fragmentContainer.getId(), addFragment).addToBackStack(null);
        }else if(page == 4){
            ft.replace(this.binding.fragmentContainer.getId(), detailFragment).addToBackStack(null);
        }else if(page == 5){
            ft.replace(this.binding.fragmentContainer.getId(), listFragmentSeries).addToBackStack(null);
        }else if(page == 6){
            ft.replace(this.binding.fragmentContainer.getId(), detailSeriesFragment).addToBackStack(null);
        }else if(page == 7){
            ft.replace(this.binding.fragmentContainer.getId(), addSeriesFragment).addToBackStack(null);
        }
        ft.commit();
        this.binding.drawerLayout.closeDrawers();
    }
}