package com.example.fcoffee.modules.Drink.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.Drink.adapter.DrinkAdapter;
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkDTO;
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkData;
import com.example.fcoffee.modules.Drink.presenter.DrinkPresenter;
import com.example.fcoffee.modules.Drink.view.DrinkView;

public class DrinkActivity extends AppCompatActivity implements DrinkView {

    private DrinkPresenter mDrinkPresenter;
    private DrinkData mDrinks;
    private RecyclerView mRecycleDrink;
    private DrinkAdapter mDrinkAdapter;
    private ImageView btn_back;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drink);

        initView();
        initData();
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        mRecycleDrink = findViewById(R.id.rcv_drinks);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        mRecycleDrink.setLayoutManager(gridLayoutManager);

        mDrinkPresenter = new DrinkPresenter(this);
    }

    private void initData() {
        mDrinkPresenter.GetAll();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onDinkSuccessGetById(DrinkDTO drinkDTO) {
        // TODO
    }

    @Override
    public void onDrinkSuccessGetAll(DrinkData drinks) {
        if (drinks != null) {
            mDrinks = drinks;
            updateRcv();
        }
    }

    @Override
    public void onDrinkFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateRcv() {
        if (mDrinkAdapter == null) {
            mDrinkAdapter = new DrinkAdapter(this, mDrinks);
            mRecycleDrink.setAdapter(mDrinkAdapter);
        } else {
            mDrinkAdapter.notifyDataSetChanged();
        }
    }
}
