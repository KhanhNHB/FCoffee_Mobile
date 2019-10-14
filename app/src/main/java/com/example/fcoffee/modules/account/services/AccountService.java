package com.example.fcoffee.modules.account.services;

import com.example.fcoffee.modules.account.models.Account;
import com.example.fcoffee.modules.account.models.DTOAccountResponse.DTOAccountResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountService {

    @POST("/login")
    Call<ResponseBody> checkLogin(@Body RequestBody login);

    @GET("/")
    Call<List<Account>> get();

    @GET("/api/account/getInfo")
    Call<DTOAccountResponse> getInfo();

    @POST("/")
    Call<Account> create(@Body Account account);

    @PUT("/")
    Call<Account> update(@Body Account account);

    @DELETE("/{username}")
    Call<Account> delete(@Path("username") String username);

    @DELETE("/undelete/{username}")
    Call<Account> undelete(@Path("username") String username);
}
