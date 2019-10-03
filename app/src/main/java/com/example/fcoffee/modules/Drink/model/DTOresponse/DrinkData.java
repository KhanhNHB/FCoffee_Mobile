package com.example.fcoffee.modules.Drink.model.DTOresponse;

import com.example.fcoffee.modules.Drink.model.DTOrequest.Drink;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrinkData {

    @SerializedName("data")
    @Expose
    private Drink mDrink;

    public DrinkData() {}

    public DrinkData(Drink mDrink) {
        this.mDrink = mDrink;
    }

    public Drink getmDrink() {
        return mDrink;
    }
}
