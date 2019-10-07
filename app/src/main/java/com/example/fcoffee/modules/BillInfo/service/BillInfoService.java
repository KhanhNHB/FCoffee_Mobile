package com.example.fcoffee.modules.BillInfo.service;

import com.example.fcoffee.modules.BillInfo.model.DTOresponse.DTOBillInfoList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BillInfoService {

    @GET("/api/manager/infobill/{billId}")
    Call<DTOBillInfoList> getBillInfoByBillId(@Path("billId") int billId);
}
