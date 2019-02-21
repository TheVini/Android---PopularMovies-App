package com.example.viniciusgintern.popularmovies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.model.TrailerModel.Trailer;

import java.util.List;

public class TrailersListAdapter extends RecyclerView.Adapter<TrailersListAdapter.MyViewHolderForTrailer> {

    private List<Trailer> trailersList;

    public TrailersListAdapter(List<Trailer> trailersList){
        this.trailersList = trailersList;
    }

    @NonNull
    @Override
    public MyViewHolderForTrailer onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listItem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_list_trailers, viewGroup, false);

        return new TrailersListAdapter.MyViewHolderForTrailer(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderForTrailer myViewHolderForTrailer, int i) {
        Trailer trailer = trailersList.get(i);
        int trailerNumber = i+1;
        myViewHolderForTrailer.textTrailer.setText("Trailer " + trailerNumber);
    }

    @Override
    public int getItemCount() {
        return trailersList.size();
    }

    public class MyViewHolderForTrailer extends RecyclerView.ViewHolder{
        private TextView textTrailer;

        public MyViewHolderForTrailer(@NonNull View itemView) {
            super(itemView);
            textTrailer = itemView.findViewById(R.id.textTrailer);
        }
    }
}
