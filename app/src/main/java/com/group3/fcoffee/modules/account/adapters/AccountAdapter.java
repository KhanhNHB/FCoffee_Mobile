package com.group3.fcoffee.modules.account.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.group3.fcoffee.R;
import com.group3.fcoffee.modules.account.models.Account;

import java.util.Collections;

public class AccountAdapter extends ArrayAdapter<Account> {
    private Context mContext;
    private Account mAccount;

    public AccountAdapter(@NonNull Context context, int resource, Account account) {
        super(context, resource, Collections.singletonList(account));

        this.mContext = context;
        this.mAccount = account;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_account, parent, false);
        return view;
    }
}
