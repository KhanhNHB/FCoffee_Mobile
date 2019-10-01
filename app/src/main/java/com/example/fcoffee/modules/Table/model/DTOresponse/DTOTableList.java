package com.example.fcoffee.modules.Table.model.DTOresponse;

import com.example.fcoffee.modules.Table.model.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DTOTableList implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Table> tableList;

    public DTOTableList() {}

    public DTOTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }
}
