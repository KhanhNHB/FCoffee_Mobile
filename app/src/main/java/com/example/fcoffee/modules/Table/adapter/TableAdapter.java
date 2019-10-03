package com.example.fcoffee.modules.Table.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.fcoffee.modules.Table.activity.TableDetailActivity;
import com.example.fcoffee.modules.Table.model.DTOresponse.TableData;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private Context mContext;
    private TableData mTables;
    public TableAdapter(Context mContext, TableData Tables) {
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
        final int tableNumber = mTables.getmTables().get(position).getTableNumber();

        holder.mTextView.setText("Bàn " + tableNumber);
        holder.mLLButtonTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TableDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("table_number", tableNumber);
                intent.putExtra("anonymouse_number", bundle);

                mContext.startActivity(intent);
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
