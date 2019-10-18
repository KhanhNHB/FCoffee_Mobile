package com.group3.fcoffee.modules.billInfo.repositories;

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
        try {
            Call<DTOBillInfoList> call = mBillInfoService.getBillInfoByBillId(billId);
            call.enqueue(new Callback<DTOBillInfoList>() {
                @Override
                public void onResponse(Call<DTOBillInfoList> call, Response<DTOBillInfoList> response) {
                    if(response.code() == 200){
                        DTOBillInfoList dto = new DTOBillInfoList();
                        dto = response.body();
                        billInfoView.onBillInfoSuccessGetAllByBillId(dto);
                    }else{
                        billInfoView.onBillFail(Error.TAG_ERROR_RESPONSE);
                    }
                }

                @Override
                public void onFailure(Call<DTOBillInfoList> call, Throwable t) {
                    billInfoView.onBillFail(Error.TAG_ERROR_RESPONSE);

                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
