package com.group3.fcoffee.modules.management.services;

import com.group3.fcoffee.modules.dink.model.DTOrequest.RequestBodyDrink;
import com.group3.fcoffee.modules.table.model.DTOresponse.TableDetailData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ManagementService {

    @GET("/api/manager/{tableNumber}")
    Call<TableDetailData> getTableByNumber(@Path("tableNumber") int tableNumber);

//    @GET("/manager/getBill")
//    Call<ResponseBody> getBill();

    @POST("/api/manager/addDrinkForTable")
    Call<ResponseBody> addDrinkForTable(@Body RequestBodyDrink requestBodyDrink);

    @PUT("/api/manager/addCount/{billInfo}")
    Call<ResponseBody> addCount(@Path("billInfo") int billInfo);

    @PUT("/api/manager/subCount/{billInfo}")
    Call<ResponseBody> subCount(@Path("billInfo") int billInfo);

    @PUT("api/manager/addDiscount/{billId}/{discount}")
    Call<ResponseBody> addDiscount(@Path("billId") int billId, @Path("discount") float discount);

    @PUT("/api/manager/payment/{billId}")
    Call<ResponseBody> payment(@Path("billId") int billId);

    @PUT("/api/manager/switchTables/{tableNumberFrom}/{tableNumberTo}")
    Call<ResponseBody> switchTable(@Path("tableNumberFrom") int tableNumberFrom, @Path("tableNumberTo") int tableNumberTo);

    @DELETE("/api/manager/removeDrink/{billInfoId}/{isLastItem}")
    Call<ResponseBody> delete(@Path("billInfoId") int BillInfoId, @Path("isLastItem") boolean isLastItem);
}
