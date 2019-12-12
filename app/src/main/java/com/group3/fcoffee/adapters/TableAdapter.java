package com.group3.fcoffee.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group3.fcoffee.R;
import com.group3.fcoffee.activities.TableDetailActivity;
import com.group3.fcoffee.models.Table;

import java.util.List;

    public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private Context mContext;
    private List<Table> mTables;

    public TableAdapter(Context mContext, List<Table> Tables) {
        this.mContext = mContext;
        this.mTables = Tables;
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
        final String tableId = mTables.get(position).getId();
        final String tableName = mTables.get(position).getName();
        final int available = mTables.get(position).getAvailable();

        holder.mTableName.setText(tableName);

        if (mTables.get(position).getAvailable() == 0) {
            holder.mLLButtonTable.setBackgroundResource(R.drawable.button_background);
            holder.mTableName.setTextColor(Color.WHITE);
        }

        holder.mLLButtonTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TableDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("table_name", tableName);
                bundle.putString("table_id", tableId);
                bundle.putInt("available", available);
                intent.putExtra("table", bundle);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTables != null ? mTables.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTableName;
        private LinearLayout mLLButtonTable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.img_icon_table);
            mTableName = itemView.findViewById(R.id.txt_title_table);
            mLLButtonTable = itemView.findViewById(R.id.ll_button_table);
        }
    }
}
