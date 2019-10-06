package com.example.fcoffee.modules.Drink.repositories;

import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkDTO;
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkData;
import com.example.fcoffee.modules.Drink.services.DrinkService;
import com.example.fcoffee.modules.Drink.view.DrinkView;
import com.example.fcoffee.utils.APIUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinkRepository {
    DrinkService mDrinkService;

    public DrinkRepository() {
        mDrinkService = APIUtils.getDrinkService();
    }

    public void getById(final int id, final DrinkView drinkView) {
        Call<DrinkDTO> callDrinkService = mDrinkService.getById(id);
        callDrinkService.enqueue(new Callback<DrinkDTO>() {
            @Override
            public void onResponse(Call<DrinkDTO> call, Response<DrinkDTO> response) {
                if (response.code() == 200) {
                    DrinkDTO drink = new DrinkDTO();
                    drink = response.body();
                    drinkView.onDinkSuccessGetById(drink);
                } else {

                }
            }

            @Override
            public void onFailure(Call<DrinkDTO> call, Throwable t) {

            }
        });
    }

    public void getAll(final DrinkView drinkView) {
        Call<DrinkData> call = mDrinkService.getDrinks();
        call.enqueue(new Callback<DrinkData>() {
            @Override
            public void onResponse(Call<DrinkData> call, Response<DrinkData> response) {
                if (response.code() == 200) {
                    DrinkData drinks = new DrinkData();
                    drinks = response.body();
                    drinkView.onDrinkSuccessGetAll(drinks);
                } else {
                    drinkView.onDrinkFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<DrinkData> call, Throwable t) {
                drinkView.onDrinkFail(t.getMessage());
            }
        });
    }
}
