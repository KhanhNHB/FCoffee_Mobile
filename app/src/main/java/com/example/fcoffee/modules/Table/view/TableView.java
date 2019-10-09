package com.example.fcoffee.modules.Table.view;

import com.example.fcoffee.modules.Table.model.DTOresponse.TableDetailData;
import com.example.fcoffee.modules.Table.model.DTOresponse.TableData;


public interface TableView {
    void onTableSuccessGetAll(TableData tables);
    void onTableSuccessGetByNumber(TableDetailData tableDetail);
    void onTableFail(String message);

}
