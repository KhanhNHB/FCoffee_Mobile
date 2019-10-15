package com.example.fcoffee.modules.dink.view;

import com.example.fcoffee.modules.dink.model.DTOresponse.DrinkDTO;
import com.example.fcoffee.modules.dink.model.DTOresponse.DrinkData;

public interface DrinkView {
    void onDinkSuccessGetById(DrinkDTO drinkDTO);
    void onDrinkSuccessGetAll(DrinkData drinks);
    void onDrinkSuccessGetByCategoryId(DrinkData drinks);
    void onDrinkSuccessCheckIn();
    void onDrinkFail(String message);
}
