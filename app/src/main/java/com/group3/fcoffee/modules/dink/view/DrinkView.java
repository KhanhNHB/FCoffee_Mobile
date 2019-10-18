package com.group3.fcoffee.modules.dink.view;

import com.group3.fcoffee.modules.dink.model.DTOresponse.DrinkDTO;
import com.group3.fcoffee.modules.dink.model.DTOresponse.DrinkData;

public interface DrinkView {
    void onDinkSuccessGetById(DrinkDTO drinkDTO);
    void onDrinkSuccessGetAll(DrinkData drinks);
    void onDrinkSuccessGetByCategoryId(DrinkData drinks);
    void onDrinkSuccessCheckIn();
    void onDrinkFail(String message);
}
