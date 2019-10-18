package com.group3.fcoffee.modules.account.repositories;

import android.util.Log;

import com.group3.fcoffee.common.Error;
import com.group3.fcoffee.modules.account.models.Account;
import com.group3.fcoffee.modules.account.services.AccountService;
import com.group3.fcoffee.modules.account.views.LoginView;
import com.group3.fcoffee.utils.APIUtils;

import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    AccountService service;

    public static String TOKEN = "";

    public LoginRepository() {
        service = APIUtils.getAccountService();
    }

    public void checkLogin(Account account, final LoginView loginView) {
        JSONObject customer = new JSONObject();
        try {
            customer.put("username", account.getUsername());
            customer.put("password", account.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isValid(account, loginView)) {
            return;
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), customer.toString());
        Call<ResponseBody> call = service.checkLogin(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        TOKEN = response.body().string();
                        loginView.onLoginSuccess();
                    } catch (Exception e) {
                        loginView.onLoginFail("Login Fail");
                        Log.d(Error.TAG_ERROR_RESPONSE, e.getMessage());
                    }
                } else {
                        loginView.onLoginFail("Tên đăng nhập và mật khẩu không đúng!");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loginView.onLoginFail("Login Fail");
                Log.d(Error.TAG_ERROR_REQUEST, t.getMessage());
            }
        });
    }

    private boolean isValid(Account account, LoginView loginView) {
        if (account.getUsername() == null || account.getUsername().trim().length() == 0) {
            loginView.onLoginFail("Vui lòng nhập tên tài khoản!");
            return false;
        }
        if (account.getPassword() == null || account.getPassword().trim().length() == 0) {
            loginView.onLoginFail("Vui lòng nhập mật khẩu!");
            return false;
        }
        return true;
    }
}
