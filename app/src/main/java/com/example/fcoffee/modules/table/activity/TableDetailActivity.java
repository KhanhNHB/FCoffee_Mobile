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
import com.example.fcoffee.modules.dink.activity.DrinkActivity;
import com.example.fcoffee.modules.management.presenter.ManagementPresenter;
import com.example.fcoffee.modules.management.view.ManagementView;
import com.example.fcoffee.modules.table.adapter.TableDetailAdapter;
import com.example.fcoffee.modules.table.model.DTOresponse.TableDetailData;
import com.example.fcoffee.modules.table.model.DTOresponse.TableData;
import com.example.fcoffee.modules.table.presenter.TablePresenter;
import com.example.fcoffee.modules.table.view.TableView;

public class TableDetailActivity extends AppCompatActivity implements TableView, ManagementView {

    private RecyclerView mRecyclerView;
    private TableDetailData mTableDetail;
    private TableDetailAdapter mTableDetailAdapter;
    private int tableNumber;

    private TablePresenter mTablePresenter;
    private ManagementPresenter mManagementPresenter;

    private ImageView btn_back, btn_add_drink;
    private Button btn_checkout;
    private TextView txt_table_name;

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
        txt_table_name = findViewById(R.id.txt_table_name);

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
        txt_table_name.setText("Bàn " + tableNumber);
        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_add_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableDetailActivity.this, DrinkActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("number", tableNumber);
                intent.putExtra("table_number", bundle);
                startActivityForResult(intent, 1);
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
            updateRcv();
        }
    }

    @Override
    public void onTableFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateRcv() {
        if (mTableDetailAdapter == null) {
            mTableDetailAdapter = new TableDetailAdapter(this, mTableDetail);
            mRecyclerView.setAdapter(mTableDetailAdapter);
        } else {
            mTableDetailAdapter.notifyDataSetChanged();
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
            finish();
        }
    }

    @Override
    public void onDrinkSuccess() {
        Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrinkFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("VAOOOOooooooooooooooooooooooooo");

        if (requestCode == 1) {
            if (requestCode == Activity.RESULT_OK) {
                System.out.println("VAOOOOooooooooooooooooooooooooo RESULT OK");
            }

            if (requestCode == Activity.RESULT_CANCELED) {
                System.out.println("VAOOOOooooooooooooooooooooooooo RESULT CANCELED");
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("on restart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("on start");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
