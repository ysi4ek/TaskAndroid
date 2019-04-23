package com.example.ysi4ek.taskandroid.api;

import com.example.ysi4ek.taskandroid.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String ROOT_URL = "http://www.nbrb.by/API/ExRates/Rates?Periodicity=0";


    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static ApiService getApiSevice(){
        return getRetrofitInstance().create(ApiService.class);
    }
}
