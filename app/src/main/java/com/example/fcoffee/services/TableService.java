package com.example.fcoffee.services;

import com.example.fcoffee.models.Table;
import com.example.fcoffee.models.TableList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TableService {

    @GET("/api/table")
    Call<TableList> get();

    @POST("/api/table")
    Call<Table> update(@Body Table table);
}
