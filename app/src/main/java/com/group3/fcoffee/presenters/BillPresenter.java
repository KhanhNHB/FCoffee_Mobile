package com.group3.fcoffee.presenters;

import com.group3.fcoffee.models.Bill;
import com.group3.fcoffee.repositories.FCoffeeRepository;
import com.group3.fcoffee.repositories.FCoffeeRepositoryIMP;
import com.group3.fcoffee.utils.CallBackData;
import com.group3.fcoffee.view_models.BillCreation;
import com.group3.fcoffee.views.BillView;

import java.util.List;

public class BillPresenter {
    private BillView mBillView;
    private FCoffeeRepository mFCoffeeRepository;

    public BillPresenter(BillView mBillView) {
        this.mBillView = mBillView;
        this.mFCoffeeRepository = new FCoffeeRepositoryIMP();
    }

    public void getBills(String token) {
        this.mFCoffeeRepository.getBills(token, new CallBackData<List<Bill>>() {
            @Override
            public void onSuccess(List<Bill> bills) {
                mBillView.onBillsSuccess(bills);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void create(BillCreation model, String token) {
        this.mFCoffeeRepository.createBill(model, token, new CallBackData<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                mBillView.onSuccess(true);
            }

            @Override
            public void onFail(String message) {
                mBillView.onBillFail(message);
            }
        });
    }
}