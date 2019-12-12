package com.group3.fcoffee.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.models.Product;
import com.group3.fcoffee.utils.FormatMoney;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context mContext;
    private List<Product> mProducts;

    public ProductAdapter(Context mContext, List<Product> products) {
        this.mContext = mContext;
        this.mProducts = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull ViewHolder holder, final int position) {
        int currentQuantity = Integer.parseInt(holder.mQuantity.getText().toString());

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build().load(mProducts.get(position).getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mImage);

        holder.mName.setText(mProducts.get(position).getName());
        holder.mPrice.setText(FormatMoney.formatVND(mProducts.get(position).getPrice() * currentQuantity));


        holder.mBtnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.mQuantity.getText().toString());
                currentQuantity = currentQuantity + 1;
                holder.mPrice.setText(FormatMoney.formatVND(mProducts.get(position).getPrice() * currentQuantity));
                holder.mQuantity.setText(String.valueOf(currentQuantity));

                mProducts.get(position).setQuantityOrder(currentQuantity);
            }
        });

        holder.mBtnSubQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.mQuantity.getText().toString());

                if (currentQuantity != 0) {
                    currentQuantity = currentQuantity - 1;
                    holder.mPrice.setText(FormatMoney.formatVND(mProducts.get(position).getPrice() * currentQuantity));
                    holder.mQuantity.setText(String.valueOf(currentQuantity));

                    mProducts.get(position).setQuantityOrder(currentQuantity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts != null ? mProducts.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage, mBtnAddQuantity, mBtnSubQuantity;
        private TextView mName, mPrice, mQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.mImage = itemView.findViewById(R.id.img_product);
            this.mName = itemView.findViewById(R.id.txt_product_name);
            this.mPrice = itemView.findViewById(R.id.txt_product_price);
            this.mQuantity = itemView.findViewById(R.id.txt_product_quantity);
            this.mBtnAddQuantity = itemView.findViewById(R.id.btn_add_quantity);
            this.mBtnSubQuantity = itemView.findViewById(R.id.btn_sub_quantity);
        }
    }
}
