package com.example.fcoffee.modules.table.services;

import com.example.fcoffee.modules.table.model.DTOrequest.Table;
import com.example.fcoffee.modules.table.model.DTOresponse.TableData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TableService {

    @GET("/api/table")
    Call<TableData> get();

    @POST("/api/table")
    Call<Table> update(@Body Table table);
}
