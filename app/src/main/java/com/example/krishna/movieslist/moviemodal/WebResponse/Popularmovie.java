package com.example.krishna.movieslist.moviemodal.WebResponse;

public class Popularmovie {

    String id;
    String title;
    String poster_path;
    String vote_average;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getThumbnail() {
        String url = "http://image.tmdb.org/t/p/w185/" + this.poster_path;
        return url;
    }
}
