package com.example.fcoffee.modules.account.views;

import com.example.fcoffee.modules.account.models.DTOAccountResponse.DTOAccountResponse;

public interface AccountView {
    public void onAccountSuccess(DTOAccountResponse accountData);
    public void onAccountFail(String message);
}
