package com.group3.fcoffee.modules.dink.model.DTOrequest;

import com.group3.fcoffee.modules.dink.model.DTO.Drink;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RequestBodyDrink implements Serializable {

    @SerializedName("listDrink")
    @Expose
    private List<Drink> listDrink;


    @SerializedName("tableNumber")
    @Expose
    private int tableNumber;

    public RequestBodyDrink() {}

    public RequestBodyDrink(int tableNumber, List<Drink> listDrink) {
        this.tableNumber = tableNumber;
        this.listDrink = listDrink;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<Drink> getListDrink() {
        return listDrink;
    }

    public void setListDrink(List<Drink> listDrink) {
        this.listDrink = listDrink;
    }
}