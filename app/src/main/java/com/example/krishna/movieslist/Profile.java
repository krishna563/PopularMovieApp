package com.example.krishna.movieslist;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class Profile {

    String Name;
    Uri picture;
    String email;

    public Profile() throws MalformedURLException {
        Name="name";
        picture= null;
        email="bestmovie@movie.com";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Uri getPicture() {
        return picture;
    }

    public void setPicture(Uri picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
