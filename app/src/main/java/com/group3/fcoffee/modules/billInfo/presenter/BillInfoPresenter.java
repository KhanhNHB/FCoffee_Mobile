package com.group3.fcoffee.modules.billInfo.presenter;

import com.group3.fcoffee.modules.billInfo.repositories.BillInfoRepository;
import com.group3.fcoffee.modules.billInfo.view.BillInfoView;

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
