package com.group3.fcoffee.services;

import com.group3.fcoffee.utils.ConfigAPI;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FCoffeeServices {

    @POST(ConfigAPI.Api.SIGN_IN)
    Call<ResponseBody> signIn(@Body RequestBody requestBody);

    @GET(ConfigAPI.Api.GET_ACCOUNT)
    Call<ResponseBody> getAccount(@Path("username") String username, @Header("Authorization") String token);

    @GET(ConfigAPI.Api.GET_TABLES)
    Call<ResponseBody> getTables(@Header("Authorization") String token);

    @GET(ConfigAPI.Api.GET_TABLE)
    Call<ResponseBody> getTableDetail(@Path("id") String id, @Header("Authorization") String token);

    @GET(ConfigAPI.Api.GET_TABLE)
    Call<ResponseBody> getTable(@Path("id") String id, @Header("Authorization") String token);

    @PUT(ConfigAPI.Api.PUT_TABLE_CHECK_IN)
    Call<ResponseBody> getCheckIn(@Path("id") String id, @Header("Authorization") String token, @Body RequestBody requestBody);

    @PUT(ConfigAPI.Api.PUT_TABLE_CHECK_OUT)
    Call<ResponseBody> getCheckOut(@Path("id") String id, @Header("Authorization") String token);

    @PUT(ConfigAPI.Api.DELETE_PRODUCT_ITEM_IN_TABLE)
    Call<ResponseBody> deleteProductItem(@Path("id") String id, @Header("Authorization") String token, @Body RequestBody requestBody);

    @GET(ConfigAPI.Api.GET_BILLS)
    Call<ResponseBody> getBills(@Header("Authorization") String token);

    @GET(ConfigAPI.Api.GET_BILLS_CURRENT_USER)
    Call<ResponseBody> getBillsByCurrentStaff(@Header("Authorization") String token);

    @GET(ConfigAPI.Api.GET_BILL)
    Call<ResponseBody> getBill(@Path("id") String id, @Header("Authorization") String token);

    @GET(ConfigAPI.Api.GET_BILL_INFOS)
    Call<ResponseBody> getBillInfos(@Header("Authorization") String token);

    @GET(ConfigAPI.Api.GET_BILL_INFO)
    Call<ResponseBody> getBillInfo(@Path("id") String id, @Header("Authorization") String token);

    @GET(ConfigAPI.Api.GET_PRODUCTS)
    Call<ResponseBody> getProducts(@Header("Authorization") String token);

    @GET(ConfigAPI.Api.GET_PRODUCT)
    Call<ResponseBody> getProduct(@Path("id") String id, @Header("Authorization") String token);
}