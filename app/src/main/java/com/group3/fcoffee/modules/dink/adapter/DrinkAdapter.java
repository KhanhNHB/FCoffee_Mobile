package com.group3.fcoffee.modules.dink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.modules.dink.model.DTOresponse.DrinkData;
import com.group3.fcoffee.utils.FormatMoney;
import com.squareup.picasso.Picasso;

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
                float currentPrice = mDrinks.getmDrink().get(position).getPrice();
                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());

                currentQuantity = currentQuantity + 1;

                if (currentQuantity == 1) {
                    setCurrentPrice(holder, currentPrice, 1, position);
                } else {
                    setCurrentPrice(holder, currentPrice, currentQuantity, position);
                }

                mDrinks.getmDrink().get(position).setCount(currentQuantity);
            }
        });

        holder.mBtnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float currentPrice = mDrinks.getmDrink().get(position).getPrice();
                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());

                if (currentQuantity > 0) {
                    currentQuantity = currentQuantity - 1;
                    setCurrentPrice(holder, currentPrice, currentQuantity, position);
                }

                mDrinks.getmDrink().get(position).setCount(currentQuantity);
            }
        });
    }

    private void setCurrentPrice(ViewHolder holder, float currentPrice, int currentQuantity, int position) {
        holder.mTxtProductQuantity.setText(String.valueOf(currentQuantity));
        holder.mTxtProductPrice.setText(FormatMoney.formatVND(currentPrice  * currentQuantity));
    }

    private void initData(ViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build().load(mDrinks.getmDrink().get(position).getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mImgProduct);

        holder.mTxtProductName.setText(mDrinks.getmDrink().get(position).getName());
        holder.mTxtProductPrice.setText(FormatMoney.formatVND(0));
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
