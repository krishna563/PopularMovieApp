package com.example.krishna.movieslist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.krishna.movieslist.Webservice.RetrofitInterface_Activity;
import com.example.krishna.movieslist.moviemodal.WebRequest.MovieRequest;
import com.example.krishna.movieslist.moviemodal.WebResponse.MovieResponse;
import com.example.krishna.movieslist.moviemodal.WebResponse.Movieff;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Moviedescription_Activity extends AppCompatActivity {

    ProgressDialog progressDialog;

    ImageView img_header, img_favorite,img_playicon;
    TextView txt_movietitle, txt_movielanguage, txt_censor, txt_movietype, txt_title, txt_synp_descrp,
            txt_time, txt_duraion_descrption, txt_casthero, txt_cast_descrption, txt_Eff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fonts);
        img_header = (ImageView) findViewById(R.id.header);
        img_favorite = (ImageView) findViewById(R.id.favorite);
        img_playicon = (ImageView) findViewById(R.id.img_play);

        txt_movietitle=(TextView)findViewById(R.id.movtitle);
        txt_movielanguage=(TextView)findViewById(R.id.movielanguage);
        txt_censor=(TextView)findViewById(R.id.txtcensor);
        txt_movietype=(TextView)findViewById(R.id.movietype);
        txt_synp_descrp=(TextView)findViewById(R.id.synp_descrp);
        txt_duraion_descrption=(TextView)findViewById(R.id.duraion_descrption);
        txt_cast_descrption=(TextView)findViewById(R.id.cast_descrption);
        txt_Eff=(TextView)findViewById(R.id.txteffect);


        img_playicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Moviedescription_Activity.this, Youtubeplayer_Activity.class);
                startActivity(intent);
            }
        });

//Extract the data…
        String stuff = getIntent().getStringExtra("MovieID_stuff");
        moviedetail(stuff);
    }


    private void moviedetail(String stuff) {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        RetrofitInterface_Activity.APIInterface apiInterface = RetrofitInterface_Activity.getClient();
        final MovieRequest user = new MovieRequest("100", stuff);
        /*final CinemaRequest user = new CinemaRequest("100", "cs");*/
        Call<MovieResponse> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressDialog.dismiss();
                MovieResponse user1 = response.body();
                Toast.makeText(getApplicationContext(), user1.getD().getStatus().getStatusID(), Toast.LENGTH_LONG).show();
                /*Toast.makeText(getApplicationContext(), user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();*/
               // Log.i("Movieinfoitem", "" + user1.getD().getMovieEff().size());
                //"1"
                if(user1.getD().getStatus().getStatusID().equals("1")){

                    txt_movietitle.setText(user1.getD().getLsFilmstrTitle());
                    txt_movielanguage.setText("["+user1.getD().getLsFilmStrLanguage()+"]");
                    txt_censor.setText("["+user1.getD().getLsFilmstrCensor()+"]");
                    txt_movietype.setText(user1.getD().getLsFilmCategoryName());
                    txt_synp_descrp.setText(user1.getD().getLsFilmstrDescriptionLong());
                    txt_duraion_descrption.setText(user1.getD().getLsFilmintDuration());
                    txt_cast_descrption.setText(user1.getD().getLsActors());

                    Glide.with(getApplicationContext()).load(user1.getD().getLsTrailerImageURL()).into(img_header);
                    Glide.with(getApplicationContext()).load(user1.getD().getLsBackgroundImageUrl()).into(img_favorite);

                    String liststring="";
                    if(user1.getD().getMovieEff()!=null) {
                        for (Movieff exper : user1.getD().getMovieEff()) {
                            liststring += exper.getLsEffect() + "\t";
                        }
                        txt_Eff.setText(liststring);
                    }else
                    {

                    }

                }else{
                    Toast.makeText(getApplicationContext(),user1.getD().getStatus().getStatusDescription(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                call.cancel();
            }
        });

    }

}
