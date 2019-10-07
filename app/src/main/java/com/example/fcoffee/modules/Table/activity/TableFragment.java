package com.example.fcoffee.modules.Table.activity;

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
import com.example.fcoffee.modules.Table.model.DTOresponse.DTOTableList;
import com.example.fcoffee.modules.Table.presenter.TablePresenter;
import com.example.fcoffee.modules.Table.services.TableService;
import com.example.fcoffee.modules.Table.view.TableView;
import com.example.fcoffee.utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TableFragment extends Fragment implements TableView {

    private View mView;
    private RecyclerView mRecyclerView;
    private TableAdapter mTableAdapter;
    private DTOTableList mTables;
    private TablePresenter mTablePresenter;

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
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initData() {
        mTablePresenter = new TablePresenter(this);
        mTablePresenter.getAll(this);
    }

    @Override
    public void onTableSuccessGetAll(DTOTableList dto) {
        if (dto != null) {
            mTables = dto;
            updateRcv();
        }
    }

    @Override
    public void onTableFail(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
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