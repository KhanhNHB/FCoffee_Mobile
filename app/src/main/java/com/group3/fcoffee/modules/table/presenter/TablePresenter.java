package com.group3.fcoffee.modules.table.presenter;

import com.group3.fcoffee.modules.table.repositories.TableRepository;
import com.group3.fcoffee.modules.table.view.TableView;

public class TablePresenter {
    private TableView mTableView;
    private TableRepository mTableRepository;

    public TablePresenter(TableView tableView) {
        mTableView = tableView;
        mTableRepository = new TableRepository();
    }

    public void getAll() {
        mTableRepository.getAll(mTableView);
    }

    public void getByNumber(int tableNumber) {
        mTableRepository.getByNumber(tableNumber, mTableView);
    }
}
