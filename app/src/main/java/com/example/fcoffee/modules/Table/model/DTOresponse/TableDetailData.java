package com.example.fcoffee.modules.Table.model.DTOresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TableDetailData implements Serializable {
    @SerializedName("data")
    @Expose
    private DTOTableDetail tableDetail;

    public TableDetailData() {}

    public TableDetailData(DTOTableDetail tableDetail) {
        this.tableDetail = tableDetail;
    }

    public DTOTableDetail getTableDetail() {
        return tableDetail;
    }
}
