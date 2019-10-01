package com.example.fcoffee.modules.Table.model;

import com.example.fcoffee.modules.Drink.model.Drink;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TableRequestBodyModel implements Serializable {

    @SerializedName("listDrink")
    @Expose
    private List<Drink> mDrinks;

    @SerializedName("tableNumber")
    @Expose
    private int tableNumber;

    public TableRequestBodyModel() {}

    public TableRequestBodyModel(List<Drink> mDrinks, int tableNumber) {
        this.mDrinks = mDrinks;
        this.tableNumber = tableNumber;
    }

    public List<Drink> getmDrinks() {
        return mDrinks;
    }

    public void setmDrinks(List<Drink> mDrinks) {
        this.mDrinks = mDrinks;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
