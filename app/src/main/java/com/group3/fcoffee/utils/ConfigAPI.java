package com.group3.fcoffee.utils;

public class ConfigAPI {
//    public static final String BASE_URL = "https://fcoffeee.azurewebsites.net/";
    public static final String BASE_URL = "http://10.0.2.2:9000/";

    public interface Api {

        // Auth
        String SIGN_IN = "auth/sign_in";

        // Account
        String GET_ACCOUNTS = "accounts";
        String GET_ACCOUNT = "accounts/{username}";
        String POST_ACCOUNTS = "accounts";
        String PUT_ACCOUNTS = "accounts/{username}";
        String DELETE_ACCOUNTS = "accounts/{username}";

        // Product
        String GET_PRODUCTS = "products";
        String GET_PRODUCT = "products/{id}";
        String POST_PRODUCTS = "products";
        String PUT_PRODUCTS = "products/{id}";
        String DELETE_PRODUCT = "products/{username}";

        // Category
        String GET_CATEGORIES = "categories";
        String GET_CATEGORY = "categories/{id}";
        String POST_CATEGORIES = "categories";
        String PUT_CATEGORIES = "categories/{id}";
        String DELETE_CATEGORIES = "categories/{id}";

        // Table
        String GET_TABLES = "tables";
        String GET_TABLE = "tables/{id}";
        String POST_TABLES = "tables";
        String PUT_TABLES = "tables/{id}";
        String PUT_TABLE_CHECK_IN = "tables/{id}/checkIn";
        String PUT_TABLE_CHECK_OUT = "tables/{id}/checkOut";
        String DELETE_TABLES = "tables/{id}";
        String DELETE_PRODUCT_ITEM_IN_TABLE = "tables/{id}/custom";

        // Bill
        String GET_BILLS = "bills";
        String GET_BILLS_CURRENT_USER = "bills/staffs/current-staff";
        String GET_BILL = "bills/{id}";
        String POST_BILLS = "bills";
        String PUT_BILLS = "bills/{id}";
        String DELETE_BILLS = "bills/{id}";

        // Bill Info
        String GET_BILL_INFOS = "billinfos";
        String GET_BILL_INFO = "billinfos/{id}";
        String POST_BILL_INFOS = "billinfos";
        String PUT_BILL_INFOS = "billinfos/{id}";
        String DELETE_BILL_INFOS = "billinfos/{id}";
    }
}
