package com.example.fcoffee.modules.table.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.fcoffee.R;
import com.example.fcoffee.modules.management.presenter.ManagementPresenter;
import com.example.fcoffee.modules.management.view.ManagementView;
import com.example.fcoffee.modules.table.activity.TableDetailActivity;
import com.example.fcoffee.modules.table.activity.TableFragment;
import com.example.fcoffee.modules.table.model.DTOrequest.Table;
import com.example.fcoffee.modules.table.model.DTOresponse.TableData;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private Context mContext;
    private TableData mTables;
    private TableFragment mTableFragment;

    public TableAdapter(Context mContext, TableData Tables, TableFragment tableFragment) {
        this.mContext = mContext;
        this.mTables = Tables;
        this.mTableFragment = tableFragment;
    }

    public void updateTableDetailData(TableData tables){
        mTables = new TableData();
        mTables = tables;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_table, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final int tableNumber = mTables.getmTables().get(position).getTableNumber();
        final boolean status = mTables.getmTables().get(position).isStatus();

        holder.mTxtTileTable.setText("Bàn " + tableNumber);

        if (status) {
            holder.mLLButtonTable.setBackgroundResource(R.drawable.button_background);
            holder.mTxtTileTable.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.mLLButtonTable.setBackgroundResource(R.drawable.custom_frame_input_important);
            holder.mTxtTileTable.setTextColor(Color.parseColor("#000000"));
        }

        holder.mLLButtonTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TableDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("table_number", tableNumber);
                intent.putExtra("anonymouse_number", bundle);
                ((Activity)mContext).startActivityForResult(intent, 1);
            }
        });

        holder.mLLButtonTable.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) mContext);
    }

    @Override
    public int getItemCount() {
        return mTables != null ? mTables.getmTables().size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, ManagementView {
        private ImageView mImageView;
        private TextView mTxtTileTable;
        private LinearLayout mLLButtonTable;
        private ManagementPresenter mManagementPresenter;
        private ViewPager mViewPager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
            initData();
        }

        private void initView(View view) {
            mImageView = view.findViewById(R.id.img_icon_table);
            mTxtTileTable = view.findViewById(R.id.txt_title_table);
            mLLButtonTable = view.findViewById(R.id.ll_button_table);
            mViewPager = view.findViewById(R.id.vp_home);
        }

        private void initData() {
            itemView.setOnCreateContextMenuListener(this);
            mManagementPresenter = new ManagementPresenter(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            if (mLLButtonTable.getBackground().getConstantState() ==  mContext.getDrawable(R.drawable.button_background).getConstantState()) {
                menu.setHeaderTitle("Chuyển bàn");
                createMenu(menu);
            }
        }

        private void createMenu(Menu menu) {
            menu.setQwertyMode(true);
            MenuItem container = null;

            for (Table table : mTables.getmTables()) {
                if (("Bàn " + table.getTableNumber()).equals(mTxtTileTable.getText().toString())) {
                    continue;
                }

                container = menu.add(0, table.getTableNumber(), table.getTableNumber(), "Bàn " + table.getTableNumber());
                container.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String toTable = item.toString();
                        String[] toNumberTable = toTable.split(" ");
                        String currentTable = mTxtTileTable.getText().toString();
                        String[] fromNumberTable = currentTable.split(" ");

                        mManagementPresenter.switchTable(Integer.parseInt(fromNumberTable[1]) ,Integer.parseInt(toNumberTable[1]));
                        return true;
                    }
                });
            }
        }

        @Override
        public void onDrinkSuccess() {
            Toast.makeText(mContext, "Chuyển bàn thành công", Toast.LENGTH_SHORT).show();
            mTableFragment.onResume();
        }

        @Override
        public void onCheckoutSuccess() {

        }

        @Override
        public void onRemoveDrinkSuccess() {
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
