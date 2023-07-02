package com.example.myappstore.model;

import android.content.Context;

import com.example.myappstore.JsonAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainCategoryRecyclerData {
    public static List<AllCategory> allCategoryList = new ArrayList<>();

    CategoryItemData data1 = new CategoryItemData();
    CategoryItemData data2 = new CategoryItemData();
    CategoryItemData data3 = new CategoryItemData();
    CategoryItemData data4 = new CategoryItemData();

    public List<AllCategory> mainBuildListData(){
        allCategoryList.clear();
        allCategoryList.add(new AllCategory("Еда и напитки", data1.categoryItemList(1)));
        allCategoryList.add(new AllCategory("Путешествия", data2.categoryItemListJourneys(2)));
        allCategoryList.add(new AllCategory("Изучение английского", data3.categoryItemListLearningEnglish(3)));
        allCategoryList.add(new AllCategory("Развлечения", data4.categoryItemListEntertainments(4)));
        return allCategoryList;
    }
}
