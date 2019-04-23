package com.example.ysi4ek.taskandroid.api;

import com.example.ysi4ek.taskandroid.model.RateList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/API/ExRates/Rates?Periodicity=0")
    Call<RateList> getMyJSON();

}
