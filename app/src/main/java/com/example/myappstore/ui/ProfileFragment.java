package com.example.myappstore.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappstore.AppDecriptionActivity;
import com.example.myappstore.JsonAPI;
import com.example.myappstore.R;
import com.example.myappstore.adapter.ProfileItemRecyclerAdapter;
import com.example.myappstore.adapter.TopItemRecyclerAdapter;
import com.example.myappstore.model.CategoryItem;
import com.example.myappstore.model.CategoryItemData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment implements ProfileItemRecyclerAdapter.ItemClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ArrayList<CategoryItem> posts;

    private View view;
    ProfileItemRecyclerAdapter adapter;
    RecyclerView recyclerView;

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(CategoryItemData.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonAPI json = retrofit.create(JsonAPI.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Call<List<CategoryItem>> call = json.getFavorite();

        call.enqueue(new Callback<List<CategoryItem>>() {

            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Error: " + response.code());
                    return;
                }
                posts = (ArrayList<CategoryItem>) response.body();
                initRecyclerView(view, posts);
            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);

        Call<List<CategoryItem>> call = json.getFavorite();

        call.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Error: " + response.code());
                    return;
                }
                posts = (ArrayList<CategoryItem>) response.body();
                initRecyclerView(view, posts);
            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
        return view;
    }

    private void initRecyclerView(View view, ArrayList<CategoryItem> categoryItemList){
        recyclerView = view.findViewById(R.id.favorite_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProfileItemRecyclerAdapter(this, getActivity(), categoryItemList);
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemClick(CategoryItem categoryItem) {
        ///переход на активность
        Intent intent = new Intent(getActivity(), AppDecriptionActivity.class);
        intent.putExtra("item_id", categoryItem.getItemId());
        intent.putExtra("Favorites", categoryItem.getFavorites());
        intent.putExtra("ImagePath", categoryItem.getImageUrl());
        intent.putExtra("AppName", categoryItem.getTitle());
        intent.putExtra("Description", categoryItem.getDescription());
        intent.putExtra("Price", categoryItem.getPrice());
        startActivity(intent);
    }
}