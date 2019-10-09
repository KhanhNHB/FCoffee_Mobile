package com.example.fcoffee.modules.dink.services;

import com.example.fcoffee.modules.dink.model.DTOresponse.DrinkDTO;
import com.example.fcoffee.modules.dink.model.DTOresponse.DrinkData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DrinkService {

    @GET("/api/drink/{drinkId}")
    Call<DrinkDTO> getById(@Path("drinkId") int drinkId);

    @GET("/api/drink")
    Call<DrinkData> getDrinks();
}
