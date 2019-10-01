package com.example.fcoffee.modules.Table.model;

import com.example.fcoffee.modules.BillInfo.model.BillInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TableInfo implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("tableNumber")
    @Expose
    private int tableNumber;

    @SerializedName("paided")
    @Expose
    private boolean paided;

    @SerializedName("totalPrice")
    @Expose
    private float totalPrice;

    @SerializedName("discount")
    @Expose
    private float discount;

    @SerializedName("usernameSatff")
    @Expose
    private String usernameSatff;

    @SerializedName("createAt")
    @Expose
    private Date createAt;

    @SerializedName("billId")
    @Expose
    private int billId;

    @SerializedName("listBillInfos")
    @Expose
    private List<BillInfo> billInfos;

    public TableInfo() {
    }

    public TableInfo(int id, int tableNumber, boolean paided, float totalPrice, float discount, String usernameSatff, Date createAt, int billId, List<BillInfo> billInfos) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.paided = paided;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.usernameSatff = usernameSatff;
        this.createAt = createAt;
        this.billId = billId;
        this.billInfos = billInfos;
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public List<BillInfo> getBillInfos() {
        return billInfos;
    }

    public void setBillInfos(List<BillInfo> billInfos) {
        this.billInfos = billInfos;
    }
}
