package com.example.ysi4ek.taskandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RateList {

    @SerializedName("rates")
    @Expose
    private ArrayList<Rates> rates = new ArrayList<>();


    public ArrayList<Rates> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rates> rates) {
        this.rates = rates;
    }
}
