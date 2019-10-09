package com.example.fcoffee.modules.Management.presenter;

import com.example.fcoffee.modules.Management.repositories.ManagementRepository;
import com.example.fcoffee.modules.Management.view.ManagementView;

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
}
