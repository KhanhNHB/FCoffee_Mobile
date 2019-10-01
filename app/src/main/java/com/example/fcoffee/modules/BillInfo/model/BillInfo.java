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

    public BillInfo() {
    }

    public BillInfo(int id, int billInfoId, int billId, int drinkId, int count, float subPrice, String image) {
        this.id = id;
        this.billInfoId = billInfoId;
        this.billId = billId;
        this.drinkId = drinkId;
        this.count = count;
        this.subPrice = subPrice;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillInfoId() {
        return billInfoId;
    }

    public void setBillInfoId(int billInfoId) {
        this.billInfoId = billInfoId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(float subPrice) {
        this.subPrice = subPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
