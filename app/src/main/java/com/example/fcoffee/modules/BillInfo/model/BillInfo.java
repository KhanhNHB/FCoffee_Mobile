package com.example.fcoffee.modules.BillInfo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BillInfo implements Serializable {

    @SerializedName("id")
    @Expose()
    private int id;

    @SerializedName("billInfoId")
    @Expose
    private int billInfoId;

    @SerializedName("billId")
    @Expose
    private int billId;

    @SerializedName("drinkId")
    @Expose
    private int drinkId;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("subPrice")
    @Expose
    private float subPrice;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("name")
    @Expose
    private String drinkName;

    public BillInfo() {
    }

    public BillInfo(int id, int billInfoId, int billId, int drinkId, int count, float subPrice, String image, String drinkName) {
        this.id = id;
        this.billInfoId = billInfoId;
        this.billId = billId;
        this.drinkId = drinkId;
        this.count = count;
        this.subPrice = subPrice;
        this.image = image;
        this.drinkName = drinkName;
    }

    public int getId() {
        return id;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public int getBillInfoId() {
        return billInfoId;
    }

    public int getBillId() {
        return billId;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public int getCount() {
        return count;
    }

    public float getSubPrice() {
        return subPrice;
    }

    public String getImage() {
        return image;
    }

    public String getDrinkName() {
        return drinkName;
    }
}
