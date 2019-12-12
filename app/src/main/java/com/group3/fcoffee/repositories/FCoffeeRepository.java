package com.group3.fcoffee.repositories;

import com.group3.fcoffee.models.Account;
import com.group3.fcoffee.models.Auth;
import com.group3.fcoffee.models.Bill;
import com.group3.fcoffee.models.BillInfo;
import com.group3.fcoffee.models.Category;
import com.group3.fcoffee.models.Product;
import com.group3.fcoffee.models.Table;
import com.group3.fcoffee.utils.CallBackData;
import com.group3.fcoffee.view_models.BillCreation;
import com.group3.fcoffee.view_models.ProductOrder;
import com.group3.fcoffee.view_models.TableDeleteProductItem;
import com.group3.fcoffee.view_models.TableDetail;

import java.util.List;

public interface FCoffeeRepository {
    // Sign In
    void signIn(String username, String password, CallBackData<Auth> authCallBackData);

    // Account
    void getAccount(String username, String token, CallBackData<Account> accountCallBackData);

    // Product
    void getProducts(String token, CallBackData<List<Product>> productsCallBackData);

    void getProduct(String id, String token, CallBackData<Product> productCallBackData);

    // Table
    void getTables(String token, CallBackData<List<Table>> tablesCallBackData);

    void getTableDetail(String id, String token, CallBackData<TableDetail> tableDetailCallBackData);

    void getTable(String id, String token, CallBackData<Table> tableCallBackData);

    void checkInTable(String id, String token, List<ProductOrder> productOrders, CallBackData<Boolean> booleanCallBackData);

    void checkOutTable(String id, String token, CallBackData<Boolean> booleanCallBackData);

    void deleteProductItem(String id, String token, TableDeleteProductItem model, CallBackData<Boolean> deleteProductItem);
    // Bill
    void getBills(String token, CallBackData<List<Bill>> billsCallBackData);

    void getBill(String id, String token, CallBackData<Bill> billCallBackData);

    void createBill(BillCreation model, String token, CallBackData<Boolean> billCallBackData);

    void updateBill();

    // Bill Info
    void getBillInfos(String token, CallBackData<List<BillInfo>> billInfosCallBackData);

    void getBillInfo(String id, String token, CallBackData<List<BillInfo>> billInfoCallBackData);

    // Category
    void getCategories(String token, CallBackData<List<Category>> categoriesCallBackData);

    void getCategory(String token, CallBackData<Category> categoryCallBackData);
}