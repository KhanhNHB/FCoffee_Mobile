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

public class TableDetailAdapter extends RecyclerView.Adapter<TableDetailAdapter.ViewHolder> {
    private Context mContext;
    private TableDetailData mTableDetailData;
    private TextView mTotalPrice;

    public TableDetailAdapter(Context context, TableDetailData tableDetail, TextView totalPrice) {
        mContext = context;
        mTableDetailData = tableDetail;
        mTotalPrice = totalPrice;
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
        final String unitPrice = String.valueOf(mTableDetailData.getTableDetail().getListBillInfos().get(position).getSubPrice() / count);


        holder.mImgBtnRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.verifyRemoveItem(mTableDetailData.getTableDetail().getListBillInfos().get(position).getDrinkName(), position, holder);
            }
        });

        holder.mBtnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentQuantityItem = holder.mTxtProductQuantity.getText().toString();
                int parseCurrentQuantityItem = Integer.parseInt(currentQuantityItem);
                float discount = mTableDetailData.getTableDetail().getDiscount();

                parseCurrentQuantityItem = parseCurrentQuantityItem + 1;

                setCurrentPrice(holder, unitPrice, parseCurrentQuantityItem, false, discount, position);

                mTableDetailData.getTableDetail().getListBillInfos().get(position).setCount(parseCurrentQuantityItem);
                holder.mManagementRepository.addCount(mTableDetailData.getTableDetail().getListBillInfos().get(position).getBillInfoId(), holder);
            }
        });

        holder.mBtnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentQuantityItem = holder.mTxtProductQuantity.getText().toString();
                int parseCurrentQuantityItem = Integer.parseInt(currentQuantityItem);
                float discount = mTableDetailData.getTableDetail().getDiscount();

                if (parseCurrentQuantityItem > 1) {
                    parseCurrentQuantityItem = parseCurrentQuantityItem - 1;

                    setCurrentPrice(holder, unitPrice, parseCurrentQuantityItem, true, discount, position);
                    mTableDetailData.getTableDetail().getListBillInfos().get(position).setCount(parseCurrentQuantityItem);
                    holder.mManagementRepository.subCount(mTableDetailData.getTableDetail().getListBillInfos().get(position).getBillInfoId(), holder);
                }
            }
        });
    }

    private void setCurrentPrice(final ViewHolder holder, String unitPrice, final int currentQuantityItem, boolean isSubstraction, final float discount, final int position) {
        float originalPrice = Float.parseFloat(unitPrice);
        float newPrice = originalPrice * currentQuantityItem;

        float percent = 0;
        if (discount > 0) {
            percent = discount / 100;
        }

        holder.mTxtProductQuantity.setText(String.valueOf(currentQuantityItem));
        holder.mTxtProductPrice.setText(FormatMoney.formatVND(newPrice));

        float discoutPriceItem = originalPrice * (1 - percent);
        if (isSubstraction) {
            discoutPriceItem = mTableDetailData.getTableDetail().getTotalPrice() - discoutPriceItem;
        } else {
            discoutPriceItem = mTableDetailData.getTableDetail().getTotalPrice() + discoutPriceItem;
        }

        mTableDetailData.getTableDetail().getListBillInfos().get(position).setSubPrice(Float.parseFloat(unitPrice));
        mTableDetailData.getTableDetail().setTotalPrice(discoutPriceItem);
        mTotalPrice.setText(FormatMoney.formatVND(discoutPriceItem));
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
                            boolean isLastItem = mTableDetailData.getTableDetail().getListBillInfos().size() > 0 ? true : false;
                            mManagementRepository.delete(mTableDetailData.getTableDetail().getListBillInfos().get(position).getBillInfoId(), isLastItem, holder);

                            float percent = 0;
                            float discount = mTableDetailData.getTableDetail().getDiscount();
                            if (discount > 0) {
                                percent = discount / 100;
                            }

                            int count = mTableDetailData.getTableDetail().getListBillInfos().get(position).getCount();
                            String unitPrice = String.valueOf(mTableDetailData.getTableDetail().getListBillInfos().get(position).getSubPrice() / count);

                            float discoutPriceItem = Float.parseFloat(unitPrice) * (1 - percent);
                            float newTotalPriceBill = mTableDetailData.getTableDetail().getTotalPrice() - (discoutPriceItem * count);

                            mTableDetailData.getTableDetail().setTotalPrice(newTotalPriceBill);

                            mTotalPrice.setText(FormatMoney.formatVND(newTotalPriceBill));
                            mTableDetailData.getTableDetail().getListBillInfos().remove(position);

                            notifyDataSetChanged();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Xóa sản phẩm");
            alert.show();

            builder.getContext();
        }

        @Override
        public void onDrinkSuccess() {
            Toast.makeText(mContext, "Thao tác thành công", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCheckoutSuccess() {

        }

        @Override
        public void onRemoveDrinkSuccess() {
            Toast.makeText(mContext, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDrinkFail(String message) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDiscountSuccess() {

        }

        @Override
        public void onDiscountFail(String message) {

        }
    }
}
