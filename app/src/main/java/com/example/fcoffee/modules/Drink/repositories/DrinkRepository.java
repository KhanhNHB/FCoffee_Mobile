package com.example.fcoffee.modules.Drink.repositories;

import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkData;
import com.example.fcoffee.modules.Drink.services.DrinkService;
import com.example.fcoffee.modules.Drink.view.DrinkView;
import com.example.fcoffee.utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinkRepository {
    DrinkService mDrinkService;

    public DrinkRepository() {
        mDrinkService = APIUtils.getDrinkService();
    }

    public void getById(final int id, final DrinkView drinkView) {
        Call<DrinkData> callDrinkService = mDrinkService.getById(id);
        callDrinkService.enqueue(new Callback<DrinkData>() {
            @Override
            public void onResponse(Call<DrinkData> call, Response<DrinkData> response) {
                if (response.code() == 200) {
                    DrinkData drink = new DrinkData();
                    drink = response.body();
                    drinkView.onDinkSuccessGetById(drink);
                } else {

                }
            }

            @Override
            public void onFailure(Call<DrinkData> call, Throwable t) {

            }
        });
    }
}
