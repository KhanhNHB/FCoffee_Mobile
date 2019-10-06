package com.example.fcoffee.modules.Drink.services;

import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkDTO;
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DrinkService {

    @GET("/api/drink/{drinkId}")
    Call<DrinkDTO> getById(@Path("drinkId") int drinkId);

    @GET("/api/drink")
    Call<DrinkData> getDrinks();
}
