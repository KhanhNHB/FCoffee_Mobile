package com.example.fcoffee.modules.Management.services;

import com.example.fcoffee.modules.Table.model.DTOresponse.TableDetailData;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ManagementService {

    @GET("/api/manager/{tableNumber}")
    Call<TableDetailData> getTableByNumber(@Path("tableNumber") int tableNumber);

//    @GET("/manager/getBill")
//    Call<> getById(@Path("username") String username);

    @POST("/api/manager/addDrinkForTable")
    Call<ResponseBody> create(@Body RequestBody model);

//    @PUT("/manager/addCount/{billInfoId}")
//    Call<> update(@Body Account account);

//    @DELETE("/manager/removeDrink{billInfoId}")
//    Call<> delete(@Path(@Path(billInfoId) int BillInfoId);
}
