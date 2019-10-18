package com.group3.fcoffee.modules.billInfo.view;

import com.group3.fcoffee.modules.billInfo.model.DTOresponse.DTOBillInfoList;

public interface BillInfoView {
    void onBillInfoSuccessGetAllByBillId(DTOBillInfoList dto);
    void onBillFail(String message);
}
