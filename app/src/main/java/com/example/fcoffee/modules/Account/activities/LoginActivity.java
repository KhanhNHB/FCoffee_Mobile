package com.example.fcoffee.modules.Account.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fcoffee.R;
import com.example.fcoffee.Login.view.LoginView;
import com.example.fcoffee.modules.Account.models.Account;
import com.example.fcoffee.modules.Account.presenters.LoginPresenter;
import com.example.fcoffee.modules.Table.activity.TableActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    LoginPresenter loginPresenter;
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
    }

    private void initData() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_account.getText().toString();
                String password = txt_password.getText().toString();
                Account account = new Account(username, password, false, null, 0, null);
                // Check form
                loginPresenter = new LoginPresenter(LoginActivity.this);
                loginPresenter.login(account,LoginActivity.this);
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


    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, TableActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFail(String message) {

    }
}
