package com.example.fcoffee.modules.dink.repositories;

import com.example.fcoffee.common.Error;
import com.example.fcoffee.modules.dink.model.DTOrequest.RequestBodyDrink;
import com.example.fcoffee.modules.dink.model.DTOresponse.DrinkDTO;
import com.example.fcoffee.modules.dink.model.DTOresponse.DrinkData;
import com.example.fcoffee.modules.dink.services.DrinkService;
import com.example.fcoffee.modules.dink.view.DrinkView;
import com.example.fcoffee.modules.management.services.ManagementService;
import com.example.fcoffee.utils.APIUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinkRepository {
    DrinkService mDrinkService;
    ManagementService mManagementService;

    public DrinkRepository() {
        mDrinkService = APIUtils.getDrinkService();
        mManagementService = APIUtils.getManagerService();
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

    public void AddDrinkForTable(RequestBodyDrink requestBodyDrinks, final DrinkView drinkView) {

        try {
            Call<ResponseBody> call = mManagementService.addDrinkForTable(requestBodyDrinks);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        drinkView.onDrinkSuccessCheckIn();
                    } else {
                        drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
