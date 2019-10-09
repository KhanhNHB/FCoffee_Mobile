package com.example.fcoffee.modules.billInfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.billInfo.adapter.BillInfoAdapter;
import com.example.fcoffee.modules.billInfo.model.DTOresponse.DTOBillInfoList;
import com.example.fcoffee.modules.billInfo.presenter.BillInfoPresenter;
import com.example.fcoffee.modules.billInfo.view.BillInfoView;

public class BillInfoActivity extends AppCompatActivity implements BillInfoView {

    private RecyclerView mRecyclerView;
    private BillInfoAdapter mBillInfoAdapter;
    private DTOBillInfoList mInfoList;
    private BillInfoPresenter mBillInfoPresenter;

    private int mBillId;

    private TextView mtv_table_number;
    private TextView mtv_price;
    private TextView mtv_username;
    private TextView mtv_discount;
    private TextView mtv_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_info);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("anonymouse_number");
        initView(bundle);
        initData(bundle);
    }

    private void initView(Bundle bundle){
        mBillId = bundle.getInt("bill_id");

        mtv_table_number = findViewById(R.id.tv_table_number_info);
        mtv_price = findViewById(R.id.tv_price_info);
        mtv_username = findViewById(R.id.tv_username_info);
        mtv_discount = findViewById(R.id.tv_discount_info);
        mtv_time = findViewById(R.id.tv_time_info);

        mRecyclerView = findViewById(R.id.rcv_bill_info);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
    private void initData(Bundle bundle){
        mBillId = bundle.getInt("bill_id");

        mtv_table_number.setText(bundle.getString("table_number"));
        mtv_price.setText(bundle.getString("price"));
        mtv_username.setText(bundle.getString("usernameStaff"));
        mtv_discount.setText(bundle.getString("discount"));
        mtv_time.setText(bundle.getString("date"));

        mBillInfoPresenter = new BillInfoPresenter(this, mBillId);
        mBillInfoPresenter.getAllBillInfoByBillId(this);
    }

    @Override
    public void onBillInfoSuccessGetAllByBillId(DTOBillInfoList dto) {
        if(dto != null){
            mInfoList = dto;
            updateRcv();
        }
    }

    private void updateRcv(){
        if(mBillInfoAdapter == null){
            mBillInfoAdapter = new BillInfoAdapter(getApplicationContext(), mInfoList);
            mRecyclerView.setAdapter(mBillInfoAdapter);
        }else{
            mBillInfoAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onBillFail(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
