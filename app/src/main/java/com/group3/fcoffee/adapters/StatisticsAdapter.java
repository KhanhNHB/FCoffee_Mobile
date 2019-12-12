package com.group3.fcoffee.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.activities.BillInfoActivity;
import com.group3.fcoffee.models.Bill;
import com.group3.fcoffee.models.BillInfo;
import com.group3.fcoffee.presenters.BillInfoPresenter;
import com.group3.fcoffee.utils.FormatMoney;
import com.group3.fcoffee.views.BillInfoView;

import java.util.ArrayList;
import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> implements BillInfoView {
    private Context mContext;
    private List<Bill> mBills;
    private SharedPreferences pref;
    private String token;
    private BillInfoPresenter mBillInfoPresenter;
    private String mTableNumber, mBillId, mDate;
    private Float mTotal;

    public StatisticsAdapter(Context mContext, List<Bill> bills) {
        this.mContext = mContext;
        this.mBills = bills;
        this.mBillInfoPresenter = new BillInfoPresenter(this);
        this.pref = mContext.getSharedPreferences("MyRef", Context.MODE_PRIVATE);
        this.token = pref.getString("token", null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_bill, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        this.mTableNumber = this.mBills.get(position).getTable_name();
        this.mTotal = this.mBills.get(position).getTotal();
        this.mBillId = this.mBills.get(position).getId();

        holder.mEdtTableNumber.setText(mTableNumber);
        holder.mEdtTime.setText(this.mBills.get(position).getCreated_at());
        holder.mEdtPrice.setText(FormatMoney.formatVND(this.mBills.get(position).getTotal()));

        holder.mLlButtonBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTableNumber = mBills.get(position).getTable_name();
                mTotal = mBills.get(position).getTotal();
                mBillId = mBills.get(position).getId();
                mDate = mBills.get(position).getCreated_at();
                mBillInfoPresenter.getBillInfoByBillId(mBills.get(position).getId(), token);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mBills != null ? this.mBills.size() : 0;
    }

    @Override
    public void onBillsInfoSuccess(List<BillInfo> billsInfo) {

        ArrayList<BillInfo> mBillsInfo = (ArrayList<BillInfo>) billsInfo;
        Intent intent = new Intent(mContext, BillInfoActivity.class);

        Bundle bundle = new Bundle();

        bundle.putString("BillId", mBillId);
        bundle.putString("Date", mDate);
        bundle.putParcelableArrayList("BillsInfo", mBillsInfo);
        bundle.putString("TableNumber", mTableNumber);
        bundle.putFloat("Total", mTotal);

        intent.putExtra("Bill", bundle);

        mContext.startActivity(intent);
    }

    @Override
    public void onBillInfoSuccess(BillInfo billInfo) {

    }

    @Override
    public void onBillInfoFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void updateList(List<Bill> bills) {
        mBills = new ArrayList<>();
        mBills = bills;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mEdtTableNumber, mEdtTime, mEdtPrice;
        private LinearLayout mLlButtonBill;


        public ViewHolder(View itemView) {
            super(itemView);
            this.mEdtTableNumber = itemView.findViewById(R.id.edtTableNumber);
            this.mEdtTime = itemView.findViewById(R.id.edtTime);
            this.mEdtPrice = itemView.findViewById(R.id.edtPrice);
            this.mLlButtonBill = itemView.findViewById(R.id.ll_button_bill);

            initData();
        }

        private void initData() {

        }
    }
}