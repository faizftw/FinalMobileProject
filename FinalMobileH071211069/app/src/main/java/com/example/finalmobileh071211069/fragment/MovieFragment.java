package com.example.finalmobileh071211069.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalmobileh071211069.R;
import com.example.finalmobileh071211069.adapter.MovieAdapter;
import com.example.finalmobileh071211069.movieModel.MovieResponse;
import com.example.finalmobileh071211069.movieModel.MovieResult;
import com.example.finalmobileh071211069.rest.ApiClient;
import com.example.finalmobileh071211069.rest.MovInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieFragment extends Fragment {

    private MovieAdapter adapter;
    String API_KEY = "90b2e587b561bff3f68bb55ce02acaa9";
    String LANGUAGE = "en-US";
    String CATEGORY = "now_playing";
    int PAGE = 1;

    private boolean isLoading = false;

    Context context;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.MovieRV);
        recyclerView.setHasFixedSize(true);

        context = requireContext();

        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        CallRetrofit();
    }


    private void CallRetrofit() {
        MovInterface movInterface = ApiClient.getClient().create(MovInterface.class);
        Call<MovieResponse> call = movInterface.getMovie(CATEGORY, API_KEY, LANGUAGE, PAGE);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MovieResult> mList = response.body().getResults();
                    adapter = new MovieAdapter(getContext(), mList);
                    recyclerView.setAdapter(adapter);
                } else {
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    PAGE++;
                    isLoading = true;
                    loadMoreData();
                }
            }
        });
    }
    private void loadMoreData() {
        MovInterface movInterface = ApiClient.getClient().create(MovInterface.class);
        Call<MovieResponse> call = movInterface.getMovie(CATEGORY, API_KEY, LANGUAGE, PAGE);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MovieResult> newList = response.body().getResults();
                    adapter.addMovies(newList);
                    isLoading = false;
                } else {

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
            }
        });
    }

}