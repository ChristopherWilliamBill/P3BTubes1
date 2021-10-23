package com.pppb.p3btubes1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabasePresenter extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Movies.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_MOVIES = "movies";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SYNOPSIS = "synopsis";
    private static final String COLUMN_RATING = "rating";

    public DatabasePresenter(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME_MOVIES
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_SYNOPSIS + " TEXT, "
                + COLUMN_RATING + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MOVIES);
        onCreate(db);
    }

    public void addMovie(String title, String synopsis, int rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_SYNOPSIS, synopsis);
        cv.put(COLUMN_RATING, rating);

        long result = db.insert(TABLE_NAME_MOVIES, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllMovie(){
        String query = "SELECT * FROM " + TABLE_NAME_MOVIES;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public ArrayList<Movies> loadMovie(){
        Cursor cursor = readAllMovie();
        ArrayList<Movies> arrayList= new ArrayList<>();
        if(cursor.getCount() == 0){
            Toast.makeText(this.context, "No Movies!", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                String title = (cursor.getString(1));
                String synopsis = (cursor.getString(2));
                int rating = (cursor.getInt(3));
                Movies movies = new Movies(title,synopsis,rating);
                arrayList.add(movies);
            }
        }
        return arrayList;
    }
}
