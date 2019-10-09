package com.example.fcoffee.modules.dink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.common.Money;
import com.example.fcoffee.modules.dink.model.DTOresponse.DrinkData;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {
    private Context mContext;
    private DrinkData mDrinks;

    public DrinkAdapter(Context context, DrinkData drinks) {
        mContext = context;
        mDrinks = drinks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_drink, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        initData(holder, position);

        holder.mBtnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPrice = String.valueOf(mDrinks.getmDrink().get(position).getPrice());
                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());

                currentQuantity = currentQuantity + 1;

                if (currentQuantity == 1) {
                    setCurrentPrice(holder, currentPrice, 1);
                } else {
                    setCurrentPrice(holder, currentPrice, currentQuantity);
                }

                mDrinks.getmDrink().get(position).setCount(currentQuantity);
            }
        });

        holder.mBtnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPrice = String.valueOf(mDrinks.getmDrink().get(position).getPrice());
                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());

                if (currentQuantity > 0) {
                    currentQuantity = currentQuantity - 1;
                    setCurrentPrice(holder, currentPrice, currentQuantity);
                }

                mDrinks.getmDrink().get(position).setCount(currentQuantity);
            }
        });
    }

    private void setCurrentPrice(final ViewHolder holder, String currentPrice, final int currentQuantity) {
        StringTokenizer stk = new StringTokenizer(currentPrice);
        String price = stk.nextToken(" ");

        NumberFormat formater = new DecimalFormat("#,###");
        String formatterPrice = formater.format(Float.parseFloat(price) * currentQuantity) + Money.VND;

        holder.mTxtProductQuantity.setText(String.valueOf(currentQuantity));
        holder.mTxtProductPrice.setText(formatterPrice);
    }

    private void initData(ViewHolder holder, int position) {
//        holder.mImgProduct = mTableDetailData.getTableDetail().getListBillInfos().get(position).getImage();

        holder.mTxtProductName.setText(mDrinks.getmDrink().get(position).getName());
        holder.mTxtProductPrice.setText("0" + Money.VND);
        holder.mTxtProductQuantity.setText("0");
    }

    @Override
    public int getItemCount() {
        return mDrinks == null ? 0 : mDrinks.getmDrink().size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgProduct, mBtnAddQuantity, mBtnRemoveQuantity;
        private TextView mTxtProductName, mTxtProductPrice, mTxtProductQuantity;

        public ViewHolder(@NonNull View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            mImgProduct = view.findViewById(R.id.img_product);
            mTxtProductName = view.findViewById(R.id.txt_product_name);
            mTxtProductPrice = view.findViewById(R.id.txt_product_price);
            mTxtProductQuantity = view.findViewById(R.id.txt_product_quantity);
            mBtnAddQuantity = view.findViewById(R.id.btn_add_quantity);
            mBtnRemoveQuantity = view.findViewById(R.id.btn_remove_quantity);
        }
    }
}
