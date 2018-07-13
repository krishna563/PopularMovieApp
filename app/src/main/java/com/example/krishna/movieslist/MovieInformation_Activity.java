package com.example.krishna.movieslist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.krishna.movieslist.Webservice.RetrofitInterface_Activity;
import com.example.krishna.movieslist.moviemodal.WebResponse.Resp_D;
import com.example.krishna.movieslist.moviemodal.WebResponse.results;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieInformation_Activity extends AppCompatActivity {

    ProgressDialog progressDialog;
    ImageView Title_Image;
    TextView txt_Title, txt_Rating, txt_calendar, txt_People, Youtube_Share, Plot_Synopsis, txt_review;
    FloatingActionButton FavourList;
    LinearLayout Youtube_Layout;
    HorizontalScrollView YoutubeHolder;
    private List<results> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_movieinformation);
        Title_Image = (ImageView) findViewById(R.id.title_image);

        txt_Title = (TextView) findViewById(R.id.txt_title);
        txt_Rating = (TextView) findViewById(R.id.txt_rating);
        txt_calendar = (TextView) findViewById(R.id.txt_calendar);
        txt_People = (TextView) findViewById(R.id.txt_people);
        Youtube_Share = (TextView) findViewById(R.id.YoutubeShare);
        Plot_Synopsis = (TextView) findViewById(R.id.plot_synopsis);
        txt_review = (TextView) findViewById(R.id.txt_review);

        Youtube_Layout = (LinearLayout) findViewById(R.id.Youtubelayout);
        FavourList = (FloatingActionButton) findViewById(R.id.favour);


       /* long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd");
        String dateString = sdf.format(date);
        txt_calendar.setText(dateString);*/

      /*  String dt;
        Date cal = (Date) Calendar.getInstance().getTime();
        dt = cal.toLocaleString();
        txt_calendar.setText(dt.toString());*/

        Youtube_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieInformation_Activity.this, Youtubeplayer_Activity.class);
                startActivity(intent);
            }
        });

        FavourList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieInformation_Activity.this, MoviePayment_Activity.class);
                startActivity(intent);
            }
        });




        Intent i = getIntent();
        results dene = (results)i.getSerializableExtra("sampleObject");
        //txt_Title.setText(dene.getTitle());
        //String stuff = getIntent().getStringExtra("MovieID_stuff");
        MovieInformation(dene);


        txt_Title.setText(dene.getTitle());


        txt_Rating.setText(dene.getVote_average());
        txt_calendar.setText(dene.getRelease_date());

        Log.i("title", dene.getOriginal_language());
        txt_People.setText(dene.getVote_count());
        Plot_Synopsis.setText(dene.getOverview());
        txt_review.setText(dene.getOriginal_language());
        Glide.with(getApplicationContext()).load(dene.getThumbnail()).into(Title_Image);

    }

    private void MovieInformation(final results dene) {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);

        RetrofitInterface_Activity.APIInterface apiInterface = RetrofitInterface_Activity.getClient();
        //final CinemaRequest user = new CinemaRequest("100", stuff);
        Call<Resp_D> call1 = apiInterface.getPositionByZip("662ead3eba1787b9660e89194452cde1","en-US");

        progressDialog.dismiss();
        //Call<MovieInfoResponse> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<Resp_D>() {
            @Override
            public void onResponse(Call<Resp_D> call, Response<Resp_D> response) {

                Resp_D user = response.body();



                // progressDialog.dismiss();
                //Log.i("bfid", user.getResults().size()+"");



                    if (!user.getResults().get(1).getId().equals(dene)) {
                        //Log.i("bfid", user.getResults().get(1).getTitle());




                       /* txt_Title.setText(dene.getTitle());
                        txt_Rating.setText(dene.getVote_average());
                        Glide.with(getApplicationContext()).load(dene.getPoster_path()).into(Title_Image);
                        txt_calendar.setText(dene.getRelease_date());
                        txt_People.setText(dene.getVote_count());
                        Plot_Synopsis.setText(dene.getOverview());
                        txt_review.setText(dene.getOriginal_language());
*/

                    }


            }




            @Override
            public void onFailure(Call<Resp_D> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }


}
