package com.example.myappstore.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.myappstore.AppDecriptionActivity;
import com.example.myappstore.JsonAPI;
import com.example.myappstore.R;
import com.example.myappstore.adapter.CategoryItemRecyclerAdapter;
import com.example.myappstore.adapter.MainRecyclerAdapter;
import com.example.myappstore.adapter.TopItemRecyclerAdapter;
import com.example.myappstore.model.AllCategory;
import com.example.myappstore.model.CategoryItem;
import com.example.myappstore.model.CategoryItemData;
import com.example.myappstore.model.MainCategoryRecyclerData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppsFragment extends Fragment implements MainRecyclerAdapter.ItemClickListener{

    MainRecyclerAdapter adapter;
    private View view;
    RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private SearchView searchView;

    public AppsFragment() {
        // Required empty public constructor
    }

    public static AppsFragment newInstance() {
        AppsFragment fragment = new AppsFragment();
        return fragment;
    }

    public static AppsFragment newInstance(String param1, String param2) {
        AppsFragment fragment = new AppsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyclerView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecyclerView(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_apps, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view){
        recyclerView = view.findViewById(R.id.main_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        MainCategoryRecyclerData data = new MainCategoryRecyclerData();
        adapter = new MainRecyclerAdapter(this, getActivity(), data.mainBuildListData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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