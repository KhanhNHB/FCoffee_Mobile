package com.example.fcoffee.utils;

import com.example.fcoffee.modules.dink.services.DrinkService;
import com.example.fcoffee.modules.bill.service.BillService;
import com.example.fcoffee.modules.billInfo.service.BillInfoService;
import com.example.fcoffee.modules.table.services.TableService;
import com.example.fcoffee.networks.RetrofitClient;
import com.example.fcoffee.modules.account.services.AccountService;
import com.example.fcoffee.modules.management.services.ManagementService;

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

    public static DrinkService getDrinkService() {
        return RetrofitClient.getClient(API_URL_BASE).create(DrinkService.class);
    }
}
