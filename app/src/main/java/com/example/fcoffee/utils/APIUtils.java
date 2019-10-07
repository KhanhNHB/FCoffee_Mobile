package com.example.fcoffee.utils;

import com.example.fcoffee.modules.Bill.service.BillService;
import com.example.fcoffee.modules.BillInfo.service.BillInfoService;
import com.example.fcoffee.modules.Table.services.TableService;
import com.example.fcoffee.networks.RetrofitClient;
import com.example.fcoffee.modules.Account.services.AccountService;
import com.example.fcoffee.modules.Management.services.ManagementService;

public class APIUtils {
    private APIUtils() {}

    public static final String API_URL_BASE = "https://xavalo.herokuapp.com/";

    public static TableService getTableService() {
        return RetrofitClient.getClient(API_URL_BASE).create(TableService.class);
    }

    public static BillService getBillService(){
        return RetrofitClient.getClient(API_URL_BASE).create(BillService.class);
    }

    public static BillInfoService getBillInfoService(){
        return RetrofitClient.getClient(API_URL_BASE).create(BillInfoService.class);
    }

    public static AccountService getAccountService() {
        return RetrofitClient.getClient(API_URL_BASE).create(AccountService.class);
    }

    public static ManagementService getManagerService() {
        return RetrofitClient.getClient(API_URL_BASE).create(ManagementService.class);
    }
}
