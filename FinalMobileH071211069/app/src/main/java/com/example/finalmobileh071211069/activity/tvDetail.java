package com.example.finalmobileh071211069.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalmobileh071211069.R;
import com.example.finalmobileh071211069.movieModel.MovieResult;
import com.example.finalmobileh071211069.televModel.televResult;

import java.util.Objects;

public class tvDetail extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    String televTitle,televDate,televDesc,televPp,televBp;
    Double televRate;
    ImageView tvLogo,tvProf,tvBp,tvFav,tvBackbt;
    TextView tvtitle,tvDate,tvrate,tvdesc;
    televResult tvresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        tvLogo = findViewById(R.id.telev_logo);
        tvProf = findViewById(R.id.telev_Poster);
        tvBp = findViewById(R.id.telev_bdrop);
        tvFav= findViewById(R.id.telev_favbt);
        tvBackbt= findViewById(R.id.telev_backbt);

        tvtitle = findViewById(R.id.telev_title);
        tvDate = findViewById(R.id.telev_date);
        tvrate= findViewById(R.id.telev_rate);
        tvdesc= findViewById(R.id.telev_desc);

        tvresult = getIntent().getParcelableExtra(EXTRA_MOVIE);
        televTitle = tvresult.getOriginalName();
        televDesc = tvresult.getOverview();
        televDate = tvresult.getFirstAirDate();
        televRate = tvresult.getVoteAverage();
        televPp = tvresult.getPosterPath();
        televBp = tvresult.getBackdropPath();

        tvtitle.setText(televTitle);
        tvDate.setText(televDate);
        tvrate.setText(String.format(televRate.toString()));
        tvdesc.setText(televDesc);

        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w185"+ televPp)
                .into(tvProf);
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/original"+ televBp)
                .into(tvBp);

        tvFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((tvFav.getDrawable().getConstantState() == Objects.requireNonNull(ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_favorite_border_24, null)).getConstantState())){
                    tvFav.setImageResource(R.drawable.baseline_favorite_24);
                    Toast.makeText(tvDetail.this, televTitle + " added to favorites", Toast.LENGTH_SHORT).show();
                }else{
                    tvFav.setImageResource(R.drawable.baseline_favorite_border_24);
                    Toast.makeText(tvDetail.this, televTitle + " removed from favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvBackbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}