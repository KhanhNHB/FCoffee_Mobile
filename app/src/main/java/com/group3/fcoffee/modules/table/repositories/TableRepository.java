package com.group3.fcoffee.modules.table.repositories;

import android.util.Log;

import com.group3.fcoffee.common.Error;
import com.group3.fcoffee.modules.dink.services.DrinkService;
import com.group3.fcoffee.modules.management.services.ManagementService;
import com.group3.fcoffee.modules.table.model.DTOresponse.TableDetailData;
import com.group3.fcoffee.modules.table.model.DTOresponse.TableData;
import com.group3.fcoffee.modules.table.services.TableService;
import com.group3.fcoffee.modules.table.view.TableView;
import com.group3.fcoffee.utils.APIUtils;

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
                    try {
                        TableData dto = response.body();
                        tableView.onTableSuccessGetAll(dto);
                    } catch (Exception ex) {
                        tableView.onTableFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                    }
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
                    try {
                        TableDetailData tableDetail;
                        tableDetail = response.body();
                        tableView.onTableSuccessGetByNumber(tableDetail);
                    } catch (Exception ex) {
                        tableView.onTableFail(Error.TAG_SYSTEM_BUSY);
                        Log.d(Error.TAG_ERROR_RESPONSE, ex.getMessage());
                    }
                } else {
                    Log.d(Error.TAG_ERROR_RESPONSE, response.message());
                }
            }

            @Override
            public void onFailure(Call<TableDetailData> call, Throwable t) {
                tableView.onTableFail(Error.TAG_ERROR_REQUEST + t.getMessage());
                Log.d(Error.TAG_ERROR_RESPONSE, t.getMessage());
            }
        });
    }
}
