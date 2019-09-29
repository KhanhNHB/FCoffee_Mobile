package com.example.fcoffee.utils;

import com.example.fcoffee.networks.RetrofitClient;
import com.example.fcoffee.services.AccountService;
import com.example.fcoffee.services.ManagerService;
import com.example.fcoffee.services.TableService;

public class APIUtils {
    private APIUtils() {}

    public static final String API_URL_BASE = "http://10.0.2.2:8080/";

    public static TableService getTableService() {
        return RetrofitClient.getClient(API_URL_BASE).create(TableService.class);
    }

    public static AccountService getAccountService() {
        return RetrofitClient.getClient(API_URL_BASE).create(AccountService.class);
    }

    public static ManagerService getManagerService() {
        return RetrofitClient.getClient(API_URL_BASE).create(ManagerService.class);
    }
}
