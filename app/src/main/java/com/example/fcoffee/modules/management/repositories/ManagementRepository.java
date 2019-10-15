package com.example.fcoffee.modules.management.repositories;

import android.util.Log;

import com.example.fcoffee.common.Error;
import com.example.fcoffee.modules.management.services.ManagementService;
import com.example.fcoffee.modules.management.view.ManagementView;
import com.example.fcoffee.utils.APIUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementRepository {
    private ManagementService mManagementService;

    public ManagementRepository() {
        mManagementService = APIUtils.getManagerService();
    }

    public void addCount(int billInfoId, final ManagementView managementView) {
        try {
            Call<ResponseBody> call = mManagementService.addCount(billInfoId);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        managementView.onDrinkSuccess();
                    } else {
                        managementView.onDrinkFail(Error.TAG_ERROR_RESPONSE + "Thêm thất bại");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    managementView.onDrinkFail(Error.TAG_ERROR_RESPONSE + t.getMessage());
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void subCount(int billInfoId, final ManagementView managementView) {
        try {
            Call<ResponseBody> call = mManagementService.subCount(billInfoId);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        managementView.onDrinkSuccess();
                    } else {
                        managementView.onDrinkFail(Error.TAG_ERROR_RESPONSE + "Xóa số lượng thất bại");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    managementView.onDrinkFail(Error.TAG_ERROR_RESPONSE + t.getMessage());
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int billInfoId, boolean isLastItem, final ManagementView managementView) {
        try {
            Call<ResponseBody> call = mManagementService.delete(billInfoId, isLastItem);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        managementView.onRemoveDrinkSuccess();
                    } else {
                        managementView.onDrinkFail(Error.TAG_ERROR_RESPONSE + "Xóa thất bại");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    managementView.onDrinkFail(Error.TAG_ERROR_RESPONSE + t.getMessage());
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void payment(int billId, final ManagementView managementView) {
        try {
            Call<ResponseBody> call = mManagementService.payment(billId);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        managementView.onCheckoutSuccess();
                    } else {
                        managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addDiscount(int billId, float discount, final ManagementView managementView){
        try {
            Call<ResponseBody> call = mManagementService.addDiscount(billId, discount);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        managementView.onDiscountSuccess();
                    } else {
                        managementView.onDiscountFail(Error.TAG_SYSTEM_BUSY);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    managementView.onDiscountFail(Error.TAG_SYSTEM_BUSY);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void switchTable(int fromNumberTable, int toNumberTable, final ManagementView managementView) {
        Call<ResponseBody> call = mManagementService.switchTable(fromNumberTable, toNumberTable);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    managementView.onDrinkSuccess();
                } else {
                    managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                    Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
            }
        });
    }
}
