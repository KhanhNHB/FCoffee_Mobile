package com.example.fcoffee.modules.billInfo.model.DTOresponse;

import com.example.fcoffee.modules.billInfo.model.BillInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DTOBillInfoList {

    @SerializedName("data")
    @Expose
    private List<BillInfo> billInfoList;

    public DTOBillInfoList() {
    }

    public DTOBillInfoList(List<BillInfo> billInfoList) {
        this.billInfoList = billInfoList;
    }

    public List<BillInfo> getBillInfoList() {
        return billInfoList;
    }

    public void setBillInfoList(List<BillInfo> billInfoList) {
        this.billInfoList = billInfoList;
    }
}
