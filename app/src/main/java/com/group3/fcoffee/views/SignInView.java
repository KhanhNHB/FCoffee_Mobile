package com.group3.fcoffee.views;

import com.group3.fcoffee.models.Auth;

public interface SignInView {
    void onSignInSuccess(Auth account);
    void onSignInFail(String message);
}
