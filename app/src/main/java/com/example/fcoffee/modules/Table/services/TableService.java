package com.example.fcoffee.modules.Table.services;

import com.example.fcoffee.modules.Table.model.Table;
import com.example.fcoffee.modules.Table.model.TableList;

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
