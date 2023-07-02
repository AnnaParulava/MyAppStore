package com.example.myappstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappstore.R;
import com.example.myappstore.model.AllCategory;
import com.example.myappstore.model.CategoryItem;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    private Context context;
    private List<AllCategory> allCategoryList;
    private ItemClickListener clickListener;

    public MainRecyclerAdapter(ItemClickListener clickListener, Context context, List<AllCategory> allCategoryList) {
        this.context = context;
        this.allCategoryList = allCategoryList;
        this.clickListener = clickListener;
    }

    public interface ItemClickListener{
        void onItemClick(CategoryItem categoryItem);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryView = LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.categoryTitle.setText(allCategoryList.get(position).getCategoryTitle());
        setCategoryItemRecycler(holder.itemRecycler, allCategoryList.get(position).getCategoryItemList());
    }

    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;
        RecyclerView itemRecycler;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.category_title);
            itemRecycler= itemView.findViewById(R.id.item_recycler);
        }
    }

    private void setCategoryItemRecycler(RecyclerView recyclerView, List<CategoryItem> categoryItemList){
        CategoryItemRecyclerAdapter itemRecyclerAdapter = new CategoryItemRecyclerAdapter(this.clickListener, context, categoryItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        itemRecyclerAdapter.notifyDataSetChanged();
       // recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.setAdapter(itemRecyclerAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        itemRecyclerAdapter.notifyDataSetChanged();
    }

}
