package com.example.krishna.movieslist.moviemodal.WebResponse;

import java.util.ArrayList;

public class Response_D {

    public StatusInfoMovie getStatus() {
        return status;
    }

    public void setStatus(StatusInfoMovie status) {
        this.status = status;
    }

    StatusInfoMovie status;

    public ArrayList<MovieInfoEff> getMovieEff() {
        return MovieEff;
    }

    public void setMovieEff(ArrayList<MovieInfoEff> movieEff) {
        MovieEff = movieEff;
    }

    ArrayList<MovieInfoEff>MovieEff;

    public String getLsFilmstrTitle() {
        return lsFilmstrTitle;
    }

    public void setLsFilmstrTitle(String lsFilmstrTitle) {
        this.lsFilmstrTitle = lsFilmstrTitle;
    }

    public String getLsFilmstrDescriptionLong() {
        return lsFilmstrDescriptionLong;
    }

    public void setLsFilmstrDescriptionLong(String lsFilmstrDescriptionLong) {
        this.lsFilmstrDescriptionLong = lsFilmstrDescriptionLong;
    }

    public String getLsBackgroundImageUrl() {
        return lsBackgroundImageUrl;
    }

    public void setLsBackgroundImageUrl(String lsBackgroundImageUrl) {
        this.lsBackgroundImageUrl = lsBackgroundImageUrl;
    }



    public String getLsTrailerImageURL() {
        return lsTrailerImageURL;
    }

    public void setLsTrailerImageURL(String lsTrailerImageURL) {
        this.lsTrailerImageURL = lsTrailerImageURL;
    }

    public String getLsFilmstrCode() {
        return lsFilmstrCode;
    }

    public void setLsFilmstrCode(String lsFilmstrCode) {
        this.lsFilmstrCode = lsFilmstrCode;
    }

    public String getLsFilmStrLanguage() {
        return lsFilmStrLanguage;
    }

    public void setLsFilmStrLanguage(String lsFilmStrLanguage) {
        this.lsFilmStrLanguage = lsFilmStrLanguage;
    }

    String lsFilmStrLanguage;


    String lsFilmstrTitle;
    String lsFilmstrDescriptionLong;
    String lsBackgroundImageUrl;
    String lsTrailerImageURL;
    String lsFilmstrCode;




}
