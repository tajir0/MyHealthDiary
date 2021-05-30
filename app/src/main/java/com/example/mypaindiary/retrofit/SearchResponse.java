package com.example.mypaindiary.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResponse {
    //No need to map all keys, only those the fields you need
    @SerializedName("items")
    public List<Items> items;
    public List<Items> getItems() {
        return items;
    }
    public void setItems(List<Items> items) {
        this.items = items;
    }
}