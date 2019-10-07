package com.example.fcoffee.modules.BillInfo.view;

import com.example.fcoffee.modules.BillInfo.model.DTOresponse.DTOBillInfoList;

public interface BillInfoView {
    void onBillInfoSuccessGetAllByBillId(DTOBillInfoList dto);
    void onBillFail(String message);
}
