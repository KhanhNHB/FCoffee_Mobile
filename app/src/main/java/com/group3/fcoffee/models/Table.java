package com.group3.fcoffee.models;

import com.google.gson.annotations.SerializedName;

public class Table {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("available")
    int available;

    public Table() {
    }

    public Table(String id, String name, int available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
