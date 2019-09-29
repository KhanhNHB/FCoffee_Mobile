package com.example.fcoffee.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TableDetail {

    @SerializedName("data")
    @Expose
    private TableInfo mTableInfo;

    public TableDetail() {}

    public TableDetail(TableInfo mTableInfo) {
        this.mTableInfo = mTableInfo;
    }

    public TableInfo getmTableInfo() {
        return mTableInfo;
    }

    public void setmTableInfo(TableInfo mTableInfo) {
        this.mTableInfo = mTableInfo;
    }
}
