package com.example.fcoffee.modules.billInfo.presenter;

import com.example.fcoffee.modules.billInfo.repositories.BillInfoRepository;
import com.example.fcoffee.modules.billInfo.view.BillInfoView;

public class BillInfoPresenter {
    private BillInfoView mBillInfoView;
    private BillInfoRepository mBillInfoRepository;

    private int mBillId;
    public BillInfoPresenter(BillInfoView mBillInfoView, int mBillId){
        this.mBillInfoView = mBillInfoView;
        this.mBillInfoRepository = new BillInfoRepository();
        this.mBillId = mBillId;
    }

    public BillInfoPresenter() {
    }

    public void getAllBillInfoByBillId(BillInfoView billInfoView){
        mBillInfoRepository.getAll(billInfoView, mBillId);
    }
}
