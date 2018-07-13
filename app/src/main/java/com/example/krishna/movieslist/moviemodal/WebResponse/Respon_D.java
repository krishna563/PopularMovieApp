package com.example.krishna.movieslist.moviemodal.WebResponse;

import java.util.ArrayList;

public class Respon_D {
    public Statusmovie getStatus() {
        return status;
    }

    public void setStatus(Statusmovie status) {
        this.status = status;
    }

    Statusmovie status;


    public ArrayList<Movieff> getMovieEff() {
        return MovieEff;
    }

    public void setMovieEff(ArrayList<Movieff> movieEff) {
        MovieEff = movieEff;
    }

    ArrayList<Movieff> MovieEff;


    String lsFilmstrTitle;
    String lsFilmStrLanguage;
    String lsFilmstrCensor;
    String lsFilmCategoryName;
    String lsFilmstrDescriptionLong;
    String lsFilmintDuration;
    String lsActors;
    String lsTrailerImageURL;
    String lsBackgroundImageUrl;

    public String getLsTrailerImageURL() {
        return lsTrailerImageURL;
    }

    public void setLsTrailerImageURL(String lsTrailerImageURL) {
        this.lsTrailerImageURL = lsTrailerImageURL;
    }

    public String getLsBackgroundImageUrl() {
        return lsBackgroundImageUrl;
    }

    public void setLsBackgroundImageUrl(String lsBackgroundImageUrl) {
        this.lsBackgroundImageUrl = lsBackgroundImageUrl;
    }




    public String getLsFilmstrTitle() {
        return lsFilmstrTitle;
    }

    public void setLsFilmstrTitle(String lsFilmstrTitle) {
        this.lsFilmstrTitle = lsFilmstrTitle;
    }

    public String getLsFilmStrLanguage() {
        return lsFilmStrLanguage;
    }

    public void setLsFilmStrLanguage(String lsFilmStrLanguage) {
        this.lsFilmStrLanguage = lsFilmStrLanguage;
    }

    public String getLsFilmstrCensor() {
        return lsFilmstrCensor;
    }

    public void setLsFilmstrCensor(String lsFilmstrCensor) {
        this.lsFilmstrCensor = lsFilmstrCensor;
    }

    public String getLsFilmCategoryName() {
        return lsFilmCategoryName;
    }

    public void setLsFilmCategoryName(String lsFilmCategoryName) {
        this.lsFilmCategoryName = lsFilmCategoryName;
    }

    public String getLsFilmstrDescriptionLong() {
        return lsFilmstrDescriptionLong;
    }

    public void setLsFilmstrDescriptionLong(String lsFilmstrDescriptionLong) {
        this.lsFilmstrDescriptionLong = lsFilmstrDescriptionLong;
    }

    public String getLsFilmintDuration() {
        return lsFilmintDuration;
    }

    public void setLsFilmintDuration(String lsFilmintDuration) {
        this.lsFilmintDuration = lsFilmintDuration;
    }

    public String getLsActors() {
        return lsActors;
    }

    public void setLsActors(String lsActors) {
        this.lsActors = lsActors;
    }
}
