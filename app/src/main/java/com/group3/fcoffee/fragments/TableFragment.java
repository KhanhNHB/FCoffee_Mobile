package com.group3.fcoffee.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.adapters.TableAdapter;
import com.group3.fcoffee.models.Table;
import com.group3.fcoffee.presenters.TablePresenter;
import com.group3.fcoffee.view_models.TableDetail;
import com.group3.fcoffee.views.TableView;

import java.util.List;

public class TableFragment extends Fragment implements TableView {

    private View mView;
    private RecyclerView mRecyclerView;
    private TablePresenter mTablePresenter;
    private TableAdapter mTableAdapter;
    private List<Table> mTables;
    private SharedPreferences pref;
    private String token;

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

        pref =  getActivity().getSharedPreferences("MyRef", Context.MODE_PRIVATE);
        token = pref.getString("token", null);
    }

    private void initData() {
        this.mTablePresenter = new TablePresenter(this);
        this.mTablePresenter.getTables(token);
    }

    @Override
    public void onTableDeleteProductItem(Boolean result) {


    }

    @Override
    public void onTablesSuccess(List<Table> tables) {
        this.mTables = tables;
        updateRcv();
    }

    @Override
    public void onTableDetailSuccess(TableDetail tableDetail) {

    }

    @Override
    public void onTableSuccess(Table table) {

    }

    @Override
    public void onResume() {
        super.onResume();
        this.mTablePresenter.getTables(token);
    }

    @Override
    public void onTableCheckAvailableSuccess(Boolean result) {

    }

    @Override
    public void onTableFail(String message) {
    }

    private void updateRcv() {
        mTableAdapter = new TableAdapter(getContext(), this.mTables);
        mRecyclerView.setAdapter(mTableAdapter);
    }
}