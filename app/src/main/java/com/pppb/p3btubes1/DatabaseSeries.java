package com.pppb.p3btubes1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseSeries extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Series.db";
    private static final int DATABASE_VERSION = 1;

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SYNOPSIS = "synopsis";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_STATUS = "status";
    private static final String TABLE_NAME_SERIES = "series";
    private static final String COLUMN_EPISODES = "episodes";
    private static final String COLUMN_POSTER = "poster";
    private ByteArrayOutputStream putPoster;
    private byte[] posterInBytes;


    public DatabaseSeries(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME_SERIES
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_SYNOPSIS + " TEXT, "
                + COLUMN_RATING + " INTEGER, "
                + COLUMN_STATUS + " TEXT, "
                + COLUMN_EPISODES + " INTEGER, "
                + COLUMN_POSTER + " BLOB)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SERIES);
        onCreate(db);
    }


    public void addSeries(String title, String synopsis, int rating, String status, int episodes, Bitmap poster){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        this.putPoster = new ByteArrayOutputStream();
        poster.compress(Bitmap.CompressFormat.JPEG,100, putPoster);
        this.posterInBytes = putPoster.toByteArray();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_SYNOPSIS, synopsis);
        cv.put(COLUMN_RATING, rating);
        cv.put(COLUMN_STATUS, status);
        cv.put(COLUMN_EPISODES, episodes);
        cv.put(COLUMN_POSTER, posterInBytes);


        long result = db.insert(TABLE_NAME_SERIES, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateSeries(String status, String synopsis, int rating, String id, int episodes){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SYNOPSIS, synopsis);
        cv.put(COLUMN_RATING, rating);
        cv.put(COLUMN_STATUS, status);
        cv.put(COLUMN_EPISODES, episodes);
        Log.d("test", "" + id);

        long result = db.update(TABLE_NAME_SERIES, cv, "id = ?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to update!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllSeries(){
        String query = "SELECT * FROM " + TABLE_NAME_SERIES;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public ArrayList<Series> loadSeries(boolean ascending, boolean sortRating, boolean isSearch, String search){
        Cursor cursor = readAllSeries();

        if(ascending) {
            cursor = readAllSeriesAZ();
        }
        if(sortRating){
            cursor = readAllSeriesRating();
        }
        if (isSearch){
            cursor = readAllSeriesSearch(search);
        }

        ArrayList<Series> arrayListSeries= new ArrayList<>();
        if(cursor.getCount() == 0){
            Toast.makeText(this.context, "No Series!", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                String title = (cursor.getString(1));
                String synopsis = (cursor.getString(2));
                int rating = (cursor.getInt(3));
                String status = (cursor.getString(4));
                int episode = (cursor.getInt(5));
                byte[] byteArray = (cursor.getBlob(6));
                Bitmap poster = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);

                Series series = new Series(title, synopsis, status, rating, episode, poster);
                arrayListSeries.add(series);
            }
        }
        return arrayListSeries;
    }

    public Cursor readAllSeriesAZ(){
        String query = "SELECT * FROM " + TABLE_NAME_SERIES + " ORDER BY " + COLUMN_TITLE +  " ASC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllSeriesRating(){
        String query = "SELECT * FROM " + TABLE_NAME_SERIES + " ORDER BY " + COLUMN_RATING +  " DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllSeriesSearch(String search){
        String searchQuery = "'" + search + "%'";
        String query = "SELECT * FROM " + TABLE_NAME_SERIES + " WHERE " + COLUMN_TITLE +  " LIKE " + searchQuery;

        Log.d("search" , query);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
