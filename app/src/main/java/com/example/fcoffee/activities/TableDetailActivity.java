package com.example.fcoffee.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.adaptes.TableDetailAdapter;
import com.example.fcoffee.models.TableDetail;
import com.example.fcoffee.services.ManagerService;
import com.example.fcoffee.utils.APIUtils;

import java.nio.file.Path;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableDetailActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TableDetailActivity mTableDetailActivity;
    private TableDetail mTableDetail;
    private ManagerService mService;
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
