package com.example.fcoffee.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TableList implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Table> tableList;

    public TableList() {}

    public TableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }
}
