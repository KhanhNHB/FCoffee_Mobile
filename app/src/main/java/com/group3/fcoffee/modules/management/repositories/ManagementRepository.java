package com.group3.fcoffee.modules.management.repositories;

import android.util.Log;

import com.group3.fcoffee.common.Error;
import com.group3.fcoffee.modules.management.services.ManagementService;
import com.group3.fcoffee.modules.management.view.ManagementView;
import com.group3.fcoffee.utils.APIUtils;

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
            Call<ResponseBody> call = mManagementService.addCount(billInfoId);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        try {
                            managementView.onDrinkSuccess();
                        } catch (Exception ex) {
                            managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                            Log.d(Error.TAG_ERROR_RESPONSE, ex.getMessage());
                        }
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

    public void subCount(int billInfoId, final ManagementView managementView) {
        Call<ResponseBody> call = mManagementService.subCount(billInfoId);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        try {
                            managementView.onDrinkSuccess();
                        } catch (Exception ex) {
                            managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                            Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                        }
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

    public void delete(int billInfoId, boolean isLastItem, final ManagementView managementView) {
            Call<ResponseBody> call = mManagementService.delete(billInfoId, isLastItem);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        try {
                            managementView.onRemoveDrinkSuccess();
                        } catch (Exception ex) {
                            managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                            Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                        }
                    } else {
                        managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                    Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
                }
            });
    }

    public void payment(int billId, final ManagementView managementView) {
        Call<ResponseBody> call = mManagementService.payment(billId);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        try {
                            managementView.onCheckoutSuccess();
                        } catch (Exception ex) {
                            managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                            Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                        }
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

    public void addDiscount(int billId, float discount, final ManagementView managementView){
        Call<ResponseBody> call = mManagementService.addDiscount(billId, discount);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        try {
                            managementView.onDiscountSuccess();
                        } catch (Exception ex) {
                            managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                            Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                        }
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

    public void switchTable(int fromNumberTable, int toNumberTable, final ManagementView managementView) {
        Call<ResponseBody> call = mManagementService.switchTable(fromNumberTable, toNumberTable);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        managementView.onDrinkSuccess();
                    } catch (Exception ex) {
                        managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                    }
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
