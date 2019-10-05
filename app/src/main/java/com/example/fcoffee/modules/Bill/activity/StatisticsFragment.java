package com.example.fcoffee.modules.Bill.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.Bill.adapter.StatisticsBillAdapter;
import com.example.fcoffee.modules.Bill.model.DTOresponse.DTOBillList;
import com.example.fcoffee.modules.Bill.presenter.BillPresenter;
import com.example.fcoffee.modules.Bill.view.BillView;


public class StatisticsFragment extends Fragment implements BillView {

    private View mView;
    private RecyclerView mRecyclerView;
    private StatisticsBillAdapter mStatisticsBillAdapter;
    private DTOBillList mBills;
    private BillPresenter mBillPresenter;

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
        initData();
        initView();
        return mView;

    }

    private void initView(){
        mRecyclerView = mView.findViewById(R.id.rcv_bill);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData(){
        mBillPresenter = new BillPresenter(this);
        mBillPresenter.getAllBillByToken(this);
    }

    @Override
    public void onBillSuccessGetAllByUsername(DTOBillList dto) {

        if(dto != null){
            mBills = dto;
            updateRcv();
        }
    }

    private void updateRcv(){
        if(mStatisticsBillAdapter == null){
            mStatisticsBillAdapter = new StatisticsBillAdapter(getContext(), mBills);
            mRecyclerView.setAdapter(mStatisticsBillAdapter);
        }else{
            mStatisticsBillAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBillFail(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
