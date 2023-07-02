package com.example.myappstore.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappstore.AppDecriptionActivity;
import com.example.myappstore.R;
import com.example.myappstore.model.CategoryItem;
import com.example.myappstore.model.Review;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    private Context context;
    private List<Review> categoryItemList;

    public ReviewAdapter(Context context, List<Review> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItemView = LayoutInflater.from(context).inflate(R.layout.review_row_item, parent, false);
        ReviewAdapter.ReviewViewHolder viewHolder = new ReviewAdapter.ReviewViewHolder(categoryItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        holder.review.setText(categoryItemList.get(position).getReview());

    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public static final class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView review;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            review = itemView.findViewById(R.id.review);
        }
    }
}