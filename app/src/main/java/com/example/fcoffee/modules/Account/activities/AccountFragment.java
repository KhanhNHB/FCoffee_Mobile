package com.example.fcoffee.modules.Account.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.Account.models.Account;
import com.example.fcoffee.modules.Account.services.AccountService;


public class AccountFragment extends Fragment {

    private View mView;
    private TextView mTxtFullname, mTxtRole;
    private EditText mAccount, mPassword;
    private Button mBtnUpdate;

    private AccountService service;
    private Account account;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
//        mTxtFullname = mView.findViewById(R.id.txt_fullname);
//        mTxtRole = mView.findViewById(R.id.txt_role);
//        mAccount = mView.findViewById(R.id.txt_account);
//        mPassword = mView.findViewById(R.id.txt_password);
//        mBtnUpdate = mView.findViewById(R.id.btn_update_account);
//        service = APIUtils.getAccountService();
    }

    private void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_account, container, false);
        return mView;
    }
}
