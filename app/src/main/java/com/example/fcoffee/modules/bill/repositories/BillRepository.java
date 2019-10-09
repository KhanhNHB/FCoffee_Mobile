package com.example.fcoffee.modules.bill.repositories;



import com.example.fcoffee.common.Error;
import com.example.fcoffee.modules.bill.model.DTOresponse.DTOBillList;
import com.example.fcoffee.modules.bill.service.BillService;
import com.example.fcoffee.modules.bill.view.BillView;
import com.example.fcoffee.modules.management.services.ManagementService;
import com.example.fcoffee.utils.APIUtils;

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
        try{
            Call<DTOBillList> call = mBillService.getByToken();
            call.enqueue(new Callback<DTOBillList>() {
                @Override
                public void onResponse(Call<DTOBillList> call, Response<DTOBillList> response) {
                    if(response.code() == 200){
                        DTOBillList dto = new DTOBillList();
                        dto = response.body();
                        billView.onBillSuccessGetAllByUsername(dto);
                    }else{
                        billView.onBillFail(Error.TAG_ERROR_RESPONSE);
                    }
                }

                @Override
                public void onFailure(Call<DTOBillList> call, Throwable t) {
                    billView.onBillFail(Error.TAG_ERROR_RESPONSE);
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
