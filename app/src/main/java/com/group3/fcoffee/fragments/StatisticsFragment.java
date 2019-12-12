package com.group3.fcoffee.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.activities.TableDetailActivity;
import com.group3.fcoffee.adapters.StatisticsAdapter;
import com.group3.fcoffee.models.Bill;
import com.group3.fcoffee.presenters.BillPresenter;
import com.group3.fcoffee.views.BillView;

import java.util.List;

public class StatisticsFragment extends Fragment implements BillView {

    private View mView;
    private BillPresenter mBillPresenter;
    private RecyclerView mRcvStatisticsBill;
    private List<Bill> bills;
    private StatisticsAdapter mStatisticsAdapter;
    private SharedPreferences pref;
    private String token;

    public static StatisticsFragment newInstance() {
        StatisticsFragment fragment = new StatisticsFragment();
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
        initView();
        initData();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        TableDetailActivity.mOnReload = new TableDetailActivity.OnReload() {
            @Override
            public void reload() {
                mBillPresenter.getBills(token);
            }
        };
    }

    private void initView() {
        this.mRcvStatisticsBill = this.mView.findViewById(R.id.rcv_statistics_bill);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        this.mRcvStatisticsBill.setLayoutManager(gridLayoutManager);

        pref = getActivity().getSharedPreferences("MyRef", Context.MODE_PRIVATE);
        token = pref.getString("token", null);
    }

    private void initData() {
        this.mBillPresenter = new BillPresenter(this);
        this.mBillPresenter.getBills(token);
    }

    @Override
    public void onBillSuccess(Bill bill) {

    }

    @Override
    public void onBillsSuccess(List<Bill> bills) {
        this.bills = bills;
        updateRcv(bills);
    }

    private void updateRcv(List<Bill> bills) {
        if (this.mStatisticsAdapter == null) {
            this.mStatisticsAdapter = new StatisticsAdapter(getContext(), this.bills);
            this.mRcvStatisticsBill.setAdapter(this.mStatisticsAdapter);
        } else {
            this.mStatisticsAdapter.updateList(bills);
        }
    }

    @Override
    public void onSuccess(Boolean result) {

    }

    @Override
    public void onBillFail(String message) {

    }
}