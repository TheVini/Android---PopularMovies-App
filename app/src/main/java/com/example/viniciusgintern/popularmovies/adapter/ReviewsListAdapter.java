package com.example.viniciusgintern.popularmovies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viniciusgintern.popularmovies.R;
import com.example.viniciusgintern.popularmovies.model.ReviewModel.Review;
import com.example.viniciusgintern.popularmovies.model.TrailerModel.Trailer;

import java.util.ArrayList;
import java.util.List;

public class ReviewsListAdapter extends RecyclerView.Adapter<ReviewsListAdapter.MyViewHolderForReview> {

    private List<Review> reviewsList;

    public ReviewsListAdapter(List<Review> reviewsList){
        this.reviewsList = reviewsList;
    }

    public ReviewsListAdapter() {
        Review noReview = new Review("", "","No ID", "NoURL");

        List<Review> reviewList = new ArrayList<Review>();
        reviewList.add(noReview);
        this.reviewsList = reviewList;
    }

    @NonNull
    @Override
    public MyViewHolderForReview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listItem = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_list_reviews, viewGroup, false);

        return new ReviewsListAdapter.MyViewHolderForReview(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderForReview myViewHolderForReview, int i) {
        Review review = reviewsList.get(i);
        if(review.getAuthor().length() != 0)
            myViewHolderForReview.authorName.setText("Author: " + review.getAuthor());
            myViewHolderForReview.textReview.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public class MyViewHolderForReview extends RecyclerView.ViewHolder{
        private TextView authorName;
        private TextView textReview;

        public MyViewHolderForReview(@NonNull View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.authorName);
            textReview = itemView.findViewById(R.id.textReview);
        }
    }
}
