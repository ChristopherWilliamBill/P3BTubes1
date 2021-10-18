package com.pppb.p3btubes1;

public class Movies {
    private String title;
    private String synopsis;
    private int rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
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

    public Movies(String title, String synopsis, int rating){
        this.title = title;
        this.synopsis = synopsis;
        this.rating = rating;
    }


}
