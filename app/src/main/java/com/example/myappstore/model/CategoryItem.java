package com.example.myappstore.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryItem {

    @SerializedName("idApps")
    public Integer idApps;
    String ImagePath;
    String AppName;
    String Price;
    @SerializedName("Rating")
    public double Rating;
    String Description;
    @SerializedName("Favorites")
    public Integer Favorites;

    public CategoryItem(Integer idApps, String ImagePath, String AppName, double Rating, String Description, Integer Favorites, String Price) {
        this.idApps = idApps;
        this.ImagePath = ImagePath;
        this.AppName = AppName;
        this.Rating = Rating;
        this.Description = Description;
        this.Favorites = Favorites;
        this.Price = Price;
    }
    public CategoryItem(){}

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public Integer getFavorites() {
        return Favorites;
    }

    public void setFavorites(Integer Favorites) {
        this.Favorites = Favorites;
    }

    public String getTitle() {
        return AppName;
    }

    public void setTitle(String AppName) {
        this.AppName = AppName;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double Rating) {
        this.Rating = Rating;
    }

    public Integer getItemId() {
        return idApps;
    }

    public void setItemId(Integer idApps) {
        this.idApps = idApps;
    }

    public String getImageUrl() {
        return ImagePath;
    }

    public void setImageUrl(String ImagePath) {
        this.ImagePath = ImagePath;
    }
}
