package com.example.myappstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappstore.R;
import com.example.myappstore.model.AllCategory;
import com.example.myappstore.model.CategoryItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder>{

    private Context context;
    private List<CategoryItem> categoryItemList;
    private MainRecyclerAdapter.ItemClickListener clickListener;

    public CategoryItemRecyclerAdapter(MainRecyclerAdapter.ItemClickListener clickListener, Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItemView = LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false);
        CategoryItemRecyclerAdapter.CategoryItemViewHolder viewHolder = new CategoryItemRecyclerAdapter.CategoryItemViewHolder(categoryItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        holder.titleItem.setText(categoryItemList.get(position).getTitle());
        holder.ratingItem.setText(String.valueOf(categoryItemList.get(position).getRating()));

        String filename = categoryItemList.get(position).getImageUrl();

        try(InputStream inputStream = context.getApplicationContext().getAssets().open(filename)){
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.imageItem.setImageDrawable(drawable);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(categoryItemList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public static final class CategoryItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem;
        TextView titleItem;
        TextView ratingItem;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageItem = itemView.findViewById(R.id.item_image);
            titleItem = itemView.findViewById(R.id.item_title);
            ratingItem = itemView.findViewById(R.id.item_rating);
        }
    }
}
