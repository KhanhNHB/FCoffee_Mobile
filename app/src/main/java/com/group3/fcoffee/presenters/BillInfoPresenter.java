package com.group3.fcoffee.presenters;

import com.group3.fcoffee.models.BillInfo;
import com.group3.fcoffee.repositories.FCoffeeRepository;
import com.group3.fcoffee.repositories.FCoffeeRepositoryIMP;
import com.group3.fcoffee.utils.CallBackData;
import com.group3.fcoffee.views.BillInfoView;

import java.util.List;

public class BillInfoPresenter {
    private BillInfoView mBillInfoView;
    private FCoffeeRepository mFCoffeeRepository;

    public BillInfoPresenter(BillInfoView mBillInfoView) {
        this.mBillInfoView = mBillInfoView;
        this.mFCoffeeRepository = new FCoffeeRepositoryIMP();
    }

    public void getBillInfoByBillId(String id, String token) {
        this.mFCoffeeRepository.getBillInfo(id, token, new CallBackData<List<BillInfo>>() {
            @Override
            public void onSuccess(List<BillInfo> billInfos) {
                mBillInfoView.onBillsInfoSuccess(billInfos);
            }

            @Override
            public void onFail(String message) {
                mBillInfoView.onBillInfoFail(message);
            }
        });
    }
}