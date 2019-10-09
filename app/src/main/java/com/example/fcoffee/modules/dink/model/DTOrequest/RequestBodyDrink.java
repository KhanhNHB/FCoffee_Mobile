package com.example.fcoffee.modules.dink.model.DTOrequest;

import com.example.fcoffee.modules.dink.model.DTO.Drinks;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RequestBodyDrink implements Serializable {

    @SerializedName("listDrink")
    @Expose
    private List<Drinks> listDrink;


    @SerializedName("tableNumber")
    @Expose
    private int tableNumber;

    public RequestBodyDrink() {}

    public RequestBodyDrink(int tableNumber, List<Drinks> listDrink) {
        this.tableNumber = tableNumber;
        this.listDrink = listDrink;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<Drinks> getListDrink() {
        return listDrink;
    }

    public void setListDrink(List<Drinks> listDrink) {
        this.listDrink = listDrink;
    }
}