package com.example.fcoffee.modules.dink.model.DTOresponse;

import com.example.fcoffee.modules.dink.model.DTOrequest.DTODrink;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrinkDTO {

    @SerializedName("data")
    @Expose
    private DTODrink mDrink;

   public DrinkDTO() {}

    public DrinkDTO(DTODrink mDrink) {
        this.mDrink = mDrink;
    }

    public DTODrink getmDrink() {
        return mDrink;
    }
}
