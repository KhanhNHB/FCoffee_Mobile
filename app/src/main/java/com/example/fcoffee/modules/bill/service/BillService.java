package com.example.fcoffee.modules.bill.service;

import com.example.fcoffee.modules.bill.model.DTOresponse.DTOBillList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BillService {

    @GET("/api/manager/getbill")
    Call<DTOBillList> getByToken();
}
