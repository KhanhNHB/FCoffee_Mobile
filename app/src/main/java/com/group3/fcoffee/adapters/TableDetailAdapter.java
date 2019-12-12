package com.group3.fcoffee.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.models.Table;
import com.group3.fcoffee.presenters.TablePresenter;
import com.group3.fcoffee.utils.FormatMoney;
import com.group3.fcoffee.view_models.ProductDetail;
import com.group3.fcoffee.view_models.ProductOrder;
import com.group3.fcoffee.view_models.TableDeleteProductItem;
import com.group3.fcoffee.view_models.TableDetail;
import com.group3.fcoffee.views.TableView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TableDetailAdapter extends RecyclerView.Adapter<TableDetailAdapter.ViewHolder> implements TableView {
    private Context mContext;
    private TableDetail mTableDetail;
    private TextView mTotalPriceBill;
    private TablePresenter mTablePresenter;
    private List<ProductOrder> mProductOrders;
    private SharedPreferences pref;
    private String token;
    private int pos;

    public TableDetailAdapter(Context context, TableDetail tableDetail, TextView totalPriceBill) {
        mContext = context;
        mTableDetail = tableDetail;
        mTotalPriceBill = totalPriceBill;
        this.mTablePresenter = new TablePresenter(this);
        pref = mContext.getSharedPreferences("MyRef", Context.MODE_PRIVATE);
        token = pref.getString("token", null);
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
        float unitPrice = this.mTableDetail.getProductDetails().get(position).getPrice();
        int currentQuantity = this.mTableDetail.getProductDetails().get(position).getQuantity();
        float currentPrice = unitPrice * currentQuantity;

        holder.mTxtProductName.setText(this.mTableDetail.getProductDetails().get(position).getName());
        holder.mTxtProductPrice.setText(FormatMoney.formatVND(currentPrice));
        holder.mTxtProductQuantity.setText(String.valueOf(this.mTableDetail.getProductDetails().get(position).getQuantity()));


        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.build().load(mTableDetail.getProductDetails().get(position).getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mImgProduct);

        holder.mBtnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());
                currentQuantity = currentQuantity + 1;
                holder.mTxtProductQuantity.setText(String.valueOf(currentQuantity));

                float unitPrice = mTableDetail.getProductDetails().get(position).getPrice();
                float currentPrice = unitPrice * currentQuantity;

                holder.mTxtProductPrice.setText(FormatMoney.formatVND(currentPrice));

                Float originTotalBill = Float.parseFloat(mTotalPriceBill.getText().toString());
                mTotalPriceBill.setText(String.valueOf(originTotalBill + unitPrice));

                List<ProductOrder> productOrders = new ArrayList<>();
                String productId = mTableDetail.getProductDetails().get(position).getId();

                ProductOrder productOrder = new ProductOrder(productId, currentQuantity, unitPrice, true, false);
                productOrders.add(productOrder);
                mTablePresenter.checkInTable(mTableDetail.getId(), token, productOrders);
            }
        });
        holder.mBtnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());
                if (currentQuantity > 1) {
                    currentQuantity = currentQuantity - 1;
                    holder.mTxtProductQuantity.setText(String.valueOf(currentQuantity));

                    float unitPrice = mTableDetail.getProductDetails().get(position).getPrice();
                    float currentProductPrice = currentQuantity * unitPrice;

                    holder.mTxtProductPrice.setText(FormatMoney.formatVND(currentProductPrice));

                    Float originTotalBill = Float.parseFloat(mTotalPriceBill.getText().toString());
                    mTotalPriceBill.setText(String.valueOf(originTotalBill - unitPrice));

                    List<ProductOrder> productOrders = new ArrayList<>();
                    String productId = mTableDetail.getProductDetails().get(position).getId();

                    ProductOrder productOrder = new ProductOrder(productId, currentQuantity, unitPrice, false, true);
                    productOrders.add(productOrder);
                    mTablePresenter.checkInTable(mTableDetail.getId(), token, productOrders);
                }
            }
        });
        holder.mBtnRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTableDetail.getProductDetails().size() > 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder
                            .setTitle("Xóa sản phẩm")
                            .setMessage("Bạn có muốn bỏ sản phẩm ra danh sách?")
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String table_id = mTableDetail.getId();
                                    String bill_id = mTableDetail.getBillDetail().getId();
                                    String product_id = mTableDetail.getProductDetails().get(position).getId();
                                    int currentQuantity = Integer.parseInt(holder.mTxtProductQuantity.getText().toString());
                                    float unitPrice = mTableDetail.getProductDetails().get(position).getPrice();
                                    pos = position;
                                    TableDeleteProductItem tableDeleteProductItem = new TableDeleteProductItem(table_id, bill_id, product_id, currentQuantity, unitPrice);
                                    mTablePresenter.deleteProductItem(table_id, token, tableDeleteProductItem);
                                }
                            })
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mTableDetail.getProductDetails() != null ? this.mTableDetail.getProductDetails().size() : 0;
    }

    @Override
    public void onTableDeleteProductItem(Boolean result) {
        mTableDetail.getProductDetails().remove(pos);
        float total = 0;
        for (ProductDetail productDetail : mTableDetail.getProductDetails()) {
            total = total + productDetail.getPrice() * productDetail.getQuantity();
        }
        mTotalPriceBill.setText(String.valueOf(total));
        notifyDataSetChanged();
    }

    @Override
    public void onTablesSuccess(List<Table> tables) {
    }

    @Override
    public void onTableDetailSuccess(TableDetail tableDetail) {
    }

    @Override
    public void onTableSuccess(Table table) {
    }

    @Override
    public void onTableCheckAvailableSuccess(Boolean result) {

    }

    @Override
    public void onTableFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgProduct, mBtnAddQuantity, mBtnRemoveQuantity, mBtnRemoveProduct;
        private TextView mTxtProductName, mTxtProductPrice, mTxtProductQuantity;

        public ViewHolder(View view) {
            super(view);

            mImgProduct = view.findViewById(R.id.img_product);
            mTxtProductName = view.findViewById(R.id.txt_product_name);
            mTxtProductPrice = view.findViewById(R.id.txt_product_price);
            mTxtProductQuantity = view.findViewById(R.id.txt_product_quantity);
            mBtnAddQuantity = view.findViewById(R.id.btn_img_add_quantity);
            mBtnRemoveQuantity = view.findViewById(R.id.btn_img_remove_quantity);
            mBtnRemoveProduct = view.findViewById(R.id.btn_img_remove_product);
        }
    }
}