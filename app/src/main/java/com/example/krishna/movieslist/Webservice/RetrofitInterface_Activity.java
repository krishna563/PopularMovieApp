package com.example.krishna.movieslist.Webservice;

import com.example.krishna.movieslist.Class.TLSSocketFactory;
import com.example.krishna.movieslist.moviemodal.WebRequest.CinemaRequest;
import com.example.krishna.movieslist.moviemodal.WebRequest.MovieInfoRequest;
import com.example.krishna.movieslist.moviemodal.WebResponse.CinemaResponse;
import com.example.krishna.movieslist.moviemodal.WebRequest.MovieRequest;
import com.example.krishna.movieslist.moviemodal.WebResponse.MovieInfoResponse;
import com.example.krishna.movieslist.moviemodal.WebResponse.MovieResponse;
import com.example.krishna.movieslist.moviemodal.WebResponse.Resp_D;
import com.example.krishna.movieslist.moviemodal.WebResponse.Resp_Popular;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RetrofitInterface_Activity {

    //static String str_url="https://mweb.jazzcinemas.com/DataserviceJson.asmx/";
    static String str_url="https://api.themoviedb.org";
    private static APIInterface apiInterface;

    public static APIInterface getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient1 = null;
        try {
            okHttpClient1 = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .sslSocketFactory(new TLSSocketFactory())
                    .addInterceptor(interceptor)
                    .build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //JSONObject jsonMenu = jsonParser.makeHttpRequest(str_url + postevent, "GET", params);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(str_url)
                .client(okHttpClient1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface=retrofit.create(APIInterface.class);
        return apiInterface;
    }

   public interface APIInterface{
       /* @POST("GetMovieList_ByType")
        Call<CinemaResponse> createUser(@Body CinemaRequest user);*/


       @GET("/3/discover/movie")
       Call<Resp_D> getPositionByZip(@Query("api_key") String keyValue, @Query("language") String language);

       @GET("/3/movie/top_rated")
       Call<Resp_D> getPosition(@Query("api_key") String keyValue, @Query("language") String language);





       @POST("GetMovieInformation_ByMovieCS")
        Call<MovieResponse> createUser(@Body MovieRequest user);

       @POST("GetMovieInformation_ByMovie")
       Call<MovieInfoResponse> createUser(@Body MovieInfoRequest user);
    }
}

