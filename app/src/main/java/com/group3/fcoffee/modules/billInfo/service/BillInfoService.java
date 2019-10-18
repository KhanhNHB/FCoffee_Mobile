package com.group3.fcoffee.modules.billInfo.service;

import com.group3.fcoffee.modules.billInfo.model.DTOresponse.DTOBillInfoList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BillInfoService {

    @GET("/api/manager/infobill/{billId}")
    Call<DTOBillInfoList> getBillInfoByBillId(@Path("billId") int billId);
}
