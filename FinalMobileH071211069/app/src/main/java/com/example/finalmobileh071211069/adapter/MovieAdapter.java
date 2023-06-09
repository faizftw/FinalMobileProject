package com.example.finalmobileh071211069.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalmobileh071211069.R;
import com.example.finalmobileh071211069.movieModel.MovieResult;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MvViewHolder> {

    private Context context;
    private List<MovieResult> movieResultList;

    public MovieAdapter(Context context, List<MovieResult> movieResultList) {
        this.context = context;
        this.movieResultList = movieResultList;
    }



    @NonNull
    @Override
    public MovieAdapter.MvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_movie, parent, false );

        MovieAdapter.MvViewHolder viewHolder = new MovieAdapter.MvViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MvViewHolder holder, int position) {
        holder.mvTitle.setText(movieResultList.get(position).getTitle());
        String releaseDate = movieResultList.get(position).getReleaseDate();
        if (releaseDate != null && !releaseDate.isEmpty()) {
            String year = releaseDate.substring(0, 4);
            holder.mvYear.setText(year);
        } else {
            holder.mvYear.setText("");
        }
        Glide.with(context).load("https://image.tmdb.org/t/p/w185" + movieResultList.get(position).getPosterPath())
                .into(holder.mvPoster)
        ;
    }

    @Override
    public int getItemCount() {
        return movieResultList.size();
    }

    public class MvViewHolder extends RecyclerView.ViewHolder {

        ImageView mvPoster;
        TextView  mvTitle, mvYear;
        public MvViewHolder(@NonNull View itemView) {
            super(itemView);

            mvPoster = itemView.findViewById(R.id.movieImg);
            mvTitle = itemView.findViewById(R.id.movie_title);
            mvYear = itemView.findViewById(R.id.movie_year);
        }
    }
}
