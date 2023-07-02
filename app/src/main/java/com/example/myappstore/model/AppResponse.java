package com.example.myappstore.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class AppResponse {
    @Nullable
    @SerializedName("state")
    private String state;

    @Nullable
    @SerializedName("token")
    private String token;
}
