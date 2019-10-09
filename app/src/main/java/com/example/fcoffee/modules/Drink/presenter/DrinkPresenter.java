package com.example.fcoffee.modules.Drink.presenter;

import com.example.fcoffee.modules.Drink.model.DTOrequest.RequestBodyDrink;
import com.example.fcoffee.modules.Drink.repositories.DrinkRepository;
import com.example.fcoffee.modules.Drink.view.DrinkView;


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

    public void GetAll() {
        mDrinkRepository.getAll(mDrinkView);
    }

    public void AddDrinkForTable(RequestBodyDrink requestBodyDrink) {
        mDrinkRepository.AddDrinkForTable(requestBodyDrink, mDrinkView);
    }
}
