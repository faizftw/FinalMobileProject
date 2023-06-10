package com.example.finalmobileh071211069.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalmobileh071211069.R;
import com.example.finalmobileh071211069.fragment.MovieFragment;
import com.example.finalmobileh071211069.movieModel.MovieResult;

import java.util.Objects;

public class MovieDetail extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    String MovieTitle,MovieDate,MovieDesc,MoviePp,MovieBp;
    Double MovieRate;
    ImageView movLogo,movProf,movBp,movFav,movBackbt;
    TextView movtitle,movDate,movrate,movdesc;
    MovieResult movieResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        movLogo = findViewById(R.id.mov_logo);
        movProf = findViewById(R.id.Mov_Poster);
        movBp = findViewById(R.id.mov_bdrop);
        movFav= findViewById(R.id.mov_favbt);
        movBackbt= findViewById(R.id.mov_backbt);

        movtitle = findViewById(R.id.mov_title);
        movDate = findViewById(R.id.mov_date);
        movrate= findViewById(R.id.move_rate);
        movdesc= findViewById(R.id.mov_desc);

        movieResult = getIntent().getParcelableExtra(EXTRA_MOVIE);
        MovieTitle = movieResult.getOriginalTitle();
        MovieDesc = movieResult.getOverview();
        MovieDate = movieResult.getReleaseDate();
        MovieRate = movieResult.getVoteAverage();
        MoviePp = movieResult.getPosterPath();
        MovieBp = movieResult.getBackdropPath();

        movtitle.setText(MovieTitle);
        movDate.setText(MovieDate);
        movrate.setText(String.format(MovieRate.toString()));
        movdesc.setText(MovieDesc);

        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w185"+ MoviePp)
                .into(movProf);
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/original"+ MovieBp)
                .into(movBp);

        movFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((movFav.getDrawable().getConstantState() == Objects.requireNonNull(ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_favorite_border_24, null)).getConstantState())){
                    movFav.setImageResource(R.drawable.baseline_favorite_24);
                    Toast.makeText(MovieDetail.this, MovieTitle + " added to favorites", Toast.LENGTH_SHORT).show();
                }else{
                    movFav.setImageResource(R.drawable.baseline_favorite_border_24);
                    Toast.makeText(MovieDetail.this, MovieTitle + " removed from favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        movBackbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}