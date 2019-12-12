package com.group3.fcoffee.models;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("disable")
    private int disable;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("role")
    private int role;
    @SerializedName("image")
    private String image;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;

    public Account() {
    }

    public Account(String username, String password, int disable, String fullname, int role, String image, String created_at, String updated_at) {
        this.username = username;
        this.password = password;
        this.disable = disable;
        this.fullname = fullname;
        this.role = role;
        this.image = image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
