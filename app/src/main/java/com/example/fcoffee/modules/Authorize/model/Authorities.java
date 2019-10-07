package com.example.fcoffee.modules.Authorize.model;

import java.io.Serializable;

public class Authorities implements Serializable {
    private int authorityId;
    private String authorityName;

    public Authorities() {}

    public Authorities(int authorityId, String authorityName) {
        this.authorityId = authorityId;
        this.authorityName = authorityName;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}