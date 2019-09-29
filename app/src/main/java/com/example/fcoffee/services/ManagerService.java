package com.example.fcoffee.services;

import com.example.fcoffee.models.TableDetail;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ManagerService {

    @GET("/api/manager/{tableNumber}")
    Call<TableDetail> getTableByNumber(@Path("tableNumber") int tableNumber);

//    @GET("/manager/getBill")
//    Call<> getById(@Path("username") String username);

    @POST("/api/manager/addDrinkForTable")
    Call<ResponseBody> create(@Body RequestBody model);

//    @PUT("/manager/addCount/{billInfoId}")
//    Call<> update(@Body Account account);

//    @DELETE("/manager/removeDrink{billInfoId}")
//    Call<> delete(@Path(@Path(billInfoId) int BillInfoId);
}
