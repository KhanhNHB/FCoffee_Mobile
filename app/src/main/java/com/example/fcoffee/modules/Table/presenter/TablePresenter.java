package com.example.fcoffee.modules.Table.presenter;

import com.example.fcoffee.modules.Table.repositories.TableRepository;
import com.example.fcoffee.modules.Table.view.TableView;

public class TablePresenter {
    private TableView mTableView;
    private TableRepository mTableRepository;

    public TablePresenter(TableView tableView) {
        mTableView = tableView;
        mTableRepository = new TableRepository();
    }

    public void getAll(TableView tableView) {
        mTableRepository.getAll(tableView);
    }
}
