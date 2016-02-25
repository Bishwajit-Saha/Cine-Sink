package com.bishwajit.cinemalyticsbuzz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bishwajit on 12/17/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    ArrayList<MovieModel> movies;

    Context ctx;
    ImageLoader mImageLoader;
    public RecyclerAdapter(ArrayList<MovieModel> movies, Context ctx) {
        this.movies = movies;
        this.ctx = ctx;
    }




    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(view, ctx, movies);
        mImageLoader = VolleySingleton.getsInstance().getImageLoader();
        return rvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        MovieModel m = movies.get(position);
        String IMAGE_URL = m.getPosterPath();
        mImageLoader.get(IMAGE_URL, ImageLoader.getImageListener(holder.poster,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher));               // default image , error loading image
        holder.movieName.setText(m.getOriginalTitle());
        holder.genre.setText(m.getGenre());
        holder.rating.setText(m.getRating());

        holder.releaseDate.setText(m.getReleaseDate());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        ImageView poster;
        TextView movieName, genre, releaseDate, rating;
        ArrayList<MovieModel> movies= new ArrayList<MovieModel>();
        Context ctx;
        public RecyclerViewHolder(View itemView ,Context ctx, ArrayList<MovieModel> movies ) {
            super(itemView);
            this.movies = movies;
            itemView.setOnClickListener(this);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            movieName = (TextView) itemView.findViewById(R.id.movieName);
            genre = (TextView) itemView.findViewById(R.id.genre);
            releaseDate = (TextView) itemView.findViewById(R.id.releaseDate);
            rating = (TextView) itemView.findViewById(R.id.rating);
            this.ctx = ctx;

        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();
            MovieModel m =movies.get(p);
            Intent i = new Intent(ctx, Details.class);
            i.putExtra("Id", m.getId());
            i.putExtra("OriginalTitle", m.getOriginalTitle());
            i.putExtra("Description", m.getDescription());
            i.putExtra("Genre", m.getGenre());
            i.putExtra("Rating", m.getRating());
            i.putExtra("ReleaseDate", m.getReleaseDate());
            i.putExtra("Runtime", m.getRuntime());
            i.putExtra("PosterPath", m.getPosterPath());
            i.putExtra("TrailerLink", m.getTrailerLink());
            this.ctx.startActivity(i);
        }
    }
}

