package com.pppb.p3btubes1;

public class Series {
    private String title;
    private String synopsis;
    private String status;
    private int rating;
    private int episode;

    public Series(String title, String synopsis, String status, int rating, int episode) {
        this.title = title;
        this.synopsis = synopsis;
        this.status = status;
        this.rating = rating;
        this.episode = episode;
    }


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }
}
