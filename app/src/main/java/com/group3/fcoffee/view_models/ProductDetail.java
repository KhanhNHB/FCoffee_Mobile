package com.group3.fcoffee.view_models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductDetail implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("price")
    private float price;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;

    public ProductDetail() {
    }

    public ProductDetail(String id, float price, int quantity, String image, String name) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
