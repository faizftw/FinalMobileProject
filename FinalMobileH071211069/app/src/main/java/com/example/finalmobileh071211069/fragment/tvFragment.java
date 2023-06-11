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


import com.example.finalmobileh071211069.adapter.televAdapter;
import com.example.finalmobileh071211069.rest.ApiClient;
import com.example.finalmobileh071211069.rest.televInterface;
import com.example.finalmobileh071211069.televModel.televResponse;
import com.example.finalmobileh071211069.televModel.televResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tvFragment extends Fragment {

    private televAdapter adapter;
    String API_KEY = "90b2e587b561bff3f68bb55ce02acaa9";
    String LANGUAGE = "en-US";
    String CATEGORY = "airing_today";
    int PAGE = 1;

    Context context;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.televRV);
        recyclerView.setHasFixedSize(true);

        context = requireContext();

        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        CallRetrofit();
    }


    private void CallRetrofit() {
        televInterface televInterface = ApiClient.getClient().create(televInterface.class);
        Call<televResponse> call = televInterface.getTelev(CATEGORY, API_KEY, LANGUAGE, PAGE);
        call.enqueue(new Callback<televResponse>() {
            @Override
            public void onResponse(Call<televResponse> call, Response<televResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<televResult> tList = response.body().getResults();
                    adapter = new televAdapter(getContext(), tList);
                    recyclerView.setAdapter(adapter);
                } else {
                }
            }

            @Override
            public void onFailure(Call<televResponse> call, Throwable t) {
            }
        });
    }
}