package com.group3.fcoffee.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.group3.fcoffee.R;
import com.group3.fcoffee.models.Account;

public class AccountFragment extends Fragment {

    private View mView;
    private TextView mTxtUsername, mTxtFullname, mTxtRole, mDisable;
    private Button mBtnUpdate;
    private ImageView mImgAvatar;

    private Account account;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_account, container, false);
        initView();
        initData();
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        mImgAvatar = mView.findViewById(R.id.imgAvatar);
        mTxtUsername = mView.findViewById(R.id.txt_username_account);
        mTxtFullname = mView.findViewById(R.id.txt_fullname_account);
        mTxtRole = mView.findViewById(R.id.txt_role_account);
        mDisable = mView.findViewById(R.id.txt_disable_account);
    }

    private void initData() {

    }
}
