package com.group3.fcoffee.presenters;

import com.group3.fcoffee.models.Table;
import com.group3.fcoffee.repositories.FCoffeeRepository;
import com.group3.fcoffee.repositories.FCoffeeRepositoryIMP;
import com.group3.fcoffee.utils.CallBackData;
import com.group3.fcoffee.view_models.ProductOrder;
import com.group3.fcoffee.view_models.TableDeleteProductItem;
import com.group3.fcoffee.view_models.TableDetail;
import com.group3.fcoffee.views.TableView;

import java.util.List;

public class TablePresenter {
    private TableView mTableView;
    private FCoffeeRepository mFCoffeeRepository;

    public TablePresenter(TableView mTableView) {
        this.mTableView = mTableView;
        this.mFCoffeeRepository = new FCoffeeRepositoryIMP();
    }

    public void getTables(String token) {
        this.mFCoffeeRepository.getTables(token, new CallBackData<List<Table>>() {
            @Override
            public void onSuccess(List<Table> tables) {
                mTableView.onTablesSuccess(tables);
            }

            @Override
            public void onFail(String message) {
                mTableView.onTableFail(message);
            }
        });
    }

    public void getTableDetail(String id, String token) {
        this.mFCoffeeRepository.getTableDetail(id, token, new CallBackData<TableDetail>() {
            @Override
            public void onSuccess(TableDetail tableDetail) {
                mTableView.onTableDetailSuccess(tableDetail);
            }

            @Override
            public void onFail(String message) {
                mTableView.onTableFail(message);
            }
        });
    }

    public void getTable(String id, String token) {
        this.mFCoffeeRepository.getTable(id, token, new CallBackData<Table>() {
            @Override
            public void onSuccess(Table table) {
                mTableView.onTableSuccess(table);
            }

            @Override
            public void onFail(String message) {
                mTableView.onTableFail(message);
            }
        });
    }

    public void checkInTable(String id, String token, List<ProductOrder> productOrders) {
        this.mFCoffeeRepository.checkInTable(id, token, productOrders, new CallBackData<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                mTableView.onTableCheckAvailableSuccess(aBoolean);
            }

            @Override
            public void onFail(String message) {
                mTableView.onTableFail(message);
            }
        });
    }

    public void checkOutTable(String id, String token) {
        this.mFCoffeeRepository.checkOutTable(id, token, new CallBackData<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                mTableView.onTableCheckAvailableSuccess(aBoolean);
            }

            @Override
            public void onFail(String message) {
                mTableView.onTableFail(message);
            }
        });
    }

    public void deleteProductItem(String id, String token, TableDeleteProductItem tableDeleteProductItem) {
        this.mFCoffeeRepository.deleteProductItem(id, token, tableDeleteProductItem, new CallBackData<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                mTableView.onTableDeleteProductItem(aBoolean);
            }

            @Override
            public void onFail(String message) {
                mTableView.onTableFail(message);
            }
        });
    }
}