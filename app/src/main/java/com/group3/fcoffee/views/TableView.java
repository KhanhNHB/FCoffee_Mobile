package com.group3.fcoffee.views;

import com.group3.fcoffee.models.Table;
import com.group3.fcoffee.view_models.TableDetail;

import java.util.List;

public interface TableView {
    void onTableDeleteProductItem(Boolean result);
    void onTablesSuccess(List<Table> tables);
    void onTableDetailSuccess(TableDetail tableDetail);
    void onTableSuccess(Table table);
    void onTableCheckAvailableSuccess(Boolean result);
    void onTableFail(String message);
}