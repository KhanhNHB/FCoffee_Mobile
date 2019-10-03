package com.example.fcoffee.modules.Table.adapter;

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
import com.example.fcoffee.modules.Drink.model.DTOresponse.DrinkData;
import com.example.fcoffee.modules.Table.model.DTOresponse.TableDetailData;

import java.util.StringTokenizer;

public class TableDetailAdapter extends RecyclerView.Adapter<TableDetailAdapter.ViewHolder> {
    private Context mContext;
    private DrinkData mDrink;
    private TableDetailData mTableDetailData;

    public TableDetailAdapter(Context context, TableDetailData tableDetail, DrinkData drinkData) {
        mContext = context;
        mTableDetailData = tableDetail;
        mDrink = drinkData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_table_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        initData(holder, position);

        final String currentPrice = holder.mTxtProductPrice.getText().toString();

        holder.mImgBtnRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.mBtnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());
                currentQuantity = currentQuantity + 1;
                holder.mTxtProductQuantity.setText(String.valueOf(currentQuantity));
                setCurrentPrice(holder, currentPrice, currentQuantity);

            }
        });

        holder.mBtnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());

                if (currentQuantity > 0) {
                    currentQuantity = currentQuantity - 1;
                    holder.mTxtProductQuantity.setText(String.valueOf(currentQuantity));
                    setCurrentPrice(holder, currentPrice, currentQuantity);
                }
            }
        });
    }

    private void setCurrentPrice(final ViewHolder holder, String currentPrice, int currentQuantity) {
        StringTokenizer stk = new StringTokenizer(currentPrice);
        String price = stk.nextToken(" ");
        holder.mTxtProductPrice.setText(String.valueOf(Float.parseFloat(price) * currentQuantity) + Money.VND);
    }

    private void initData(ViewHolder holder, int position) {
//        holder.mImgProduct = mTableDetailData.getTableDetail().getListBillInfos().get(position).getImage();
        int drinkID = mTableDetailData.getTableDetail().getListBillInfos().get(position).getDrinkId();
        if (drinkID == mDrink.getmDrink().getId()) {
            holder.mTxtProductName.setText(String.valueOf(mDrink.getmDrink().getName()));
        }

        holder.mTxtProductPrice.setText(String.valueOf(mTableDetailData.getTableDetail().getListBillInfos().get(position).getSubPrice() + Money.VND));
        holder.mTxtProductQuantity.setText(String.valueOf(mTableDetailData.getTableDetail().getListBillInfos().get(position).getCount()));
    }

    @Override
    public int getItemCount() {
        return mTableDetailData == null ? 0 : mTableDetailData.getTableDetail().getListBillInfos().size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgProduct, mImgBtnRemoveProduct, mBtnAddQuantity, mBtnRemoveQuantity;
        private TextView mTxtProductName, mTxtProductPrice, mTxtProductQuantity;

        public ViewHolder(View view) {
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
            mImgBtnRemoveProduct = view.findViewById(R.id.img_btn_remove_product);
        }
    }


}
