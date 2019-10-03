package com.example.fcoffee.modules.Table.model.DTOresponse;

import com.example.fcoffee.modules.BillInfo.model.BillInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DTOTableDetail implements Serializable {
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

    @SerializedName("usernameStaff")
    @Expose
    private String usernameStaff;

    @SerializedName("createAt")
    @Expose
    private Date createAt;

    @SerializedName("billId")
    @Expose
    private int billId;

    @SerializedName("listBillInfos")
    @Expose
    private List<BillInfo> listBillInfos;

    public DTOTableDetail() {}

    public DTOTableDetail(int id, int tableNumber, boolean paided, float totalPrice, float discount, String usernameStaff, Date createAt, int billId, List<BillInfo> listBillInfos) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.paided = paided;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.usernameStaff = usernameStaff;
        this.createAt = createAt;
        this.billId = billId;
        this.listBillInfos = listBillInfos;
    }

    public int getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isPaided() {
        return paided;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public String getUsernameStaff() {
        return usernameStaff;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public int getBillId() {
        return billId;
    }

    public List<BillInfo> getListBillInfos() {
        return listBillInfos;
    }
}
