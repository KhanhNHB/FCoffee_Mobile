package com.example.fcoffee.modules.Table.repositories;

import com.example.fcoffee.common.Error;
import com.example.fcoffee.modules.BillInfo.model.BillInfo;
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkDTO;
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkData;
import com.example.fcoffee.modules.Drink.services.DrinkService;
import com.example.fcoffee.modules.Management.services.ManagementService;
import com.example.fcoffee.modules.Table.model.DTOresponse.TableDetailData;
import com.example.fcoffee.modules.Table.model.DTOresponse.TableData;
import com.example.fcoffee.modules.Table.services.TableService;
import com.example.fcoffee.modules.Table.view.TableView;
import com.example.fcoffee.utils.APIUtils;

import java.util.ArrayList;
import java.util.List;

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
                    TableData dto = new TableData();
                    dto = response.body();
                    tableView.onTableSuccessGetAll(dto);
                } else {
                    tableView.onTableFail(Error.TAG_ERROR_RESPONSE);
                }
            }

            @Override
            public void onFailure(Call<TableData> call, Throwable t) {
                tableView.onTableFail(Error.TAG_ERROR_REQUEST + t.getMessage());
            }
        });
    }

    public void getByNumber(final int tableNumber, final TableView tableView) {
        Call<TableDetailData> call = mManagementService.getTableByNumber(tableNumber);
        call.enqueue(new Callback<TableDetailData>() {
            @Override
            public void onResponse(Call<TableDetailData> call, Response<TableDetailData> response) {
                if (response.code() == 200) {
                    TableDetailData tableDetail = new TableDetailData();

                    tableDetail = response.body();

                    tableView.onTableSuccessGetByNumber(tableDetail);

                    List<BillInfo> billInfos = new ArrayList<>();
                    billInfos = tableDetail.getTableDetail().getListBillInfos();

                    if (billInfos.size() > 0) {
                        for (BillInfo billInfo : billInfos) {
                            getByDrinkId(billInfo.getDrinkId(), tableView);
                        }
                    }
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

    public void getByDrinkId(final int id, final TableView tableView) {
        Call<DrinkDTO> callDrinkService = mDrinkService.getById(id);
        callDrinkService.enqueue(new Callback<DrinkDTO>() {
            @Override
            public void onResponse(Call<DrinkDTO> call, Response<DrinkDTO> response) {
                if (response.code() == 200) {
                    DrinkDTO drink = new DrinkDTO();
                    drink = response.body();
                    tableView.onDinkSuccessGetById(drink);
                } else {
                    tableView.onTableFail(Error.TAG_ERROR_RESPONSE + response.message());
                }
            }

            @Override
            public void onFailure(Call<DrinkDTO> call, Throwable t) {
                tableView.onTableFail(Error.TAG_ERROR_RESPONSE + t.getMessage());
            }
        });
    }
}
