package com.group3.fcoffee.view_models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TableDeleteProductItem implements Serializable {
    @SerializedName("table_id")
    private String tableId;
    @SerializedName("bill_id")
    private String billId;
    @SerializedName("product_id")
    private String product_id;
    @SerializedName("product_quantity")
    private int productQuantity;
    @SerializedName("product_price")
    private float productPrice;

    public TableDeleteProductItem(String tableId, String billId, String product_id, int productQuantity, float productPrice) {
        this.tableId = tableId;
        this.billId = billId;
        this.product_id = product_id;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public String getTableId() {
        return tableId;
    }

    public String getBillId() {
        return billId;
    }

    public String getProduct_id() {
        return product_id;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public float getProductPrice() {
        return productPrice;
    }
}
