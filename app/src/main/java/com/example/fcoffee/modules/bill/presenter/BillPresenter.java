package com.example.fcoffee.modules.bill.presenter;

import com.example.fcoffee.modules.bill.repositories.BillRepository;
import com.example.fcoffee.modules.bill.view.BillView;

public class BillPresenter {
    private BillView mBillView;
    private BillRepository mBillRepository;

    public BillPresenter(BillView mBillView) {
        this.mBillView = mBillView;
        this.mBillRepository = new BillRepository();
    }

    public BillPresenter() {
    }

    public void getAllBillByToken(BillView billView){ mBillRepository.getAll(billView);
    }
}
