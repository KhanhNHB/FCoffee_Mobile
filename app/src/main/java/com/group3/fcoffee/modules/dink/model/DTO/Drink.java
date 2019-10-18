package com.group3.fcoffee.modules.dink.model.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Drink implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("drinkId")
    @Expose
    private int drinkId;

    public Drink() {}

    public Drink(int count, int drinkId) {
        this.count = count;
        this.drinkId = drinkId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }
}
