package com.example.fcoffee.modules.account.presenters;

import com.example.fcoffee.modules.account.models.Account;
import com.example.fcoffee.modules.account.repositories.LoginRepository;
import com.example.fcoffee.modules.account.views.LoginView;

public class LoginPresenter {
    private LoginView mLoginView;
    private LoginRepository mLoginRepository;

    public LoginPresenter(LoginView mLoginView) {
        this.mLoginView = mLoginView;
        mLoginRepository = new LoginRepository();
    }

    public void login(Account account, LoginView mLoginView) {
        mLoginRepository.checkLogin(account, mLoginView);
    }
}
