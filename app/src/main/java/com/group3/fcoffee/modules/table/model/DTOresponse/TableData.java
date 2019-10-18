package com.group3.fcoffee.modules.table.model.DTOresponse;

import com.group3.fcoffee.modules.table.model.DTOrequest.Table;
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
