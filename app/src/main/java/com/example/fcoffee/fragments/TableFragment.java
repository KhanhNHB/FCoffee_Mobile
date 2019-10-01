package com.example.fcoffee.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.Table.adapter.TableAdapter;
import com.example.fcoffee.modules.Table.model.TableList;
import com.example.fcoffee.modules.Table.services.TableService;
import com.example.fcoffee.utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TableFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerView;
    private TableAdapter mTableAdapter;
    private TableList mTables;
    private TableService mService;

    public static TableFragment newInstance() {
        TableFragment fragment = new TableFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_table, container, false);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        mRecyclerView = mView.findViewById(R.id.rcv_table);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);

        mRecyclerView.setLayoutManager(gridLayoutManager);

        mService = APIUtils.getTableService();
    }

    private void initData() {
        Call<TableList> call = mService.get();
        call.enqueue(new Callback<TableList>() {
            @Override
            public void onResponse(Call<TableList> call, Response<TableList> response) {
                if (response.code() == 200) {
                    mTables = response.body();
                    updateRcv();
                } else {
                    Toast.makeText(mView.getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TableList> call, Throwable t) {
                Toast.makeText(mView.getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERROR: ", t.getMessage());
            }
        });


    }

    private void updateRcv() {
        if (mTableAdapter == null) {
            mTableAdapter = new TableAdapter(getContext(), mTables);
            mRecyclerView.setAdapter(mTableAdapter);
        } else {
            mTableAdapter.notifyDataSetChanged();
        }
    }
}
