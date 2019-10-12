package com.example.fcoffee.modules.table.repositories;

import android.util.Log;

import com.example.fcoffee.common.App;
import com.example.fcoffee.common.Error;
import com.example.fcoffee.modules.dink.services.DrinkService;
import com.example.fcoffee.modules.management.services.ManagementService;
import com.example.fcoffee.modules.table.model.DTOresponse.TableDetailData;
import com.example.fcoffee.modules.table.model.DTOresponse.TableData;
import com.example.fcoffee.modules.table.services.TableService;
import com.example.fcoffee.modules.table.view.TableView;
import com.example.fcoffee.utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableRepository {
    TableService mTableService;
    DrinkService mDrinkService;
    ManagementService mManagementService;

    public TableRepository() {
        mTableService = APIUtils.getTableService();
        mManagementService = APIUtils.getManagerService();
        mDrinkService = APIUtils.getDrinkService();
    }

    public void getAll(final TableView tableView) {
        Call<TableData> call = mTableService.get();
        call.enqueue(new Callback<TableData>() {
            @Override
            public void onResponse(Call<TableData> call, Response<TableData> response) {
                if (response.code() == 200) {
                    TableData dto = response.body();
                    tableView.onTableSuccessGetAll(dto);
                } else {
                    tableView.onTableFail(Error.TAG_SYSTEM_BUSY);
                    Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                }
            }

            @Override
            public void onFailure(Call<TableData> call, Throwable t) {
                tableView.onTableFail(Error.TAG_SYSTEM_BUSY);
                Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
            }
        });
    }

    public void getByNumber(final int tableNumber, final TableView tableView) {
        Call<TableDetailData> call = mManagementService.getTableByNumber(tableNumber);
        call.enqueue(new Callback<TableDetailData>() {
            @Override
            public void onResponse(Call<TableDetailData> call, Response<TableDetailData> response) {
                if (response.code() == 200) {
                    TableDetailData tableDetail;

                    tableDetail = response.body();
                    tableView.onTableSuccessGetByNumber(tableDetail);
                } else {
                    tableView.onTableFail("Bàn trống");
                }
            }

            @Override
            public void onFailure(Call<TableDetailData> call, Throwable t) {
                tableView.onTableFail(Error.TAG_ERROR_REQUEST + t.getMessage());
            }
        });
    }
}
