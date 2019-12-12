package com.group3.fcoffee.repositories;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group3.fcoffee.models.Account;
import com.group3.fcoffee.models.Auth;
import com.group3.fcoffee.models.Bill;
import com.group3.fcoffee.models.BillInfo;
import com.group3.fcoffee.models.Category;
import com.group3.fcoffee.models.Product;
import com.group3.fcoffee.models.Table;
import com.group3.fcoffee.utils.CallBackData;
import com.group3.fcoffee.utils.ClientAPI;
import com.group3.fcoffee.utils.ResponseData;
import com.group3.fcoffee.view_models.BillCreation;
import com.group3.fcoffee.view_models.ProductOrder;
import com.group3.fcoffee.view_models.TableDeleteProductItem;
import com.group3.fcoffee.view_models.TableDetail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FCoffeeRepositoryIMP implements FCoffeeRepository {
    @Override
    public void signIn(String username, String password, final CallBackData<Auth> authCallBackData) {
        ClientAPI clientAPI = new ClientAPI();
        JSONObject customInfo = new JSONObject();
        try {
            customInfo.put("username", username);
            customInfo.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), customInfo.toString());
        Call<ResponseBody> call = clientAPI.FCoffeeServices().signIn(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<Auth>>() {
                        }.getType();
                        ResponseData<Auth> responseData = new Gson().fromJson(result, type);
                        Auth auth = responseData.getData();
                        if (auth != null) {
                            authCallBackData.onSuccess(auth);
                        } else {
                            authCallBackData.onFail("Không thể đăng nhập!\nVui lòng kiểm tra lại tên đăng nhập hoặc mật khẩu");
                        }
                    } catch (Exception ex) {
                        authCallBackData.onFail(ex.getMessage());
                    }
                } else {
                    authCallBackData.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                authCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void getAccount(String username, String token, final CallBackData<Account> accountCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer + " + token;
        Call<ResponseBody> call = api.FCoffeeServices().getAccount(username, header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<Account>>() {
                        }.getType();
                        ResponseData<Account> responseData = new Gson().fromJson(result, type);
                        Account account = responseData.getData();
                        if (account != null) {
                            accountCallBackData.onSuccess(account);
                        } else {
                            accountCallBackData.onFail("Không tìm thấy thông tin tài khoản!");
                        }
                    } catch (Exception e) {
                        accountCallBackData.onFail("Không tìm thấy thông tin tài khoản!");
                    }
                } else {
                    accountCallBackData.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                accountCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void getProducts(String token, final CallBackData<List<Product>> productCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        Call<ResponseBody> call = api.FCoffeeServices().getProducts(header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Product>>>() {
                        }.getType();
                        ResponseData<List<Product>> responseData = new Gson().fromJson(result, type);
                        List<Product> products = responseData.getData();
                        if (products != null) {
                            productCallBackData.onSuccess(products);
                        } else {
                            productCallBackData.onFail("Không hiển thị được danh sách sản phẩm");
                        }
                    } catch (Exception e) {
                        productCallBackData.onFail("Không hiển thị được danh sách sản phẩm");
                    }
                } else {
                    productCallBackData.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                productCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void getProduct(String id, String token, CallBackData<Product> product) {

    }

    @Override
    public void getTables(String token, final CallBackData<List<Table>> tablesCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        Call<ResponseBody> call = api.FCoffeeServices().getTables(header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Table>>>() {
                        }.getType();
                        ResponseData<List<Table>> responseData = new Gson().fromJson(result, type);
                        List<Table> tables = responseData.getData();
                        if (tables != null) {
                            tablesCallBackData.onSuccess(tables);
                        } else {
                            tablesCallBackData.onFail("Không hiển thị được danh sách bàn");
                        }
                    } catch (Exception e) {
                        tablesCallBackData.onFail("Không hiển thị được danh sách bàn");
                    }
                } else {
                    tablesCallBackData.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                tablesCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void getTableDetail(String id, String token, final CallBackData<TableDetail> tableDetailCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        Call<ResponseBody> call = api.FCoffeeServices().getTableDetail(id, header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<TableDetail>>() {
                        }.getType();

                        ResponseData<TableDetail> responseData = new Gson().fromJson(result, type);
                        TableDetail tableDetail = responseData.getData();
                        if (tableDetail != null) {
                            tableDetailCallBackData.onSuccess(tableDetail);
                        } else {
                            tableDetailCallBackData.onFail("Bàn trống");
                        }
                    } catch (Exception e) {
                    }
                } else {
                    tableDetailCallBackData.onFail("Không thấy thông tin bàn");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                tableDetailCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void getTable(String id, String token, final CallBackData<Table> tableCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        Call<ResponseBody> call = api.FCoffeeServices().getTable(id, header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<Table>>() {
                        }.getType();

                        ResponseData<Table> responseData = new Gson().fromJson(result, type);
                        Table table = responseData.getData();
                        if (table != null) {
                            tableCallBackData.onSuccess(table);
                        } else {
                            tableCallBackData.onFail("Bàn trống");
                        }
                    } catch (Exception e) {
                    }
                } else {
                    tableCallBackData.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                tableCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void checkInTable(String id, String token, List<ProductOrder> productOrders, final CallBackData<Boolean> productOrdersCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        JSONObject custom = new JSONObject();
        try {
            JSONArray array = new JSONArray();
            for (ProductOrder productOrder : productOrders) {
                JSONObject object = new JSONObject();
                object.put("id", productOrder.getId());
                object.put("price", productOrder.getPrice());
                object.put("quantity", productOrder.getQuantity());
                object.put("is_plus", productOrder.isPlus());
                object.put("is_sub", productOrder.isSub());
                array.put(object);
            }
            custom.put("products", array);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), custom.toString());
        Call<ResponseBody> call = api.FCoffeeServices().getCheckIn(id, header, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<Boolean>>() {
                        }.getType();
                        ResponseData<Boolean> responseData = new Gson().fromJson(result, type);
                        Boolean isSuccess = responseData.getData();
                        if (isSuccess) {
                            productOrdersCallBackData.onSuccess(isSuccess);
                        } else {
                            productOrdersCallBackData.onFail("Check in fail!");
                        }
                    } catch (Exception e) {
                        productOrdersCallBackData.onFail("Check in fail!");
                    }
                } else {
                    productOrdersCallBackData.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                productOrdersCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void checkOutTable(String id, String token, final CallBackData<Boolean> booleanCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        Call<ResponseBody> call = api.FCoffeeServices().getCheckOut(id, header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<Boolean>>() {
                        }.getType();
                        ResponseData<Boolean> responseData = new Gson().fromJson(result, type);
                        Boolean isCheckOut = responseData.getData();
                        if (isCheckOut) {
                            booleanCallBackData.onSuccess(isCheckOut);
                        } else {
                            booleanCallBackData.onFail("Thanh toán thất bại");
                        }
                    } catch (Exception e) {
                        booleanCallBackData.onFail("Thanh toán thất bại");
                    }
                } else {
                    booleanCallBackData.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                booleanCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void deleteProductItem(String table_id, String token, TableDeleteProductItem model, final CallBackData<Boolean> deleteProductItem) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        JSONObject custom = new JSONObject();
        try {
            custom.put("table_id", model.getTableId());
            custom.put("bill_id", model.getBillId());
            custom.put("product_id", model.getProduct_id());
            custom.put("product_quantity", model.getProductQuantity());
            custom.put("product_price", model.getProductPrice());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), custom.toString());
        Call<ResponseBody> call = api.FCoffeeServices().deleteProductItem(table_id, header, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<Boolean>>() {}.getType();
                        ResponseData<Boolean> responseData = new Gson().fromJson(result, type);
                        Boolean isSuccess = responseData.getData();
                        if (isSuccess) {
                            deleteProductItem.onSuccess(isSuccess);
                        } else {
                            deleteProductItem.onSuccess(isSuccess);
                        }
                    } catch (Exception e) {
                        deleteProductItem.onFail("Remove product item fail");
                    }
                } else {
                    deleteProductItem.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                deleteProductItem.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void getBills(String token, final CallBackData<List<Bill>> billsCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        Call<ResponseBody> call = api.FCoffeeServices().getBillsByCurrentStaff(header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Bill>>>() {
                        }.getType();

                        ResponseData<List<Bill>> responseData = new Gson().fromJson(result, type);
                        List<Bill> bills = responseData.getData();

                        if (bills != null) {
                            billsCallBackData.onSuccess(bills);
                        } else {
                            billsCallBackData.onFail("Hiển thị bill thất bại");
                        }
                    } catch (Exception e) {
                        billsCallBackData.onFail(e.getMessage());
                    }
                } else {
                    billsCallBackData.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                billsCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void getBill(String id, String token, final CallBackData<Bill> billCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        Call<ResponseBody> call = api.FCoffeeServices().getBill(id, header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<Bill>() {
                        }.getType();

                        ResponseData<Bill> responseData = new Gson().fromJson(result, type);
                        Bill bill = responseData.getData();

                        if (bill != null) {
                            billCallBackData.onSuccess(bill);
                        } else {
                            billCallBackData.onFail("Hiển thị thông tin bill thất bại");
                        }
                    } catch (Exception e) {
                        billCallBackData.onFail(e.getMessage());
                    }
                } else {
                    billCallBackData.onFail("Lỗi Server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                billCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void createBill(BillCreation model, String token, CallBackData<Boolean> billCallBackData) {

    }

    @Override
    public void updateBill() {
    }

    @Override
    public void getBillInfos(String token, CallBackData<List<BillInfo>> billInfos) {

    }

    @Override
    public void getBillInfo(String id, String token, final CallBackData<List<BillInfo>> billInfosCallBackData) {
        ClientAPI api = new ClientAPI();
        String header = "Bearer " + token;
        Call<ResponseBody> call = api.FCoffeeServices().getBillInfo(id, header);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<BillInfo>>>() {
                        }.getType();

                        ResponseData<List<BillInfo>> responseData = new Gson().fromJson(result, type);
                        List<BillInfo> billsInfo = responseData.getData();
                        if (billsInfo != null) {
                            billInfosCallBackData.onSuccess(billsInfo);
                        } else {
                            billInfosCallBackData.onFail("Hiển thị thông tin bill thất bại");
                        }
                    } catch (Exception err) {
                        Log.d("ERROR", err.getMessage());
                        billInfosCallBackData.onFail(err.getMessage());
                    }
                } else {
                    billInfosCallBackData.onFail("Thông tin bill không tồn tại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                billInfosCallBackData.onFail("Hệ thống đang bận");
            }
        });
    }

    @Override
    public void getCategories(String token, CallBackData<List<Category>> categories) {

    }

    @Override
    public void getCategory(String token, CallBackData<Category> category) {

    }
}