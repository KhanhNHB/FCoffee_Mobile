package com.example.fcoffee.modules.bill.model.DTOresponse;

import com.example.fcoffee.modules.bill.model.Bill;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DTOBillList implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Bill> billList;

    public DTOBillList() {
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public DTOBillList(List<Bill> billList) {
        this.billList = billList;
    }
}
