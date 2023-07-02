package com.example.myappstore.model;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("review")
    public String review;
    @SerializedName("idApp")
    public Integer idApp;

    public Review(){}

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getId() {
        return idApp;
    }

    public void setId(Integer idApp) {
        this.idApp = idApp;
    }

    public Review(String review, Integer id) {
        this.review = review;
        this.idApp = idApp;
    }
}
