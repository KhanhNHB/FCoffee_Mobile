package com.group3.fcoffee.modules.account.presenters;

import com.group3.fcoffee.modules.account.repositories.AccountRepository;
import com.group3.fcoffee.modules.account.views.AccountView;

public class AccountPresenter {
    private AccountRepository mAccountRepository;
    private AccountView mAccountView;

    public AccountPresenter() {}

    public AccountPresenter(AccountView mAccountView) {
        this.mAccountRepository = new AccountRepository();
        this.mAccountView = mAccountView;
    }

    public void getInfo() {
        mAccountRepository.getInfo(mAccountView);
    }
}