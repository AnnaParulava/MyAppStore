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

import com.example.myappstore.R;
import com.example.myappstore.model.CategoryItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProfileItemRecyclerAdapter extends RecyclerView.Adapter<ProfileItemRecyclerAdapter.ProfileItemViewHolder>{
    private Context context;
    private List<CategoryItem> categoryItemList;
    private ItemClickListener clickListener;

    public ProfileItemRecyclerAdapter(ItemClickListener clickListener, Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
        this.clickListener = clickListener;
    }

    public interface ItemClickListener{
        void onItemClick(CategoryItem categoryItem);
    }

    @NonNull
    @Override
    public ProfileItemRecyclerAdapter.ProfileItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItemView = LayoutInflater.from(context).inflate(R.layout.profile_row_item, parent, false);
        ProfileItemRecyclerAdapter.ProfileItemViewHolder viewHolder = new ProfileItemRecyclerAdapter.ProfileItemViewHolder(categoryItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileItemRecyclerAdapter.ProfileItemViewHolder holder, int position) {
        holder.titleItem.setText(categoryItemList.get(position).getTitle());
        holder.rating.setText(String.valueOf(categoryItemList.get(position).getRating()));
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

    public static final class ProfileItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem;
        TextView titleItem;
        TextView rating;

        public ProfileItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageItem = itemView.findViewById(R.id.profile_image);
            titleItem = itemView.findViewById(R.id.profile_title);
            rating = itemView.findViewById(R.id.profile_rating);
        }
    }
}
