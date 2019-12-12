package com.group3.fcoffee.models;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private float price;
    @SerializedName("disable")
    private int disable;
    @SerializedName("description")
    private String description;
    @SerializedName("category_id")
    private String category_id;
    @SerializedName("image")
    private String image;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("created_by_id")
    private String created_by_id;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("updated_by_id")
    private String updated_by_id;
    private int quantityOrder;

    public Product() {
    }

    public Product(String id, String name, float price, int disable, String description, String category_id, String image, String created_at, String created_by_id, String updated_at, String updated_by_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.disable = disable;
        this.description = description;
        this.category_id = category_id;
        this.image = image;
        this.created_at = created_at;
        this.created_by_id = created_by_id;
        this.updated_at = updated_at;
        this.updated_by_id = updated_by_id;
    }

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(String created_by_id) {
        this.created_by_id = created_by_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_by_id() {
        return updated_by_id;
    }

    public void setUpdated_by_id(String updated_by_id) {
        this.updated_by_id = updated_by_id;
    }
}
