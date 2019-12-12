package com.group3.fcoffee.view_models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TableDetail implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("available")
    private String available;
    @SerializedName("products")
    private List<ProductDetail> productDetails;
    @SerializedName("bill")
    private BillDetail billDetail;

    public TableDetail() {
    }

    public TableDetail(String id, String available, List<ProductDetail> productDetails, BillDetail billDetail) {
        this.id = id;
        this.available = available;
        this.productDetails = productDetails;
        this.billDetail = billDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public BillDetail getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(BillDetail billDetail) {
        this.billDetail = billDetail;
    }
}
