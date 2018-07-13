package com.example.krishna.movieslist.moviemodal.WebRequest;

import com.google.gson.annotations.SerializedName;

public class CinemaRequest {


    /*@SerializedName("lsCinemaId")
    public String lsCinemaId;
    @SerializedName("lsType")
    public String lsType;

    public CinemaRequest(String lsCinemaId, String lsType) {
        this.lsCinemaId = lsCinemaId;
        this.lsType = lsType;
    }*/


    @SerializedName("api_key")
    public String api_key;
    @SerializedName("language")
    public String language;

    public CinemaRequest(String api_key, String language) {
        this.api_key = api_key;
        this.language = language;
    }
}
