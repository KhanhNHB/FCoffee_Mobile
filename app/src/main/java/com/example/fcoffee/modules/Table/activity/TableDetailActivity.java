package com.example.fcoffee.modules.Table.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.Table.adapter.TableDetailAdapter;
import com.example.fcoffee.modules.Table.model.TableDetail;
import com.example.fcoffee.modules.Management.services.ManagementService;
import com.example.fcoffee.utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableDetailActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TableDetailActivity mTableDetailActivity;
    private TableDetail mTableDetail;
    private ManagementService mService;
    private TableDetailAdapter mTableDetailAdapter;
    private int tableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_detail);

        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rcv_table_detail);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("anonymouse_number");

        tableNumber = bundle.getInt("table_number");

        mService = APIUtils.getManagerService();
    }

    private void initData() {
        try {
            Call<TableDetail> call = mService.getTableByNumber(tableNumber);

            call.enqueue(new Callback<TableDetail>() {
                @Override
                public void onResponse(Call<TableDetail> call, Response<TableDetail> response) {
                    if (response.code() == 200) {
                        mTableDetail = response.body();

                        updateRcv();
                    } else {
                        Toast.makeText(TableDetailActivity.this, "Erorr: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TableDetail> call, Throwable t) {
                    Toast.makeText(TableDetailActivity.this, "Erorr: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateRcv() {
        if (mTableDetailAdapter == null) {
            mTableDetailAdapter = new TableDetailAdapter(this, mTableDetail);
            mRecyclerView.setAdapter(mTableDetailAdapter);
        } else {
            mTableDetailAdapter.notifyDataSetChanged();
        }
    }

}
