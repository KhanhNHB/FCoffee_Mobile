package com.group3.fcoffee.views;

import com.group3.fcoffee.models.BillInfo;

import java.util.List;

public interface BillInfoView {
    void onBillsInfoSuccess(List<BillInfo> billsInfo);
    void onBillInfoSuccess(BillInfo billInfo);
    void onBillInfoFail(String message);
}