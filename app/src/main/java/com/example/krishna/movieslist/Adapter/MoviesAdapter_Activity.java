package com.example.krishna.movieslist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Filter;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.krishna.movieslist.MovieInformation_Activity;
import com.example.krishna.movieslist.R;
import com.example.krishna.movieslist.moviemodal.WebResponse.Movieinfoitem;
import com.example.krishna.movieslist.moviemodal.WebResponse.results;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter_Activity extends RecyclerView.Adapter<MoviesAdapter_Activity.MovieHolder> implements Filterable {

    private Context mContext;
    private List<results> movieList;
    private List<results> contactListFiltered;
    private ContactsAdapterListener listener;


    public MoviesAdapter_Activity(Context mContext,List<results> movieList) {
        this.mContext = mContext;
        this.listener = listener;
        this.movieList = movieList;
        this.contactListFiltered = movieList;

    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_moviecard, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, final int position) {
        final results Movieload = contactListFiltered.get(position);
        //Log.w("Moviewid", "onBindViewHolder: " + Movieload.getMovie_strId());

            holder.title.setText(Movieload.getTitle());
            holder.vote.setText(Movieload.getVote_average());
            Glide.with(mContext).load(Movieload.getThumbnail()).into(holder.thumbnail);

             Log.i("check the image",Movieload.getThumbnail());


            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(mContext, MovieInformation_Activity.class);
                    i.putExtra("sampleObject", Movieload);
                    mContext.startActivity(i);


                  //  Intent intent = new Intent(mContext, MovieInformation_Activity.class);
                    //intent.putExtra("MovieID_stuff", position);



                    //intent.putExtra("Movie_videoURL",Movieload.getVideoUrl());
                   // mContext.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = movieList;
                } else {
                    List<results> filteredList = new ArrayList<>();
                    for (results row : movieList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<results>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Movieinfoitem Movieinfoitem);
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        public TextView title, vote;
        public ImageView thumbnail;


        public MovieHolder(View View) {
            super(View);

            title = (TextView) View.findViewById(R.id.movietitle);
            vote = (TextView) View.findViewById(R.id.movievote);
            thumbnail = (ImageView) View.findViewById(R.id.moviePhoto);

           /* View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });*/


        }
    }
}
