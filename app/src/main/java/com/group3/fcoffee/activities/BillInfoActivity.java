package com.group3.fcoffee.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.adapters.BillInfoAdapter;
import com.group3.fcoffee.models.BillInfo;
import com.group3.fcoffee.models.Product;
import com.group3.fcoffee.presenters.BillInfoPresenter;
import com.group3.fcoffee.utils.FormatMoney;
import com.group3.fcoffee.views.BillInfoView;
import com.group3.fcoffee.views.ProductView;

import java.util.ArrayList;
import java.util.List;

public class BillInfoActivity extends AppCompatActivity implements BillInfoView, ProductView {

    private ImageView mImgBack;
    private TextView mTxtTableNumberInfo, mTxtTotalPriceInfo, mTxtDiscountInfo, mTxtStaffInfo, mTxtTimeInfo;
    private String mTableNumber;
    private Float mTotal;
    private BillInfoPresenter mBillInfoPresenter;
    private String mBillId, token, mDate;
    private SharedPreferences pref;
    private List<BillInfo> mBillsInfo;
    private BillInfoAdapter mBillInfoAdapter;
    private RecyclerView mRcvBillsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_info);

        initView();
        initData();
    }

    private void initView() {
        this.mImgBack = findViewById(R.id.btn_back);
        this.mTxtTableNumberInfo = findViewById(R.id.txt_table_number_info);
        this.mTxtTotalPriceInfo = findViewById(R.id.txt_total_price_info);
        this.mTxtDiscountInfo = findViewById(R.id.txt_discount_info);
        this.mTxtStaffInfo = findViewById(R.id.txt_staff_info);
        this.mTxtTimeInfo = findViewById(R.id.txt_time_info);

        this.mRcvBillsInfo = findViewById(R.id.rcv_bill_info);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 1);
        this.mRcvBillsInfo.setLayoutManager(gridLayoutManager);
    }

    private void initData() {
        pref = getSharedPreferences("MyRef", MODE_PRIVATE);
        token = pref.getString("token", null);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bill");

        mBillId = bundle.getString("BillId");
        mDate = bundle.getString("Date");
        mTableNumber = bundle.getString("TableNumber");
        mTotal = bundle.getFloat("Total");

        ArrayList<BillInfo> billsInfo = bundle.getParcelableArrayList("BillsInfo");
        this.mBillsInfo = billsInfo;

        mTxtTableNumberInfo.setText(mTableNumber);
        mTxtTimeInfo.setText(mDate);
        mTxtTotalPriceInfo.setText(FormatMoney.formatVND(mTotal));

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mBillInfoPresenter = new BillInfoPresenter(this);
        mBillInfoPresenter.getBillInfoByBillId(mBillId, token);
    }

    @Override
    public void onBillsInfoSuccess(List<BillInfo> billsInfo) {
        updateRcv();
    }

    @Override
    public void onBillInfoSuccess(BillInfo billInfo) {

    }

    @Override
    public void onBillInfoFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateRcv() {
        if (this.mBillInfoAdapter == null) {
            this.mBillInfoAdapter = new BillInfoAdapter(this, this.mBillsInfo);
            this.mRcvBillsInfo.setAdapter(this.mBillInfoAdapter);
        } else {
            this.mBillInfoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onProductsSuccess(List<Product> product) {

    }

    @Override
    public void onProductSuccess(Product product) {

    }

    @Override
    public void onProductFail(String message) {

    }
}
