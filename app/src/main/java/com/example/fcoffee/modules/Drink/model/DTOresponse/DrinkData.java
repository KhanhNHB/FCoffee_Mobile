package com.example.fcoffee.modules.Drink.model.DTOresponse;

import com.example.fcoffee.modules.Drink.model.DTOrequest.DTODrink;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrinkData {

    @SerializedName("data")
    @Expose
    private List<DTODrink> mDrink;

    public DrinkData() {}

    public DrinkData(List<DTODrink> mDrink) {
        this.mDrink = mDrink;
    }

    public List<DTODrink> getmDrink() {
        return mDrink;
    }
}
