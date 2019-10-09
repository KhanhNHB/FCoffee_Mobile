package com.example.fcoffee.modules.Table.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.Drink.adapter.common.Money;
import com.example.fcoffee.modules.Account.repositories.LoginRepository;
import com.example.fcoffee.modules.Table.model.DTOresponse.TableDetailData;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class TableDetailAdapter extends RecyclerView.Adapter<TableDetailAdapter.ViewHolder> {
    private Context mContext;
    private TableDetailData mTableDetailData;

    public TableDetailAdapter(Context context, TableDetailData tableDetail) {
        mContext = context;
        mTableDetailData = tableDetail;
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

        final int count = mTableDetailData.getTableDetail().getListBillInfos().get(position).getCount();
        final String currentPrice = String.valueOf(mTableDetailData.getTableDetail().getListBillInfos().get(position).getSubPrice() / count);

        holder.mImgBtnRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyRemoveItem(holder.mTxtProductName.getText().toString(), position);
            }
        });

        holder.mBtnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentQuantity = holder.mTxtProductQuantity.getText().toString();
                int parseQuantity = Integer.parseInt(currentQuantity);

                parseQuantity = parseQuantity + 1;
                setCurrentPrice(holder, currentPrice, parseQuantity);

            }
        });

        holder.mBtnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentQuantity = holder.mTxtProductQuantity.getText().toString();

                int parseQuantity = Integer.parseInt(currentQuantity);

                if (parseQuantity > 1) {
                    parseQuantity = parseQuantity - 1;

                    setCurrentPrice(holder, currentPrice, parseQuantity);
                }
            }
        });
    }

    private void setCurrentPrice(final ViewHolder holder, String currentPrice, final int currentQuantity) {
        StringTokenizer stk = new StringTokenizer(currentPrice);
        String price = stk.nextToken(" ");

        NumberFormat format = new DecimalFormat("#,###");
        String formatterPrice = format.format(Float.parseFloat(price) * currentQuantity) + Money.VND;

        holder.mTxtProductQuantity.setText(String.valueOf(currentQuantity));
        holder.mTxtProductPrice.setText(formatterPrice);
    }

    private void initData(ViewHolder holder, int position) {
//        holder.mImgProduct = mTableDetailData.getTableDetail().getListBillInfos().get(position).getImage();
        holder.mTxtProductName.setText(mTableDetailData.getTableDetail().getListBillInfos().get(position).getDrinkName());
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

    private void verifyRemoveItem(String drinkName, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Bạn có muốn xóa sản phẩm " + drinkName + " ra khỏi bàn?")
                .setCancelable(false)
                .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mTableDetailData.getTableDetail().getListBillInfos().remove(position);

                        notifyDataSetChanged();
                        LoginRepository.TOKEN = "";
                        Toast.makeText(mContext, "Đã xóa",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Xóa sản phẩm");
        alert.show();
    }

}
