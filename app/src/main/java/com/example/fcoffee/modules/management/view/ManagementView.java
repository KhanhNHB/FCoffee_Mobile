package com.example.fcoffee.modules.management.view;

public interface ManagementView {
    void onDrinkSuccess();
    void onCheckoutSuccess();
    void onRemoveDrinkSuccess();
    void onDrinkFail(String message);

    void onDiscountSuccess();
    void onDiscountFail(String message);

}
