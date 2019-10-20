package com.group3.fcoffee.modules.account.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.fcoffee.R;
import com.group3.fcoffee.modules.account.models.Account;
import com.group3.fcoffee.modules.account.presenters.LoginPresenter;
import com.group3.fcoffee.modules.account.views.LoginView;
import com.group3.fcoffee.modules.table.activity.TableActivity;

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

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, TableActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
