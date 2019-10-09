package com.example.fcoffee.modules.Drink.view;

import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkDTO;
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkData;

public interface DrinkView {
    void onDinkSuccessGetById(DrinkDTO drinkDTO);
    void onDrinkSuccessGetAll(DrinkData drinks);
    void onDrinkSuccessCheckIn();
    void onDrinkFail(String message);
}
