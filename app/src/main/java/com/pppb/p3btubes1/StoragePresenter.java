package com.pppb.p3btubes1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoragePresenter {

    private ArrayList<Movies> arrayList;
    public StoragePresenter(){}

    public void saveData(Context context, ArrayList<Movies> moviesArrayList){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(moviesArrayList);
        editor.putString("Movie" , json);
        editor.apply();
    }

    public ArrayList<Movies> loadData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Movie", null);
        Type type = new TypeToken<ArrayList<Movies>>(){}.getType();
        this.arrayList = gson.fromJson(json,type);
        if(arrayList == null){
            arrayList = new ArrayList<Movies>();
        }
        return arrayList;
    }
}
