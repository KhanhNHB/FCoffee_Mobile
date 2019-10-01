package com.example.fcoffee.modules.Table.view;

import com.example.fcoffee.modules.Table.model.DTOresponse.DTOTableList;


public interface TableView {
    void onTableSuccessGetAll(DTOTableList dto);
    void onTableFail(String message);
}
