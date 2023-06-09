package com.example.finalmobileh071211069.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalmobileh071211069.R;

public class MovieDetail extends AppCompatActivity {

    ImageView movLogo,movProf,movBp,movFav,movBackbt;
    TextView movtitle,movDate,movrate,movdesc;

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
    }
}