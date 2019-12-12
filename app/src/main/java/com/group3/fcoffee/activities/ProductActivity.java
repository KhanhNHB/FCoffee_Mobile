package com.group3.fcoffee.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.adapters.ProductAdapter;
import com.group3.fcoffee.models.Product;
import com.group3.fcoffee.models.Table;
import com.group3.fcoffee.presenters.ProductPresenter;
import com.group3.fcoffee.presenters.TablePresenter;
import com.group3.fcoffee.view_models.ProductOrder;
import com.group3.fcoffee.view_models.TableDetail;
import com.group3.fcoffee.views.ProductView;
import com.group3.fcoffee.views.TableView;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements ProductView, TableView {

    private ProductPresenter mProductPresenter;
    private RecyclerView mRcvDrinks;
    private ProductAdapter mProductAdapter;
    private List<Product> products;
    private ImageView mBtnBack;
    private Button mBtnCheckIn;
    private TablePresenter mTablePresenter;
    private String mTableId;
    private List<ProductOrder> mProductOrders;
    private Intent intent;
    private SharedPreferences pref;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initView();
        initData();
    }

    private void initView() {
        this.mBtnBack = findViewById(R.id.btn_back);
        this.mBtnCheckIn = findViewById(R.id.btn_check_in);

        this.intent = getIntent();
        Bundle bundle = intent.getBundleExtra("table");
        this.mTableId = bundle.getString("table_id");

        pref = getSharedPreferences("MyRef", MODE_PRIVATE);
        token = pref.getString("token", null);
    }

    private void initData() {
        this.mProductPresenter = new ProductPresenter(this);
        this.mProductPresenter.getDrinks(token);

        this.mTablePresenter = new TablePresenter(this);

        this.mRcvDrinks = findViewById(R.id.rcv_drinks);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 1);
        this.mRcvDrinks.setLayoutManager(gridLayoutManager);

        this.mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onProductsSuccess(List<Product> product) {
        this.products = product;
        updateRcv();
    }

    @Override
    public void onProductSuccess(Product product) {
    }

    @Override
    public void onProductFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateRcv() {
        if (this.mProductAdapter == null) {
            this.mProductAdapter = new ProductAdapter(this, this.products);
            this.mRcvDrinks.setAdapter(this.mProductAdapter);
        } else {
            this.mProductAdapter.notifyDataSetChanged();
        }
    }

    public void clickToCheckIn(View view) {
        if (this.mProductOrders == null) {
            this.mProductOrders = new ArrayList<>();
        }
        for (Product product : this.products) {
            if (product.getQuantityOrder() > 0) {
                ProductOrder productOrder = new ProductOrder(product.getId(), product.getQuantityOrder(), product.getPrice(), false, false);
                System.out.println(productOrder);
                this.mProductOrders.add(productOrder);
            }
        }
        this.mTablePresenter.checkInTable(this.mTableId, token, this.mProductOrders);
    }

    @Override
    public void onTableDeleteProductItem(Boolean result) {

    }

    @Override
    public void onTablesSuccess(List<Table> tables) {

    }

    @Override
    public void onTableDetailSuccess(TableDetail tableDetail) {

    }

    @Override
    public void onTableSuccess(Table table) {

    }


    @Override
    public void onTableCheckAvailableSuccess(Boolean result) {
//        ArrayList<ProductOrder> arrayList = (ArrayList<ProductOrder>) this.mProductOrders;
//        this.intent.putParcelableArrayListExtra("productOrders", arrayList);
        this.setResult(RESULT_OK, this.intent);
        finish();
    }

    @Override
    public void onTableFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}