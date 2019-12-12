package com.group3.fcoffee.models;

import com.google.gson.annotations.SerializedName;

public class Bill {
    @SerializedName("id")
    String id;
    @SerializedName("table_name")
    String table_name;
    @SerializedName("total")
    float total;
    @SerializedName("created_at")
    String created_at;

    public Bill() {
    }

    public Bill(String id, String table_name, float total, String created_at) {
        this.id = id;
        this.table_name = table_name;
        this.total = total;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
