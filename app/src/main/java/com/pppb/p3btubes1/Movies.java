package com.pppb.p3btubes1;

import android.graphics.Bitmap;

public class Movies {
    private String title;
    private String synopsis;
    private String status;
    private int rating;
    private Bitmap poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Bitmap getPoster() {
        return poster;
    }

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }

    public Movies(String title, String synopsis, int rating, String status, Bitmap poster){
        this.title = title;
        this.synopsis = synopsis;
        this.rating = rating;
        this.status = status;
        this.poster = poster;
    }


}
