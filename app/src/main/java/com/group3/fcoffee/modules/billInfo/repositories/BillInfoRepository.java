package com.group3.fcoffee.modules.billInfo.repositories;

import android.util.Log;

import com.group3.fcoffee.common.Error;
import com.group3.fcoffee.modules.billInfo.model.DTOresponse.DTOBillInfoList;
import com.group3.fcoffee.modules.billInfo.service.BillInfoService;
import com.group3.fcoffee.modules.billInfo.view.BillInfoView;
import com.group3.fcoffee.modules.management.services.ManagementService;
import com.group3.fcoffee.utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillInfoRepository {
    BillInfoService mBillInfoService;
    ManagementService mManagementService;

    public BillInfoRepository(){
        this.mBillInfoService = APIUtils.getBillInfoService();
        this.mManagementService = APIUtils.getManagerService();
    }

    public void getAll(final BillInfoView billInfoView, int billId){

        Call<DTOBillInfoList> call = mBillInfoService.getBillInfoByBillId(billId);
            call.enqueue(new Callback<DTOBillInfoList>() {
                @Override
                public void onResponse(Call<DTOBillInfoList> call, Response<DTOBillInfoList> response) {
                    if(response.code() == 200){
                        try {
                            DTOBillInfoList dto = response.body();
                            billInfoView.onBillInfoSuccessGetAllByBillId(dto);
                        } catch (Exception ex) {
                            billInfoView.onBillFail(Error.TAG_SYSTEM_BUSY);
                            Log.d(Error.TAG_ERROR_RESPONSE, ex.getMessage());
                        }
                    }else{
                        billInfoView.onBillFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                    }
                }

                @Override
                public void onFailure(Call<DTOBillInfoList> call, Throwable t) {
                    billInfoView.onBillFail(Error.TAG_SYSTEM_BUSY);
                    Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
                }
            });

    }
}
