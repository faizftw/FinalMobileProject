package com.example.finalmobileh071211069.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalmobileh071211069.R;
import com.example.finalmobileh071211069.activity.MovieDetail;
import com.example.finalmobileh071211069.activity.tvDetail;
import com.example.finalmobileh071211069.database.DatabaseHelper;
import com.example.finalmobileh071211069.movieModel.MovieResult;
import com.example.finalmobileh071211069.televModel.televResult;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {

    private Context context;
    private List<MovieResult> favoriteMovies;
    private List<televResult> favoriteTVShows;
    private DatabaseHelper databaseHelper;

    public FavAdapter(Context context) {
        this.context = context;
        favoriteMovies = new ArrayList<>();
        favoriteTVShows = new ArrayList<>();
        databaseHelper = new DatabaseHelper(context);
    }

    public void fetchFavoriteMovies() {
        favoriteMovies = databaseHelper.getFavoriteMovies();
        notifyDataSetChanged();
    }

    public void fetchFavoriteTVShows() {
        favoriteTVShows = databaseHelper.getFavoriteTVShows();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_fav, parent, false);
        databaseHelper = new DatabaseHelper(parent.getContext());
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        if (position < favoriteMovies.size()) {
            MovieResult movie = favoriteMovies.get(position);
            holder.bindMovie(movie);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MovieDetail.class);
                    intent.putExtra(MovieDetail.EXTRA_MOVIE, movie);
                    context.startActivity(intent);
                }
            });
        } else {
            int tvShowPosition = position - favoriteMovies.size();
            televResult tvShow = favoriteTVShows.get(tvShowPosition);
            holder.bindTVShow(tvShow);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, tvDetail.class);
                    intent.putExtra(tvDetail.EXTRA_TV_SHOW, tvShow);
                    context.startActivity(intent);
                }
            });
        }
    }





    @Override
    public int getItemCount() {
        return favoriteMovies.size() + favoriteTVShows.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView favImg;
        TextView favTitle;
        TextView fav_year;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);

            favImg = itemView.findViewById(R.id.favImg);
            favTitle = itemView.findViewById(R.id.fav_title);
            fav_year = itemView.findViewById(R.id.fav_year);
        }

        public void bindData(Object data) {
            if (data instanceof MovieResult) {
                bindMovie((MovieResult) data);
            } else if (data instanceof televResult) {
                bindTVShow((televResult) data);
            }
        }

        private void bindMovie(MovieResult movie) {
            favTitle.setText(movie.getTitle());
            String releaseDate = movie.getReleaseDate();
            if (releaseDate != null && !releaseDate.isEmpty()) {
                String year = releaseDate.substring(0, 4);
                fav_year.setText(year);
            } else {
                fav_year.setText("");
            }
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185" + movie.getPosterPath())
                    .into(favImg);
        }


        private void bindTVShow(televResult tvShow) {
            favTitle.setText(tvShow.getName());
            String releaseDate = tvShow.getFirstAirDate();
            if (releaseDate != null && !releaseDate.isEmpty()) {
                String year = releaseDate.substring(0, 4);
                fav_year.setText(year);
            } else {
                fav_year.setText("");
            }
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185" + tvShow.getPosterPath())
                    .into(favImg);
        }
    }
}
