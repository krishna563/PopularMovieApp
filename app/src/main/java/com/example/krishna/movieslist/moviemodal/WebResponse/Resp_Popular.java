package com.example.krishna.movieslist.moviemodal.WebResponse;

import java.util.ArrayList;

public class Resp_Popular {


    public ArrayList<Popularmovie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Popularmovie> results) {
        this.results = results;
    }

    ArrayList<Popularmovie>results;
}
