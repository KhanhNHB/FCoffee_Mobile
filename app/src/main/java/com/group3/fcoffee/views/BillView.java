package com.group3.fcoffee.views;

import com.group3.fcoffee.models.Bill;

import java.util.List;

public interface BillView {
    void onBillSuccess(Bill bill);
    void onBillsSuccess(List<Bill> bills);
    void onSuccess(Boolean result);
    void onBillFail(String message);
}
