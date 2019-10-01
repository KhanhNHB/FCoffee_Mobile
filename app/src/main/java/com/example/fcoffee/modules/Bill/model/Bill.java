package com.example.fcoffee.modules.Bill.model;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {
    private int billId;
    private int table_number;
    private int paided;
    private float discount;
    private float totalPrice;
    private String usernameStaff;
    private Date createdAr;

    public Bill() {}

    public Bill(int billId, int table_number, int paided, float discount, float totalPrice, String usernameStaff, Date createdAr) {
        this.billId = billId;
        this.table_number = table_number;
        this.paided = paided;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.usernameStaff = usernameStaff;
        this.createdAr = createdAr;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getTable_number() {
        return table_number;
    }

    public void setTable_number(int table_number) {
        this.table_number = table_number;
    }

    public int getPaided() {
        return paided;
    }

    public void setPaided(int paided) {
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

    public String getUsernameStaff() {
        return usernameStaff;
    }

    public void setUsernameStaff(String usernameStaff) {
        this.usernameStaff = usernameStaff;
    }

    public Date getCreatedAr() {
        return createdAr;
    }

    public void setCreatedAr(Date createdAr) {
        this.createdAr = createdAr;
    }
}