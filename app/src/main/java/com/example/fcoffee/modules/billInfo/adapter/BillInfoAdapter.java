package com.example.fcoffee.modules.billInfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.billInfo.model.DTOresponse.DTOBillInfoList;

import java.text.NumberFormat;
import java.util.Locale;

public class BillInfoAdapter extends RecyclerView.Adapter<BillInfoAdapter.ViewHolder>{

    private Context mContext;
    private DTOBillInfoList mBillInfoList;
    private Locale localeVN = new Locale("vi", "VN");

    private NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
    public BillInfoAdapter(Context mContext, DTOBillInfoList mBillInfoList) {
        this.mContext = mContext;
        this.mBillInfoList = mBillInfoList;
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

        String priceString = nf.format(mBillInfoList.getBillInfoList().get(position).getSubPrice());

        holder.mTextViewCount.setText(String.valueOf(position + 1));
        holder.mTextViewName.setText(mBillInfoList.getBillInfoList().get(position).getImage());
        holder.mTextViewQuantity.setText(String.valueOf(mBillInfoList.getBillInfoList().get(position).getCount()));
        holder.mTextViewSubPrice.setText(priceString);
    }

    @Override
    public int getItemCount() {
        return mBillInfoList != null ? mBillInfoList.getBillInfoList().size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextViewCount;
        private TextView mTextViewName;
        private TextView mTextViewQuantity;
        private TextView mTextViewSubPrice;

        public ViewHolder(@NonNull View view){
            super(view);
            mTextViewCount = view.findViewById(R.id.edtBillCount);
            mTextViewName = view.findViewById(R.id.edtBillInfoName);
            mTextViewQuantity = view.findViewById(R.id.edtBillInfoQuantity);
            mTextViewSubPrice = view.findViewById(R.id.edtBillInfoPrice);
        }
    }
}
