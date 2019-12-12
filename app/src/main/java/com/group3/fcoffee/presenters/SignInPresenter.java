package com.group3.fcoffee.presenters;


import android.content.SharedPreferences;

import com.group3.fcoffee.models.Account;
import com.group3.fcoffee.models.Auth;
import com.group3.fcoffee.repositories.FCoffeeRepository;
import com.group3.fcoffee.repositories.FCoffeeRepositoryIMP;
import com.group3.fcoffee.utils.CallBackData;
import com.group3.fcoffee.views.SignInView;

public class SignInPresenter {
    private SignInView mSignInView;
    private FCoffeeRepository mFCoffeeRepository;

    public SignInPresenter(SignInView mSignInView) {
        this.mSignInView = mSignInView;
        mFCoffeeRepository = new FCoffeeRepositoryIMP();
    }

    public void login(Account account) {
        mFCoffeeRepository.signIn(account.getUsername(), account.getPassword(), new CallBackData<Auth>() {
            @Override
            public void onSuccess(Auth auth) {
                mSignInView.onSignInSuccess(auth);
            }

            @Override
            public void onFail(String message) {
                mSignInView.onSignInFail(message);
            }
        });
    }

//    private boolean isValid(Account account) {
//        if (account.getUsername() == null || account.getUsername().trim().length() == 0) {
//            Toast.makeText(this, "Vui lòng điền tên đăng nhập!", Toast.LENGTH_LONG).show();
//            return false;
//        }
//        if (account.getPassword() == null || account.getPassword().trim().length() == 0) {
//            Toast.makeText(this, "Vui lòng điền mật khẩu!", Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return true;
//    }
}
