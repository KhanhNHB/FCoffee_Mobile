package com.example.fcoffee.modules.account.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fcoffee.R;


public class LogoutFragment extends Fragment {

    private View mView;

    public static LogoutFragment newInstance() {
        LogoutFragment fragment = new LogoutFragment();
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
        mView = inflater.inflate(R.layout.fragment_statistics, container, false);
        return mView;
    }
}
