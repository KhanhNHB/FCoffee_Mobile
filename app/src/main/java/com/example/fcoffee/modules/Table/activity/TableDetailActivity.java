package com.example.fcoffee.modules.Table.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkData;
import com.example.fcoffee.modules.Table.adapter.TableDetailAdapter;
import com.example.fcoffee.modules.Table.model.DTOresponse.TableDetailData;
import com.example.fcoffee.modules.Table.model.DTOresponse.TableData;
import com.example.fcoffee.modules.Table.presenter.TablePresenter;
import com.example.fcoffee.modules.Table.view.TableView;

public class TableDetailActivity extends AppCompatActivity implements TableView {

    private RecyclerView mRecyclerView;
    private TableDetailData mTableDetail;
    private TableDetailAdapter mTableDetailAdapter;
    private int tableNumber;
    private TablePresenter mTablePresenter;
    private DrinkData mDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_detail);

        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rcv_table_detail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("anonymouse_number");
        tableNumber = bundle.getInt("table_number");
    }

    private void initData() {
        mTablePresenter = new TablePresenter(this);
        mTablePresenter.getByNumber(tableNumber);
    }

    @Override
    public void onTableSuccessGetAll(TableData dto) {
        // TODO- TableFragment
    }

    @Override
    public void onTableSuccessGetByNumber(TableDetailData tableDetail) {
        if (tableDetail != null) {
            mTableDetail = tableDetail;
        }
    }

    @Override
    public void onDinkSuccessGetById(DrinkData drink) {
        if (drink != null) {
            mDrink = drink;
            updateRcv();
        }
    }

    @Override
    public void onTableFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateRcv() {
        if (mTableDetailAdapter == null) {
            mTableDetailAdapter = new TableDetailAdapter(this, mTableDetail, mDrink);
            mRecyclerView.setAdapter(mTableDetailAdapter);
        } else {
            mTableDetailAdapter.notifyDataSetChanged();
        }
    }

}
