package com.example.myappstore.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappstore.R;
import com.example.myappstore.model.CategoryItem;
import com.example.myappstore.ui.TopFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TopItemRecyclerAdapter extends RecyclerView.Adapter<TopItemRecyclerAdapter.TopItemViewHolder>{

    private Context context;
    private List<CategoryItem> categoryItemList;
    private ItemClickListener clickListener;

    public TopItemRecyclerAdapter(ItemClickListener clickListener, Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
        this.clickListener = clickListener;
    }

    public interface ItemClickListener{
        void onItemClick(CategoryItem categoryItem);
    }

    @NonNull
    @Override
    public TopItemRecyclerAdapter.TopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItemView = LayoutInflater.from(context).inflate(R.layout.top_row_item, parent, false);
        TopItemRecyclerAdapter.TopItemViewHolder viewHolder = new TopItemRecyclerAdapter.TopItemViewHolder(categoryItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopItemRecyclerAdapter.TopItemViewHolder holder, int position) {
        holder.titleItem.setText(categoryItemList.get(position).getTitle());
        holder.rating.setText(String.valueOf(categoryItemList.get(position).getRating()));
        holder.numberItem.setText(String.valueOf(holder.getAdapterPosition()+1));

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

    public static final class TopItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem;
        TextView titleItem;
        TextView numberItem;
        TextView rating;

        public TopItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageItem = itemView.findViewById(R.id.top_image);
            titleItem = itemView.findViewById(R.id.top_title);
            numberItem = itemView.findViewById(R.id.top_number);
            rating = itemView.findViewById(R.id.top_rating);
        }
    }
}











/*
        AssetManager assetManager = context.getResources().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(filename);
            if ( inputStream != null) {
                // загружаем как Drawable
                Drawable d = Drawable.createFromStream(inputStream, null);
                // выводим картинку в ImageView
                holder.imageItem.setImageDrawable(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
 */

       /* String filename = categoryItemList.get(position).getImageUrl();
        try(InputStream inputStream = context.getApplicationContext().getAssets().open(filename)){
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.imageItem.setImageDrawable(drawable);
            //holder.imageItem.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        catch (IOException e){
            e.printStackTrace();
        } */