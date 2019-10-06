package com.example.fcoffee.modules.Table.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.Drink.activity.DrinkActivity;
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkDTO;
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
    private DrinkDTO mDrink;
    private ImageView btn_back, btn_add_drink;

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

        mRecyclerView = findViewById(R.id.rcv_table_detail);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("anonymouse_number");
        tableNumber = bundle.getInt("table_number");
    }

    private void initData() {
        mTablePresenter = new TablePresenter(this);
        mTablePresenter.getByNumber(tableNumber);

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
                startActivity(intent);
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
        }
    }

    @Override
    public void onDinkSuccessGetById(DrinkDTO drink) {
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
