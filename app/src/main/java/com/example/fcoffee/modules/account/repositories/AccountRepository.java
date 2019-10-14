package com.example.fcoffee.modules.account.repositories;

import android.util.Log;
import android.widget.Toast;

import com.example.fcoffee.common.Error;
import com.example.fcoffee.modules.account.models.Account;
import com.example.fcoffee.modules.account.models.DTOAccountResponse.DTOAccountResponse;
import com.example.fcoffee.modules.account.services.AccountService;
import com.example.fcoffee.modules.account.views.AccountView;
import com.example.fcoffee.utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private AccountService mAccountService;

    public AccountRepository() {
        mAccountService = APIUtils.getAccountService();
    }

    public AccountRepository(AccountService mAccountService) {
        this.mAccountService = mAccountService;
    }

    public void getInfo(final AccountView accountView) {
        try {
            Call<DTOAccountResponse> call = mAccountService.getInfo();
            call.enqueue(new Callback<DTOAccountResponse>() {
                @Override
                public void onResponse(Call<DTOAccountResponse> call, Response<DTOAccountResponse> response) {
                    if (response.code() == 200) {
                        DTOAccountResponse account = response.body();
                        accountView.onAccountSuccess(account);
                    } else {
                        accountView.onAccountFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                    }
                }

                @Override
                public void onFailure(Call<DTOAccountResponse> call, Throwable t) {
                    accountView.onAccountFail(Error.TAG_SYSTEM_BUSY);
                    Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
