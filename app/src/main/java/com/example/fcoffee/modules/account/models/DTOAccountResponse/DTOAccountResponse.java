package com.example.fcoffee.modules.account.models.DTOAccountResponse;

import com.example.fcoffee.modules.account.models.Account;
import com.google.gson.annotations.SerializedName;

public class DTOAccountResponse {

    @SerializedName("data")
    private Account accountData;

    public DTOAccountResponse() {
    }

    public DTOAccountResponse(Account accountData) {
        this.accountData = accountData;
    }

    public Account getAccountData() {
        return accountData;
    }

    public void setAccountData(Account accountData) {
        this.accountData = accountData;
    }
}
