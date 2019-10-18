package com.group3.fcoffee.modules.dink.presenter;

import com.group3.fcoffee.modules.dink.model.DTOrequest.RequestBodyDrink;
import com.group3.fcoffee.modules.dink.repositories.DrinkRepository;
import com.group3.fcoffee.modules.dink.view.DrinkView;


public class DrinkPresenter {
    private DrinkRepository mDrinkRepository;
    private DrinkView mDrinkView;

    public DrinkPresenter(DrinkView drinkView) {
        mDrinkView = drinkView;
        mDrinkRepository = new DrinkRepository();
    }

    public void GetById(int drinkId) {
        mDrinkRepository.getById(drinkId, mDrinkView);
    }

    public void GetByCategoryId(int categoryId) {
        mDrinkRepository.getByCategoryId(categoryId, mDrinkView);
    }

    public void GetAll() {
        mDrinkRepository.getAll(mDrinkView);
    }

    public void AddDrinkForTable(RequestBodyDrink requestBodyDrink) {
        mDrinkRepository.AddDrinkForTable(requestBodyDrink, mDrinkView);
    }
}
