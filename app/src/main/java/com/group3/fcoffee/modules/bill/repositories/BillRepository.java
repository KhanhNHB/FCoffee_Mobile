package com.group3.fcoffee.modules.bill.repositories;

import android.util.Log;

import com.group3.fcoffee.common.Error;
import com.group3.fcoffee.modules.bill.model.DTOresponse.DTOBillList;
import com.group3.fcoffee.modules.bill.service.BillService;
import com.group3.fcoffee.modules.bill.view.BillView;
import com.group3.fcoffee.modules.management.services.ManagementService;
import com.group3.fcoffee.utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillRepository {
    BillService mBillService;
    ManagementService mManagementService;

    public BillRepository() {
        this.mBillService = APIUtils.getBillService();
        this.mManagementService = APIUtils.getManagerService();
    }

    public void getAll(final BillView billView){
        Call<DTOBillList> call = mBillService.getByToken();
            call.enqueue(new Callback<DTOBillList>() {
                @Override
                public void onResponse(Call<DTOBillList> call, Response<DTOBillList> response) {
                    if(response.code() == 200){
                        try {
                            DTOBillList dto = response.body();
                            billView.onBillSuccessGetAllByUsername(dto);
                        } catch (Exception ex) {
                            billView.onBillFail(Error.TAG_SYSTEM_BUSY);
                            Log.d(Error.TAG_ERROR_RESPONSE, ex.getMessage());
                        }
                    } else {
                        billView.onBillFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                    }
                }

                @Override
                public void onFailure(Call<DTOBillList> call, Throwable t) {
                    billView.onBillFail(Error.TAG_SYSTEM_BUSY);
                    Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
                }
            });
    }
}
