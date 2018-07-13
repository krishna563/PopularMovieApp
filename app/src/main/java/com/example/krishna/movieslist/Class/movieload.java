package com.example.krishna.movieslist.Class;

public class movieload {
    private String mTitle;
    private String mVote;
    private int mThumbnail;

    public movieload(String mTitle,String mVote,int mThumbnail) {
        this.mTitle = mTitle;
        this.mVote = mVote;
        this.mThumbnail = mThumbnail;
        }

    public String getmTitle() {
        return mTitle;
    }


    public String getmVote() {
        return mVote;
    }


    public int getmThumbnail() {
        return mThumbnail;
    }

}
