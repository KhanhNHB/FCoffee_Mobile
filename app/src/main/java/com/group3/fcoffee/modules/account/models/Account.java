package com.group3.fcoffee.modules.account.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Account implements Serializable {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("disable")
    private boolean disable = false;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("role")
    private int role = 0;

    @SerializedName("avatar")
    private String avatar;

    public Account() {}

    public Account(String username, String password, boolean disable, String fullname, int role, String avatar) {
        this.username = username;
        this.password = password;
        this.disable = disable;
        this.fullname = fullname;
        this.role = role;
        this.avatar = avatar;
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

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
