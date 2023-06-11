package com.example.finalmobileh071211069.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalmobileh071211069.R;
import com.example.finalmobileh071211069.adapter.FavAdapter;
import com.example.finalmobileh071211069.database.DatabaseHelper;
import com.example.finalmobileh071211069.movieModel.MovieResult;
import com.example.finalmobileh071211069.televModel.televResult;

import java.util.List;

public class FavFragment extends Fragment {

    private RecyclerView favRV;
    private FavAdapter favAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav, container, false);

        favRV = view.findViewById(R.id.favRV);
        favRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
        favAdapter = new FavAdapter(getContext());
        favRV.setAdapter(favAdapter);

        databaseHelper = new DatabaseHelper(getContext());

        favAdapter.fetchFavoriteMovies();
        favAdapter.fetchFavoriteTVShows();

        return view;
    }
}
