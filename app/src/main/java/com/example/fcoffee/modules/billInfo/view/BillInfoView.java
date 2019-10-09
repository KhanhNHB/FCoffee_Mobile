package com.example.fcoffee.modules.billInfo.view;

import com.example.fcoffee.modules.billInfo.model.DTOresponse.DTOBillInfoList;

public interface BillInfoView {
    void onBillInfoSuccessGetAllByBillId(DTOBillInfoList dto);
    void onBillFail(String message);
}
