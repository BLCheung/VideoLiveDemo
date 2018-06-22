package com.cityconstruction.blcheung.videolivedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cityconstruction.blcheung.videolivedemo.listener.OnItemClickListener;
import com.cityconstruction.blcheung.videolivedemo.R;

/**
 * Created by BLCheung.
 * Date:2018/6/22 15:53
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    LinearLayout llItemView;
    TextView tvProgramItem;
    private OnItemClickListener listener;

    public MyViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);
        /** MyViewHolder内部处理点击事件 **/
        this.listener = listener;
        llItemView = (LinearLayout) itemView;
        llItemView.setOnClickListener(this);
        //** 控件绑定在此 **//
        tvProgramItem = itemView.findViewById(R.id.tv_program_item);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v, getAdapterPosition());
    }
}
