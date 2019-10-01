package com.example.fcoffee.modules.Account.presenters;

import com.example.fcoffee.modules.Account.models.Account;
import com.example.fcoffee.modules.Account.repositories.LoginRepository;
import com.example.fcoffee.Login.view.LoginView;

public class LoginPresenter {
    private LoginView mLoginView;
    private LoginRepository mLoginRepository;

    public LoginPresenter(LoginView mLoginView) {
        this.mLoginView = mLoginView;
        mLoginRepository =  new LoginRepository();
    }

    public void login(Account account, LoginView mLoginView){
        mLoginRepository.checkLogin(account,mLoginView);
    }
}
