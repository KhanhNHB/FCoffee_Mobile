package com.group3.fcoffee.view_models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BillDetail implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("discount")
    private int discount;
    @SerializedName("total")
    private float total;

    public BillDetail() {
    }

    public BillDetail(String id, int discount, float total) {
        this.id = id;
        this.discount = discount;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
