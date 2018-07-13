package com.example.krishna.movieslist;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.krishna.movieslist.Adapter.MoviesAdapter_Activity;
import com.example.krishna.movieslist.Webservice.RetrofitInterface_Activity;
import com.example.krishna.movieslist.moviemodal.WebRequest.CinemaRequest;
import com.example.krishna.movieslist.moviemodal.WebResponse.Resp_D;
import com.example.krishna.movieslist.moviemodal.WebResponse.Resp_Popular;
import com.example.krishna.movieslist.moviemodal.WebResponse.results;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieList_Activity extends AppCompatActivity {

    RetrofitInterface_Activity apiInterface;
    ProgressDialog progressDialog;
    String strmovietype = "HighestRated";
    private RecyclerView recyclerView;
    private MoviesAdapter_Activity adapter;
    private List<results> movieList;
    private Button btn_Event;
    private Toolbar tool_Bar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);

        recyclerView = (RecyclerView) findViewById(R.id.recyler_movielist);


        //tool_Bar.inflateMenu(R.menu.main);

        // dont create new adapter , but instead use notify dataset change

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);

            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        } else {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
            recyclerView.setLayoutManager(mLayoutManager);

            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }


        /*Toast.makeText(getApplicationContext(),"Hello krishna",Toast.LENGTH_SHORT).show();*/

        /*prepareAlbums();*/



        if(strmovietype!=null){
            CineField();
        }else{}
        CineField1();
    }


    private void CineField() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(true);

        RetrofitInterface_Activity.APIInterface apiInterface = RetrofitInterface_Activity.getClient();

       // CinemaRequest user = new CinemaRequest("662ead3eba1787b9660e89194452cde1", strmovietype);
        /*final CinemaRequest user = new CinemaRequest("100", "cs");*/

        Call<Resp_D> call1 = apiInterface.getPositionByZip("662ead3eba1787b9660e89194452cde1","en-US");
        //Call<Resp_Popular> cal = apiInterface.getPosition("662ead3eba1787b9660e89194452cde1","en-US");
        progressDialog.dismiss();

        call1.enqueue(new Callback<Resp_D>() {
            @Override
            public void onResponse(Call<Resp_D> call, Response<Resp_D> response) {
                //progressDialog.dismiss();
                Resp_D user = response.body();



                    movieList = new ArrayList<>();
                   /* results results;
                    for (int i = 0; i < user.getResults().size(); i++) {
                        if (!user.getResults().get(i).getId().equals("")) {
                            results = new results();
                            results.setTitle(user.getResults().get(i).getTitle());
                            results.setPoster_path(user.getResults().get(i).getPoster_path());
                            results.setVote_average(user.getResults().get(i).getVote_average());
                            movieList.add(results);
                        }
                    }*/
                    /*recyclerView.setAdapter(adapter);*/

                    movieList = user.getResults();


                    recyclerView.setHasFixedSize(true);
                    adapter = new MoviesAdapter_Activity(MovieList_Activity.this, movieList);
                    recyclerView.setAdapter(adapter);


                }


            @Override
            public void onFailure(Call<Resp_D> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }

    private void CineField1() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(true);


        RetrofitInterface_Activity.APIInterface apiInterface = RetrofitInterface_Activity.getClient();
        // CinemaRequest user = new CinemaRequest("662ead3eba1787b9660e89194452cde1", strmovietype);
        /*final CinemaRequest user = new CinemaRequest("100", "cs");*/

        Call<Resp_D> call1 = apiInterface.getPosition("662ead3eba1787b9660e89194452cde1","en-US");
        //Call<Resp_Popular> cal = apiInterface.getPosition("662ead3eba1787b9660e89194452cde1","en-US");
        progressDialog.dismiss();


        call1.enqueue(new Callback<Resp_D>() {
            @Override
            public void onResponse(Call<Resp_D> call, Response<Resp_D> response) {

                Resp_D user1 = response.body();

                movieList = new ArrayList<>();
                movieList = user1.getResults();


                recyclerView.setHasFixedSize(true);
                adapter = new MoviesAdapter_Activity(MovieList_Activity.this, movieList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Resp_D> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.searchmenu)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.popular:
                strmovietype = "MostPopular";
                CineField();
                // Toast.makeText(getApplicationContext(),"coming soon not allowed",Toast.LENGTH_LONG).show();
                return true;
            case R.id.highRate:
                strmovietype = "HighestRated";
                CineField1();
                return true;
            case R.id.searchmenu:
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MovieList_Activity.this, Login_Activity.class);
        startActivity(i);
        finish();
    }*/
}

