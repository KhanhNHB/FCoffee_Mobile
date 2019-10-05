package com.example.fcoffee.modules.Bill.view;

import com.example.fcoffee.modules.Bill.model.DTOresponse.DTOBillList;

public interface BillView {
    void onBillSuccessGetAllByUsername(DTOBillList dto);
    void onBillFail(String message);
}
