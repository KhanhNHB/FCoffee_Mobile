package com.example.fcoffee.modules.table.view;

import com.example.fcoffee.modules.table.model.DTOresponse.TableDetailData;
import com.example.fcoffee.modules.table.model.DTOresponse.TableData;


public interface TableView {
    void onTableSuccessGetAll(TableData tables);
    void onTableSuccessGetByNumber(TableDetailData tableDetail);
    void onTableFail(String message);

}
