package com.example.fcoffee.modules.bill.view;

import com.example.fcoffee.modules.bill.model.DTOresponse.DTOBillList;

public interface BillView {
    void onBillSuccessGetAllByUsername(DTOBillList dto);
    void onBillFail(String message);
}
