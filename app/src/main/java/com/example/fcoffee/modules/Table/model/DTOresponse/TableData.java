package com.example.fcoffee.modules.Table.model.DTOresponse;

import com.example.fcoffee.modules.Table.model.DTOrequest.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TableData implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Table> mTables;

    public TableData() {}

    public TableData(List<Table> mTables) {
        this.mTables = mTables;
    }

    public List<Table> getmTables() {
        return mTables;
    }
}
