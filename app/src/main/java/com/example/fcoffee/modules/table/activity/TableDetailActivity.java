package com.example.fcoffee.modules.table.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.common.Money;
import com.example.fcoffee.modules.dink.activity.DrinkActivity;
import com.example.fcoffee.modules.management.presenter.ManagementPresenter;
import com.example.fcoffee.modules.management.view.ManagementView;
import com.example.fcoffee.modules.table.adapter.TableDetailAdapter;
import com.example.fcoffee.modules.table.model.DTOresponse.TableDetailData;
import com.example.fcoffee.modules.table.model.DTOresponse.TableData;
import com.example.fcoffee.modules.table.presenter.TablePresenter;
import com.example.fcoffee.modules.table.view.TableView;
import com.example.fcoffee.utils.FormatMoney;

public class TableDetailActivity extends AppCompatActivity implements TableView, ManagementView {
    private static final int DRINK = 1998;
    private RecyclerView mRecyclerView;
    private TableDetailData mTableDetail;
    private TableDetailAdapter mTableDetailAdapter;
    private int tableNumber;
    private TextView mTxtDiscount;

    private TablePresenter mTablePresenter;
    private ManagementPresenter mManagementPresenter;

    private ImageView btn_back, btn_add_drink;
    private Button btn_checkout;
    private TextView mTxtTableName, mTxtTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_detail);
        initView();
        initData();
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        btn_add_drink = findViewById(R.id.btn_add_drink);
        btn_checkout = findViewById(R.id.btn_check_out);
        mTxtTableName = findViewById(R.id.txt_table_name);
        mTxtTotalPrice = findViewById(R.id.txt_total_price);
        mTxtDiscount = findViewById(R.id.txt_discount);

        mRecyclerView = findViewById(R.id.rcv_table_detail);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("anonymouse_number");
        tableNumber = bundle.getInt("table_number");
    }

    private void initData() {
        mTablePresenter = new TablePresenter(this);
        mManagementPresenter = new ManagementPresenter(this);

        mTablePresenter.getByNumber(tableNumber);

        mTxtTableName.setText("Bàn " + tableNumber);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_add_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableDetailActivity.this, DrinkActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("number", tableNumber);
                intent.putExtra("table_number", bundle);
                startActivityForResult(intent, DRINK);
            }
        });
    }

    @Override
    public void onTableSuccessGetAll(TableData dto) {
        // TODO- TableFragment
    }

    @Override
    public void onTableSuccessGetByNumber(TableDetailData tableDetail) {
        if (tableDetail != null) {
            mTableDetail = tableDetail;
            mTxtTotalPrice.setText(FormatMoney.formatVND(mTableDetail.getTableDetail().getTotalPrice()));
            mTxtDiscount.setText(String.valueOf(mTableDetail.getTableDetail().getDiscount()));
            updateRcv(mTableDetail);
        }
    }

    @Override
    public void onTableFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateRcv(TableDetailData data) {
        if (mTableDetailAdapter == null) {
            mTableDetailAdapter = new TableDetailAdapter(this, data, mTxtTotalPrice, mTableDetail.getTableDetail().getTotalPrice()) ;
            mRecyclerView.setAdapter(mTableDetailAdapter);
        } else {
            mTableDetailAdapter.updateTableDetailData(data);
        }
    }

    public void clickToCheckOut(View view) {
        if (mTableDetail == null || mTableDetail.getTableDetail().getListBillInfos().size() == 0) {
            Toast.makeText(this, "Bàn trống", Toast.LENGTH_SHORT).show();
        } else {
            int billId = mTableDetail.getTableDetail().getBillId();
            mManagementPresenter.payment(billId);
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
        }
    }

    @Override
    public void onDrinkSuccess() {
        Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onDrinkFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initData();
    }

}
