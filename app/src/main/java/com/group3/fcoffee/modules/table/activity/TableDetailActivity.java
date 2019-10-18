package com.group3.fcoffee.modules.table.activity;

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

import com.group3.fcoffee.R;
import com.group3.fcoffee.modules.dink.activity.DrinkActivity;
import com.group3.fcoffee.modules.management.presenter.ManagementPresenter;
import com.group3.fcoffee.modules.management.view.ManagementView;
import com.group3.fcoffee.modules.table.adapter.TableDetailAdapter;
import com.group3.fcoffee.modules.table.model.DTOresponse.TableDetailData;
import com.group3.fcoffee.modules.table.model.DTOresponse.TableData;
import com.group3.fcoffee.modules.table.presenter.TablePresenter;
import com.group3.fcoffee.modules.table.view.TableView;
import com.group3.fcoffee.utils.FormatMoney;

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

    ImageView btn_add_discount;

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

        btn_add_discount = findViewById(R.id.img_product);

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

        btn_add_discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTableDetail == null || mTableDetail.getTableDetail().getListBillInfos().size() == 0) {
                    Toast.makeText(TableDetailActivity.this, "Bàn trống", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(TableDetailActivity.this, PictureBarcodeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("billId", mTableDetail.getTableDetail().getBillId());
                    intent.putExtra("bundle_billId", bundle);
                    startActivity(intent);
                }
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
            mTxtDiscount.setText(mTableDetail.getTableDetail().getDiscount()+ " %");
            updateRcv(mTableDetail);
        }
    }

    @Override
    public void onTableFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateRcv(TableDetailData data) {
        if (mTableDetailAdapter == null) {
            mTableDetailAdapter = new TableDetailAdapter(this, data, mTxtTotalPrice);
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
        }
    }

    @Override
    public void onDrinkSuccess() {
    }

    @Override
    public void onCheckoutSuccess() {
        Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRemoveDrinkSuccess() {
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrinkFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDiscountSuccess() {
    }

    @Override
    public void onDiscountFail(String message) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initData();
    }

}
