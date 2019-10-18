package com.group3.fcoffee.modules.bill.view;

import com.group3.fcoffee.modules.bill.model.DTOresponse.DTOBillList;

public interface BillView {
    void onBillSuccessGetAllByUsername(DTOBillList dto);
    void onBillFail(String message);
}
