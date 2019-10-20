package com.group3.fcoffee.modules.dink.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.modules.dink.adapter.DrinkAdapter;
import com.group3.fcoffee.modules.dink.model.DTO.Drink;
import com.group3.fcoffee.modules.dink.model.DTOrequest.RequestBodyDrink;
import com.group3.fcoffee.modules.dink.model.DTOresponse.DrinkDTO;
import com.group3.fcoffee.modules.dink.model.DTOresponse.DrinkData;
import com.group3.fcoffee.modules.dink.presenter.DrinkPresenter;
import com.group3.fcoffee.modules.dink.view.DrinkView;

import java.util.ArrayList;
import java.util.List;

public class DrinkActivity extends AppCompatActivity implements DrinkView {

    private DrinkPresenter mDrinkPresenter;
    private DrinkData mDrinks;
    private RecyclerView mRecycleDrink;
    private DrinkAdapter mDrinkAdapter;
    private ImageView btn_back;
    private Button btn_check_in;
    private int tableNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        initView();
        initData();
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        btn_check_in = findViewById(R.id.btn_check_in);

        mRecycleDrink = findViewById(R.id.rcv_drinks);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        mRecycleDrink.setLayoutManager(gridLayoutManager);
    }

    private void initData() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mDrinkPresenter = new DrinkPresenter(this);
        mDrinkPresenter.GetAll();
    }

    @Override
    public void onDinkSuccessGetById(DrinkDTO drinkDTO) {
        // TODO
    }

    @Override
    public void onDrinkSuccessGetAll(DrinkData drinks) {
        if (drinks != null) {
            mDrinks = new DrinkData();
            mDrinks = drinks;
            updateRcv();
        }
    }

    @Override
    public void onDrinkSuccessGetByCategoryId(DrinkData drinks) {
    }

    @Override
    public void onDrinkSuccessCheckIn() {
        Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onDrinkFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateRcv() {
        mDrinkAdapter = new DrinkAdapter(this, mDrinks);
        mRecycleDrink.setAdapter(mDrinkAdapter);
    }

    public void clickToCheckIn(View view) {
        if (mDrinks == null || mDrinks.getmDrink().size() == 0) {
            Toast.makeText(this, "Vui lòng chọn sản phẩm", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Drink> listDrink = new ArrayList<>();
        for (int i = 0; i < mDrinks.getmDrink().size(); i++) {
            int count = mDrinks.getmDrink().get(i).getCount();
            if (count > 0) {
                listDrink.add(new Drink(count, mDrinks.getmDrink().get(i).getId()));
            }
        }

        if (listDrink.size() == 0 || listDrink == null) {
            Toast.makeText(this, "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("table_number");

        tableNumber = bundle.getInt("number");

        RequestBodyDrink requestBodyDrink = new RequestBodyDrink();
        requestBodyDrink.setListDrink(listDrink);
        requestBodyDrink.setTableNumber(tableNumber);
        mDrinkPresenter.AddDrinkForTable(requestBodyDrink);
    }
}