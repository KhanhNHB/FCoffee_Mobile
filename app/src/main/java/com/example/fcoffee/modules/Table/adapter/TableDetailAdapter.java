package com.example.fcoffee.modules.Table.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.Table.model.TableDetail;

public class TableDetailAdapter extends RecyclerView.Adapter<TableDetailAdapter.ViewHolder> {
    private Context mContext;
    private TableDetail mTableDetail;

    public TableDetailAdapter(Context context, TableDetail tableDetail) {
        mContext = context;
        mTableDetail = tableDetail;
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

        holder.mBtnRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.mBtnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());
                currentQuantity = currentQuantity + 1;
                holder.mTxtProductQuantity.setText(currentQuantity);
            }
        });

        holder.mBtnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());

                if (currentQuantity != 0) {
                    currentQuantity = currentQuantity - 1;
                    holder.mTxtProductQuantity.setText(currentQuantity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgProduct;
        private TextView mTxtProductName, mTxtProductPrice, mTxtProductQuantity;
        private Button mBtnAddQuantity, mBtnRemoveQuantity, mBtnRemoveProduct;

        public ViewHolder(View view) {
            super(view);
            mImgProduct = view.findViewById(R.id.img_product);
            mTxtProductName = view.findViewById(R.id.txt_product_name);
            mTxtProductPrice = view.findViewById(R.id.txt_product_price);
            mTxtProductQuantity = view.findViewById(R.id.txt_product_quantity);
            mBtnAddQuantity = view.findViewById(R.id.btn_add_quantity);
            mBtnRemoveQuantity = view.findViewById(R.id.btn_remove_quantity);
            mBtnRemoveProduct = view.findViewById(R.id.btn_remove_product);
        }


    }
}
