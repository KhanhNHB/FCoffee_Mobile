package com.group3.fcoffee.view_models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductOrder implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("price")
    private float price;
    @SerializedName("is_plus")
    private boolean isPlus;
    @SerializedName("is_sub")
    private boolean isSub;

    public ProductOrder() {
    }

    public ProductOrder(String id, int quantity, float price, boolean isPlus, boolean isSub) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.isPlus = isPlus;
        this.isSub = isSub;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isPlus() {
        return isPlus;
    }

    public void setPlus(boolean plus) {
        isPlus = plus;
    }

    public boolean isSub() {
        return isSub;
    }

    public void setSub(boolean sub) {
        isSub = sub;
    }
}
