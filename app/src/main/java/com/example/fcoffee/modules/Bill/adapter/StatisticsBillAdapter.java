package com.example.fcoffee.modules.Bill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fcoffee.R;
import com.example.fcoffee.common.Error;
import com.example.fcoffee.modules.Bill.model.DTOresponse.DTOBillList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StatisticsBillAdapter extends RecyclerView.Adapter<StatisticsBillAdapter.ViewHolder> {

    private Context mContext;
    private DTOBillList mBill;
    private SimpleDateFormat sdf = new SimpleDateFormat(Error.DATE_FORMAT);

    private Locale localeVN = new Locale("vi", "VN");

    private NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
    public StatisticsBillAdapter(Context mContext, DTOBillList mBill){
        this.mBill = mBill;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_bill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int tableNumber = mBill.getBillList().get(position).getTableNumber();
        float tableDiscount = mBill.getBillList().get(position).getDiscount();
        float price = mBill.getBillList().get(position).getTotalPrice();
        Date date = mBill.getBillList().get(position).getCreateAt();
        String dateString = sdf.format(date);
        String priceString = nf.format(price);

        holder.mTextViewCount.setText(String.valueOf(position + 1));
        holder.mTextViewTableNumber.setText(String.valueOf(tableNumber));
        holder.mTextViewDiscount.setText(String.valueOf(tableDiscount) + "%");
        holder.mTextViewPrice.setText(priceString);
        holder.mTextViewTime.setText(dateString);

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

        public ViewHolder(@NonNull View view){
            super(view);
            mTextViewCount = view.findViewById(R.id.edtTableCount);
            mTextViewTableNumber = view.findViewById(R.id.edtTableNumber);
            mTextViewDiscount = view.findViewById(R.id.edtDiscount);
            mTextViewPrice = view.findViewById(R.id.edtPrice);
            mTextViewTime = view.findViewById(R.id.edtTime);
        }
    }
}
