package com.example.fcoffee.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Table implements Serializable {
    @SerializedName("tableNumber")
    private int tableNumber;

    @SerializedName("status")
    private boolean status;

    public Table() {}

    public Table(int tableNumber, boolean status) {
        this.tableNumber = tableNumber;
        this.status = status;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
