package com.example.fcoffee.modules.Table.repositories;

import android.widget.Toast;

import com.example.fcoffee.common.Error;
import com.example.fcoffee.modules.Management.services.ManagementService;
import com.example.fcoffee.modules.Table.model.DTOresponse.DTOTableList;
import com.example.fcoffee.modules.Table.services.TableService;
import com.example.fcoffee.modules.Table.view.TableView;
import com.example.fcoffee.utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableRepository {
    TableService mTableService;
    ManagementService mManagementService;

    public TableRepository() {
        mTableService = APIUtils.getTableService();
        mManagementService = APIUtils.getManagerService();
    }

    public void getAll(final TableView tableView) {
        Call<DTOTableList> call = mTableService.get();
        call.enqueue(new Callback<DTOTableList>() {
            @Override
            public void onResponse(Call<DTOTableList> call, Response<DTOTableList> response) {
                if (response.code() == 200) {
                    DTOTableList dto = new DTOTableList();
                    dto = response.body();
                    tableView.onTableSuccessGetAll(dto);
                } else {
                    tableView.onTableFail(Error.TAG_ERROR_RESPONSE);
                }
            }

            @Override
            public void onFailure(Call<DTOTableList> call, Throwable t) {
                tableView.onTableFail(Error.TAG_ERROR_REQUEST + t.getMessage());
            }
        });
    }
}
