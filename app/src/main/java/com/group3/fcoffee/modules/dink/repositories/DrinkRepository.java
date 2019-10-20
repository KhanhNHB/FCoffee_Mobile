package com.group3.fcoffee.modules.dink.repositories;

import android.util.Log;

import com.group3.fcoffee.common.Error;
import com.group3.fcoffee.modules.dink.model.DTOrequest.RequestBodyDrink;
import com.group3.fcoffee.modules.dink.model.DTOresponse.DrinkDTO;
import com.group3.fcoffee.modules.dink.model.DTOresponse.DrinkData;
import com.group3.fcoffee.modules.dink.services.DrinkService;
import com.group3.fcoffee.modules.dink.view.DrinkView;
import com.group3.fcoffee.modules.management.services.ManagementService;
import com.group3.fcoffee.utils.APIUtils;

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
                    try {
                        DrinkDTO drink = response.body();
                        drinkView.onDinkSuccessGetById(drink);
                    } catch (Exception ex) {
                        drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, ex.getMessage());
                    }
                } else {
                    drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                    Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                }
            }

            @Override
            public void onFailure(Call<DrinkDTO> call, Throwable t) {
                drinkView.onDrinkFail(t.getMessage());
                Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
            }
        });
    }

    public void getByCategoryId(final int categoryId, final DrinkView drinkView) {
        Call<DrinkData> call = mDrinkService.getDrinkByCategoryId(categoryId);
        call.enqueue(new Callback<DrinkData>() {
            @Override
            public void onResponse(Call<DrinkData> call, Response<DrinkData> response) {
                if (response.code() == 200) {
                    try {
                        DrinkData drinks = response.body();
                        drinkView.onDrinkSuccessGetByCategoryId(drinks);
                    } catch (Exception ex) {
                        drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, ex.getMessage());
                    }
                } else {
                    drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                    Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                }
            }

            @Override
            public void onFailure(Call<DrinkData> call, Throwable t) {
                drinkView.onDrinkFail(t.getMessage());
                Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
            }
        });
    }

    public void getAll(final DrinkView drinkView) {
        Call<DrinkData> call = mDrinkService.getDrinks();
        call.enqueue(new Callback<DrinkData>() {
            @Override
            public void onResponse(Call<DrinkData> call, Response<DrinkData> response) {
                if (response.code() == 200) {
                    try {
                        DrinkData drinks = response.body();
                        drinkView.onDrinkSuccessGetAll(drinks);
                    } catch (Exception ex) {
                        drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, ex.getMessage());
                    }
                } else {
                    drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                    Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                }
            }

            @Override
            public void onFailure(Call<DrinkData> call, Throwable t) {
                drinkView.onDrinkFail(t.getMessage());
                Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
            }
        });
    }

    public void AddDrinkForTable(RequestBodyDrink requestBodyDrinks, final DrinkView drinkView) {
            Call<ResponseBody> call = mManagementService.addDrinkForTable(requestBodyDrinks);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        try {
                            drinkView.onDrinkSuccessCheckIn();
                        } catch (Exception ex) {
                            drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                            Log.d(Error.TAG_ERROR_RESPONSE, ex.getMessage());
                        }
                    } else {
                        drinkView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    drinkView.onDrinkFail(t.getMessage());
                    Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
                }
            });
    }
}
