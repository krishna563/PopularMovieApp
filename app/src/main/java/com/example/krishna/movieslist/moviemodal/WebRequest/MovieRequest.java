package com.example.krishna.movieslist.moviemodal.WebRequest;

import com.google.gson.annotations.SerializedName;

public class MovieRequest {

        @SerializedName("lsCinemaId")
        public String lsCinemaId;
        @SerializedName("lsMovieId")
        public String lsMovieId;
       /* @SerializedName("lsAPPId")
        public String lsAPPId;
        @SerializedName("lsUserId")
        public String lsUserId;*/


        public MovieRequest(String lsCinemaId, String lsMovieId ) {
            this.lsCinemaId = lsCinemaId;
            this.lsMovieId = lsMovieId;
            /*this.lsAPPId=lsAPPId;
            this.lsUserId=lsUserId;*/
        }

}
