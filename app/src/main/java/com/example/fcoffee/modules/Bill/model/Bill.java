package com.example.fcoffee.modules.Bill.model;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {
    private int id;
    private int tableNumber;
    private boolean paided;
    private float discount;
    private float totalPrice;
    private String usernameSatff;
    private Date createAt;

    public Bill() {}

    public Bill(int id, int tableNumber, boolean paided, float discount, float totalPrice, String usernameSatff, Date createAt) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.paided = paided;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.usernameSatff = usernameSatff;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isPaided() {
        return paided;
    }

    public void setPaided(boolean paided) {
        this.paided = paided;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsernameSatff() {
        return usernameSatff;
    }

    public void setUsernameSatff(String usernameSatff) {
        this.usernameSatff = usernameSatff;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}