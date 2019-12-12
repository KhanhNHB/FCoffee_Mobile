package com.group3.fcoffee.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.fcoffee.R;
import com.group3.fcoffee.models.Account;
import com.group3.fcoffee.models.Auth;
import com.group3.fcoffee.presenters.SignInPresenter;
import com.group3.fcoffee.views.SignInView;

public class SignInActivity extends AppCompatActivity implements SignInView {

    SignInPresenter loginPresenter;
    EditText txt_account, txt_password;
    Button btn_login;
    SharedPreferences pref;

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
                Account account = new Account(username, password, 0, null, 1, null, null, null);
                // Check form
                loginPresenter = new SignInPresenter(SignInActivity.this);
                loginPresenter.login(account);
            }
        });
    }

    @Override
    public void onSignInSuccess(Auth auth) {
        pref = getSharedPreferences("MyRef", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", auth.getToken());

        editor.commit();

        Intent intent = new Intent(SignInActivity.this, TableActivity.class);
        
        startActivity(intent);
    }

    @Override
    public void onSignInFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}