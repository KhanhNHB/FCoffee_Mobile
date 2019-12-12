package com.group3.fcoffee.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.models.BillInfo;
import com.group3.fcoffee.utils.FormatMoney;

import java.util.List;

public class BillInfoAdapter extends RecyclerView.Adapter<BillInfoAdapter.ViewHolder> {

    private Context mContext;
    private List<BillInfo> mBillInfos;

    public BillInfoAdapter(Context mContext, List<BillInfo> billInfos) {
        this.mContext = mContext;
        this.mBillInfos = billInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_bill_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTxtBillInfoProductName.setText(mBillInfos.get(position).getName());
        holder.mTxtBillInfoProductUnitPrice.setText(FormatMoney.formatVND(mBillInfos.get(position).getPrice()));
        holder.mTxtBillInfoProductQuantity.setText(String.valueOf(mBillInfos.get(position).getQuantity()));
        holder.mTxtBillInfoProductTotalPrice.setText(FormatMoney.formatVND(mBillInfos.get(position).getTotal_price()));
    }

    @Override
    public int getItemCount() {
        return this.mBillInfos != null ? this.mBillInfos.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtBillInfoProductName, mTxtBillInfoProductUnitPrice, mTxtBillInfoProductQuantity, mTxtBillInfoProductTotalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTxtBillInfoProductName = itemView.findViewById(R.id.txtBillInfoProductName);
            this.mTxtBillInfoProductUnitPrice = itemView.findViewById(R.id.txtBillInfoProductUnitPrice);
            this.mTxtBillInfoProductQuantity = itemView.findViewById(R.id.txtBillInfoProductQuantity);
            this.mTxtBillInfoProductTotalPrice = itemView.findViewById(R.id.txtBillInfoProductTotalPrice);
        }
    }
}
