package com.pppb.p3btubes1;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

public class FragmentPresenter {

    private FragmentManager fragmentManager;

    public FragmentPresenter(FragmentManager fm){
        fragmentManager = fm;
    }

    public void createMainFragment(){
        Bundle result = new Bundle();
        result.putInt("page", 1);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public void createListFragment(){
        Bundle result = new Bundle();
        result.putInt("page", 2);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public void createAddFragment(){
        Bundle result = new Bundle();
        result.putInt("page", 3);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public void createDetailFragment(int i){
        Bundle result = new Bundle();
        result.putInt("page", 4);
        result.putInt("index", i);
        fragmentManager.setFragmentResult("index", result);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public interface IMainActivity{
        void changePage(int page);
    }
}
