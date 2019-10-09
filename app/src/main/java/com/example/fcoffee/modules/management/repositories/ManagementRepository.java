package com.example.fcoffee.modules.management.repositories;

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
    }

    public void subCount(int billInfoId, final ManagementView managementView) {
        Call<ResponseBody> call = mManagementService.subCount(billInfoId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    managementView.onDrinkSuccess();
                } else {
                    managementView.onDrinkFail(Error.TAG_ERROR_RESPONSE + "Xóa thất bại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                managementView.onDrinkFail(Error.TAG_ERROR_RESPONSE + t.getMessage());
            }
        });
    }

    public void payment(int billId, final ManagementView managementView) {
        Call<ResponseBody> call = mManagementService.payment(billId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    managementView.onDrinkSuccess();
                } else {
                    managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                managementView.onDrinkFail(Error.TAG_SYSTEM_BUSY);
            }
        });
    }
}
