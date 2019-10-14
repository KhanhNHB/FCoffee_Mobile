package com.example.fcoffee.modules.management.presenter;

import com.example.fcoffee.modules.management.repositories.ManagementRepository;
import com.example.fcoffee.modules.management.view.ManagementView;

public class ManagementPresenter {

    private ManagementView mManagementView;
    private ManagementRepository mManagementRepository;

    public ManagementPresenter(ManagementView managementView) {
        mManagementView = managementView;
        mManagementRepository = new ManagementRepository();
    }

    public void payment(int billId) {
        mManagementRepository.payment(billId, mManagementView);
    }

    public void switchTable(int fromNumberTable, int toNumberTable) {
        mManagementRepository.switchTable(fromNumberTable, toNumberTable, mManagementView);
    }
}
