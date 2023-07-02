package com.example.myappstore;

import com.example.myappstore.model.AppResponse;
import com.example.myappstore.model.CategoryItem;
import com.example.myappstore.model.Review;

import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonAPI {
    @GET("appinfo")
    Call<List<CategoryItem>> getPosts();

    @GET("appinfo/{id}")
    Call<List<CategoryItem>> getCategory(@Path("id") Integer id);

    @GET("favorite")
    Call<List<CategoryItem>> getFavorite();

    @GET("reviews/{id}")
    Call<List<Review>> getReview(@Path("id") Integer id);

    @PUT("change_rtng/{id}")
    Call<AppResponse> changeRating(@Path("id") Integer id, @Body CategoryItem rating);

    @PUT("change_fav/{id}")
    Call<AppResponse> changeFavorite(@Path("id") Integer id, @Body CategoryItem favorite);

    @POST("add_review/{id}")
    Call<AppResponse> addReview(@Path("id") Integer id, @Body Review review);
}

