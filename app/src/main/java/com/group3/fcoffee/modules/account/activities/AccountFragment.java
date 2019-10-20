package com.group3.fcoffee.modules.account.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.group3.fcoffee.R;
import com.group3.fcoffee.modules.account.models.DTOAccountResponse.DTOAccountResponse;
import com.group3.fcoffee.modules.account.presenters.AccountPresenter;
import com.group3.fcoffee.modules.account.views.AccountView;
import com.squareup.picasso.Picasso;


public class AccountFragment extends Fragment implements AccountView {

    private View mView;
    private TextView mTxtFullName, mTxtAccount, mTxtPassword, mTxtRole;
    private ImageView mImgAvatar;
    private DTOAccountResponse mAccount;
    private AccountPresenter mAccountPresenter;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_account, container, false);
        initData();
        initView();
        return mView;
    }

    private void initView(){
        mTxtFullName = mView.findViewById(R.id.txt_fullname);
        mTxtAccount = mView.findViewById(R.id.txt_account);
        mTxtPassword = mView.findViewById(R.id.txt_password);
        mTxtPassword.setTransformationMethod(new PasswordTransformationMethod());
        mImgAvatar = mView.findViewById(R.id.imgAvatar);
        mTxtRole = mView.findViewById(R.id.txt_role);
    }

    private void initData(){
        mAccountPresenter = new AccountPresenter(this);
        mAccountPresenter.getInfo();
    }

    @Override
    public void onAccountSuccess(DTOAccountResponse accountData) {
        if (accountData != null) {
            mAccount = accountData;
            updateData();
        } else {
            Toast.makeText(mView.getContext(), "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData(){
        mTxtFullName.setText(mAccount.getAccountData().getFullname());

        Picasso.Builder builder = new Picasso.Builder(mView.getContext());
        builder.build().load(mAccount.getAccountData().getAvatar())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(mImgAvatar);
        mTxtAccount.setText(mAccount.getAccountData().getUsername());
//        mTxtPassword.setTransformationMethod(new PasswordTransformationMethod());
//        mTxtPassword.setText(mAccount.getAccountData().getPassword());
        mTxtRole.setText(mAccount.getAccountData().getRole() == 1 ? "Quản lý" : "Nhân viên");
    }

    @Override
    public void onAccountFail(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
