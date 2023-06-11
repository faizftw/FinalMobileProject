package com.example.finalmobileh071211069.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalmobileh071211069.R;
import com.example.finalmobileh071211069.activity.MovieDetail;
import com.example.finalmobileh071211069.activity.tvDetail;
import com.example.finalmobileh071211069.movieModel.MovieResult;
import com.example.finalmobileh071211069.televModel.televResult;

import java.util.List;

public class televAdapter extends RecyclerView.Adapter<televAdapter.televViewHolder> {

    private Context context;
    private List<televResult> televResultList;

    public televAdapter(Context context, List<televResult> televResultList) {
        this.context = context;
        this.televResultList = televResultList;
    }

    public void addTelevShows(List<televResult> newTelevShows) {
        int startPos = televResultList.size();
        televResultList.addAll(newTelevShows);
        notifyItemRangeInserted(startPos, newTelevShows.size());
    }



    @NonNull
    @Override
    public televAdapter.televViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_telev, parent, false );
        televAdapter.televViewHolder viewHolder = new televAdapter.televViewHolder(view);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(), tvDetail.class);
                televResult televResult = new televResult();
                televResult.setName(televResultList.get(viewHolder.getAdapterPosition()).getName());
                televResult.setOverview(televResultList.get(viewHolder.getAdapterPosition()).getOverview());
                televResult.setBackdropPath(televResultList.get(viewHolder.getAdapterPosition()).getBackdropPath());
                televResult.setVoteAverage(televResultList.get(viewHolder.getAdapterPosition()).getVoteAverage());
                televResult.setFirstAirDate(televResultList.get(viewHolder.getAdapterPosition()).getFirstAirDate());
                televResult.setPosterPath(televResultList.get(viewHolder.getAdapterPosition()).getPosterPath());
                intent.putExtra(tvDetail.EXTRA_TV_SHOW, televResult);
                parent.getContext().startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull televAdapter.televViewHolder holder, int position) {
        holder.tvTitle.setText(televResultList.get(position).getName());
        String releaseDate = televResultList.get(position).getFirstAirDate();
        if (releaseDate != null && !releaseDate.isEmpty()) {
            String year = releaseDate.substring(0, 4);
            holder.tvYear.setText(year);
        } else {
            holder.tvYear.setText("");
        }
        Glide.with(context).load("https://image.tmdb.org/t/p/w185" + televResultList.get(position).getPosterPath())
                .into(holder.tvPoster)
        ;
    }

    @Override
    public int getItemCount() {
        return televResultList.size();
    }

    public class televViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView tvPoster;
        TextView tvTitle,tvYear;

        public televViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.televLayout);
            tvPoster = itemView.findViewById(R.id.televImg);
            tvTitle = itemView.findViewById(R.id.telev_title);
            tvYear = itemView.findViewById(R.id.telev_year);
        }


    }
}
