package com.group3.fcoffee.models;

import com.google.gson.annotations.SerializedName;

public class Auth {
    @SerializedName("username")
    private String username;
    @SerializedName("access_token")
    public String token;
    @SerializedName("expires_in")
    private String expires;
    @SerializedName("role")
    private int role;

    public Auth() {
    }

    public Auth(String username, String token, String expires, int role) {
        this.username = username;
        this.token = token;
        this.expires = expires;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
