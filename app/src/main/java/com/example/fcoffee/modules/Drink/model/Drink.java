package com.example.fcoffee.modules.Drink.model;

import java.io.Serializable;

public class Drink implements Serializable {
    private int drinkId;
    private String drinkName;
    private float price;
    private boolean disable;
    private String descriptionm;
    private int categoryId;
    private String image;

    public Drink() {}

    public Drink(int drinkId, String drinkName, float price, boolean disable, String descriptionm, int categoryId, String image) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.price = price;
        this.disable = disable;
        this.descriptionm = descriptionm;
        this.categoryId = categoryId;
        this.image = image;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getDescriptionm() {
        return descriptionm;
    }

    public void setDescriptionm(String descriptionm) {
        this.descriptionm = descriptionm;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
