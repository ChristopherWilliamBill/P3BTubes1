package com.pppb.p3btubes1;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.pppb.p3btubes1.databinding.ActivityMainBinding;

public class FragmentPresenter {

    private FragmentManager fragmentManager;

    public FragmentPresenter(FragmentManager fm){
        fragmentManager = fm;
    }

    public void createListFragment(){
        Log.d("test","dari FragmentPresenter");
        Bundle result = new Bundle();
        result.putInt("page", 2);
        fragmentManager.setFragmentResult("changePage", result);
    }

    public interface IMainActivity{
        void changePage(int page);
    }
}
