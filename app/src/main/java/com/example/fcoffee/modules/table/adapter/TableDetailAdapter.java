package com.example.fcoffee.modules.table.adapter;

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
import com.example.fcoffee.modules.management.repositories.ManagementRepository;
import com.example.fcoffee.modules.management.view.ManagementView;
import com.example.fcoffee.modules.table.model.DTOresponse.TableDetailData;
import com.example.fcoffee.utils.FormatMoney;
import com.squareup.picasso.Picasso;

import java.util.StringTokenizer;

public class TableDetailAdapter extends RecyclerView.Adapter<TableDetailAdapter.ViewHolder> {
    private Context mContext;
    private TableDetailData mTableDetailData;
    private TextView mTotalPrice;
    private Float tempPrice;

    public TableDetailAdapter(Context context, TableDetailData tableDetail, TextView totalPrice, float price) {
        mContext = context;
        mTableDetailData = tableDetail;
        mTotalPrice = totalPrice;
        tempPrice = price;
    }

    public void updateTableDetailData(TableDetailData data){
        mTableDetailData = new TableDetailData();
        mTableDetailData = data;

        notifyDataSetChanged();
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
                holder.verifyRemoveItem(mTableDetailData.getTableDetail().getListBillInfos().get(position).getDrinkName(), position, holder);
            }
        });

        holder.mBtnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentQuantity = holder.mTxtProductQuantity.getText().toString();
                int parseQuantity = Integer.parseInt(currentQuantity);
                parseQuantity = parseQuantity + 1;
                setCurrentPrice(holder, currentPrice, parseQuantity, false);

                mTableDetailData.getTableDetail().getListBillInfos().get(position).setCount(parseQuantity);
                holder.mManagementRepository.addCount(mTableDetailData.getTableDetail().getListBillInfos().get(position).getBillInfoId(), holder);
            }
        });

        holder.mBtnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentQuantity = holder.mTxtProductQuantity.getText().toString();

                int parseQuantity = Integer.parseInt(currentQuantity);

                if (parseQuantity > 1) {
                    parseQuantity = parseQuantity - 1;

                    setCurrentPrice(holder, currentPrice, parseQuantity, true);
                    mTableDetailData.getTableDetail().getListBillInfos().get(position).setCount(parseQuantity);
                    holder.mManagementRepository.subCount(mTableDetailData.getTableDetail().getListBillInfos().get(position).getBillInfoId(), holder);
                }
            }
        });
    }

    private void setCurrentPrice(final ViewHolder holder, String currentPrice, final int currentQuantity, boolean isSubstraction) {
        StringTokenizer stk = new StringTokenizer(currentPrice);
        String price = stk.nextToken(" ");

        float originalPrice = Float.parseFloat(price);
        float calculatePrice = originalPrice * currentQuantity;

        if (isSubstraction) {
            tempPrice -= originalPrice;
        } else {
            tempPrice += originalPrice;
        }

        String formatCalculatePrice = FormatMoney.formatVND(calculatePrice);
        holder.mTxtProductQuantity.setText(String.valueOf(currentQuantity));
        holder.mTxtProductPrice.setText(formatCalculatePrice);

        String formatTotalPrice = FormatMoney.formatVND(tempPrice);

        formatTotalPrice = formatTotalPrice.replace(",", ".");
        mTotalPrice.setText(formatTotalPrice);
    }

    private void initData(ViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build().load(mTableDetailData.getTableDetail().getListBillInfos().get(position).getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mImgProduct);

        holder.mTxtProductName.setText(mTableDetailData.getTableDetail().getListBillInfos().get(position).getDrinkName());
        holder.mTxtProductPrice.setText(FormatMoney.formatVND(mTableDetailData.getTableDetail().getListBillInfos().get(position).getSubPrice()));
        holder.mTxtProductQuantity.setText(String.valueOf(mTableDetailData.getTableDetail().getListBillInfos().get(position).getCount()));
    }

    @Override
    public int getItemCount() {
        return mTableDetailData == null ? 0 : mTableDetailData.getTableDetail().getListBillInfos().size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements ManagementView {
        private ImageView mImgProduct, mImgBtnRemoveProduct, mBtnAddQuantity, mBtnRemoveQuantity;
        private TextView mTxtProductName, mTxtProductPrice, mTxtProductQuantity;
        private ManagementRepository mManagementRepository;

        public ViewHolder(View view) {
            super(view);
            initView(view);
            initData();
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

        private void initData() {
            mManagementRepository = new ManagementRepository();

            mBtnAddQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        private void verifyRemoveItem(String drinkName, final int position, final ViewHolder holder) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("Bạn có muốn xóa sản phẩm " + drinkName + " ra khỏi bàn?")
                    .setCancelable(false)
                    .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button\
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mManagementRepository.delete(mTableDetailData.getTableDetail().getListBillInfos().get(position).getBillInfoId(), holder);
                            mTableDetailData.getTableDetail().getListBillInfos().remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(mContext, "Đã xóa",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Xóa sản phẩm");
            alert.show();

            builder.getContext();
        }

        @Override
        public void onDrinkSuccess() {
            Toast.makeText(mContext, "Vui lòng thao tác chậm lại", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDrinkFail(String message) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }
}
