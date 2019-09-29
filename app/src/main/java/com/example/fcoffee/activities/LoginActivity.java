package com.example.fcoffee.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fcoffee.R;
import com.example.fcoffee.models.Account;
import com.example.fcoffee.services.AccountService;
import com.example.fcoffee.utils.APIUtils;

import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    AccountService service;
    EditText txt_account, txt_password;
    Button btn_login;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initView();
        initData();
    }

    private void initView() {
        txt_account = findViewById(R.id.txt_account);
        txt_password = findViewById(R.id.txt_password);
        txt_password.setTransformationMethod(new PasswordTransformationMethod());
        btn_login = findViewById(R.id.btn_login);

        service = APIUtils.getAccountService();
    }

    private void initData() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_account.getText().toString();
                String password = txt_password.getText().toString();
                Account account = new Account(username, password, false, null, 0, null);
                // Check form
                if (isValid(account)) {
                    checkLogin(account);
                }
            }
        });
    }

    private boolean isValid(Account account) {
        if (account.getUsername() == null || account.getUsername().trim().length() == 0) {
            Toast.makeText(this, "Vui lòng điền tên đăng nhập!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (account.getPassword() == null || account.getPassword().trim().length() == 0) {
            Toast.makeText(this, "Vui lòng điền mật khẩu!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void checkLogin(Account account) {
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
                        String token = response.body().string();
                        Intent intent = new Intent(LoginActivity.this, TableActivity.class);
                        intent.putExtra("author_token", token);
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.d("ERROR +=+=+= ", e.getMessage());
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Tên đăng nhập và mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERROR +=+=+= ", t.getMessage());
            }
        });
    }
}
