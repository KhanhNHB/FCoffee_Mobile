package com.group3.fcoffee.modules.statistics.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.common.Error;
import com.group3.fcoffee.modules.bill.model.DTOresponse.DTOBillList;
import com.group3.fcoffee.modules.billInfo.activity.BillInfoActivity;
import com.group3.fcoffee.utils.FormatMoney;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatisticsBillAdapter extends RecyclerView.Adapter<StatisticsBillAdapter.ViewHolder> {

    private Context mContext;
    private DTOBillList mBill;
    private SimpleDateFormat sdf = new SimpleDateFormat(Error.DATE_FORMAT);


    public StatisticsBillAdapter(Context mContext, DTOBillList mBill){
        this.mBill = mBill;
        this.mContext = mContext;
    }

    public void updateList(DTOBillList billList){
        mBill = new DTOBillList();
        mBill = billList;
        notifyDataSetChanged();
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
        final int billId = mBill.getBillList().get(position).getId();
        final int tableNumber = mBill.getBillList().get(position).getTableNumber();
        final float tableDiscount = mBill.getBillList().get(position).getDiscount();
        final float price = mBill.getBillList().get(position).getTotalPrice();
        final Date date = mBill.getBillList().get(position).getCreateAt();
        final String dateString = sdf.format(date);
        final String priceString = FormatMoney.formatVND(price);

        holder.mTextViewCount.setText(String.valueOf(position + 1));
        holder.mTextViewTableNumber.setText(String.valueOf(tableNumber));
        holder.mTextViewDiscount.setText(String.valueOf(tableDiscount) + "%");
        holder.mTextViewPrice.setText(priceString);
        holder.mTextViewTime.setText(dateString);
        holder.mLLButtonBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BillInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("bill_id", billId);
                bundle.putString("table_number", String.valueOf(tableNumber));
                bundle.putString("price", priceString);
                bundle.putString("discount", String.valueOf(tableDiscount) + "%");
                bundle.putString("usernameStaff", String.valueOf(mBill.getBillList().get(position).getUsernameSatff()));
                bundle.putString("date", dateString);

                intent.putExtra("anonymouse_number", bundle);

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBill != null ? mBill.getBillList().size() : 0;
    }

    protected class  ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextViewCount;
        private TextView mTextViewTableNumber;
        private TextView mTextViewDiscount;
        private TextView mTextViewPrice;
        private TextView mTextViewTime;
        private LinearLayout mLLButtonBill;

        public ViewHolder(@NonNull View view){
            super(view);
            mTextViewCount = view.findViewById(R.id.edtTableCount);
            mTextViewTableNumber = view.findViewById(R.id.edtTableNumber);
            mTextViewDiscount = view.findViewById(R.id.edtDiscount);
            mTextViewPrice = view.findViewById(R.id.edtPrice);
            mTextViewTime = view.findViewById(R.id.edtTime);
            mLLButtonBill = view.findViewById(R.id.ll_button_bill);
        }
    }
}
