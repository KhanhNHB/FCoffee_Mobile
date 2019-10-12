package com.example.fcoffee.modules.table.adapter;

import android.app.Activity;
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

import com.example.fcoffee.R;
import com.example.fcoffee.modules.table.activity.TableDetailActivity;
import com.example.fcoffee.modules.table.model.DTOresponse.TableData;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private Context mContext;
    private TableData mTables;

    public TableAdapter(Context mContext, TableData Tables) {
        this.mContext = mContext;
        this.mTables = Tables;
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

        holder.mTextView.setText("Bàn " + tableNumber);

        if (status) {
            holder.mLLButtonTable.setBackgroundResource(R.drawable.button_background);
            holder.mTextView.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.mLLButtonTable.setBackgroundResource(R.drawable.custom_frame_input_important);
            holder.mTextView.setTextColor(Color.parseColor("#000000"));
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
    }


    @Override
    public int getItemCount() {
        return mTables != null ? mTables.getmTables().size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
        private LinearLayout mLLButtonTable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.img_icon_table);
            mTextView = itemView.findViewById(R.id.txt_title_table);
            mLLButtonTable = itemView.findViewById(R.id.ll_button_table);
        }
    }
}
