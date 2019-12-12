package com.group3.fcoffee.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.adapters.TableAdapter;
import com.group3.fcoffee.adapters.TableDetailAdapter;
import com.group3.fcoffee.common.RequestCode;
import com.group3.fcoffee.models.Table;
import com.group3.fcoffee.presenters.TablePresenter;
import com.group3.fcoffee.view_models.ProductOrder;
import com.group3.fcoffee.view_models.TableDetail;
import com.group3.fcoffee.views.TableView;

import java.util.List;

public class TableDetailActivity extends AppCompatActivity implements TableView {

    private RecyclerView mRecyclerView;
    private TableDetailActivity mTableDetailActivity;
    private TableAdapter mTableAdapter;
    private TableDetailAdapter mTableDetailAdapter;

    private TablePresenter mTablePresenter;
    private String mTableName;
    private String mTableId;
    private TextView mTxtTableName, mTxtTotalPrice, mTxtDiscount;
    private Button mBtnCheckOut;
    private ImageView mBtnAddDrink, mBtnBack;
    private TableDetail mTableDetail;
    private int mAvailable;
    private List<ProductOrder> mProductOrders;
    private Intent intent;
    private SharedPreferences pref;
    private String token = null;
    public static OnReload mOnReload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_detail);
        initView();
        initData();
    }

    private void initView() {
        this.mRecyclerView = findViewById(R.id.rcv_table_detail);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);

        this.mRecyclerView.setLayoutManager(gridLayoutManager);
        this.intent = getIntent();
        Bundle bundle = intent.getBundleExtra("table");

        this.mTableName = bundle.getString("table_name");
        this.mTableId = bundle.getString("table_id");
        this.mAvailable = bundle.getInt("available");

        this.mTxtTableName = findViewById(R.id.txt_table_name);
        this.mBtnBack = findViewById(R.id.btn_back);
        this.mBtnAddDrink = findViewById(R.id.btn_add_drink);

        this.mTxtTotalPrice = findViewById(R.id.txt_total_price);
        this.mTxtDiscount = findViewById(R.id.txt_discount);

        this.mBtnCheckOut = findViewById(R.id.btn_check_out);

        pref = getSharedPreferences("MyRef", MODE_PRIVATE);
        token = pref.getString("token", null);
    }

    private void initData() {
        mTxtTableName.setText(mTableName);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mBtnAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("table_id", mTableId);
                intent.putExtra("table", bundle);
                startActivityForResult(intent, RequestCode.REQUEST_CODE_ADD_DRINK);
            }
        });

        this.mTablePresenter = new TablePresenter(this);
        this.mTablePresenter.getTableDetail(mTableId, token);
    }

    @Override
    public void onTableDeleteProductItem(Boolean result) {

    }

    @Override
    public void onTablesSuccess(List<Table> tables) {
    }

    @Override
    public void onTableDetailSuccess(TableDetail tableDetail) {
        this.mTableDetail = tableDetail;

        this.mTxtTotalPrice.setText(String.valueOf(this.mTableDetail.getBillDetail().getTotal()));
        this.mTxtDiscount.setText(String.valueOf(this.mTableDetail.getBillDetail().getDiscount()));

        updateRcv();
    }

    @Override
    public void onTableSuccess(Table table) {
    }

    @Override
    public void onTableCheckAvailableSuccess(Boolean result) {
        onBackPressed();
    }

    private void updateRcv() {
        mTableDetailAdapter = new TableDetailAdapter(this, this.mTableDetail, this.mTxtTotalPrice);
        mRecyclerView.setAdapter(mTableDetailAdapter);
    }

    @Override
    public void onTableFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode.REQUEST_CODE_ADD_DRINK) {
//            ArrayList<ProductOrder> arrayList = data.getParcelableArrayListExtra("productOrders");
//            this.mProductOrders = arrayList;
            this.mTablePresenter.getTableDetail(mTableId, token);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void clickToCheckOut(View view) {
        this.mTablePresenter.checkOutTable(this.mTableId, token);
        mOnReload.reload();
    }

    public interface OnReload {
        void reload();
    }
}