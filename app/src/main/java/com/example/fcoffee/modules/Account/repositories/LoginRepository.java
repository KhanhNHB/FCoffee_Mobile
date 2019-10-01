package com.example.fcoffee.modules.Account.repositories;

import android.util.Log;

import com.example.fcoffee.modules.Account.models.Account;
import com.example.fcoffee.modules.Account.services.AccountService;
import com.example.fcoffee.utils.APIUtils;
import com.example.fcoffee.Login.view.LoginView;

import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    AccountService service;

    public void checkLogin(Account account, final LoginView loginView) {
        service = APIUtils.getAccountService();
        JSONObject custommer = new JSONObject();
        try {
            custommer.put("username", account.getUsername());
            custommer.put("password", account.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), custommer.toString());
        Call<ResponseBody> call = service.checkLogin(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
//                        String token = response.body().string();
//                        Intent intent = new Intent(LoginActivity.this, TableActivity.class);
//                        intent.putExtra("author_token", token);
//                        startActivity(intent);
                        loginView.onLoginSuccess();
                    } catch (Exception e) {
                        loginView.onLoginFail("Login Fail");
                        Log.d("ERROR +=+=+= ", e.getMessage());
                    }
                } else {
//                    Toast.makeText(LoginActivity.this, "Tên đăng nhập và mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                loginView.onLoginFail("Login Fail");
                Log.d("ERROR +=+=+= ", t.getMessage());
            }
        });
    }






}
