package com.cityconstruction.blcheung.videolivedemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cityconstruction.blcheung.videolivedemo.LiveActivity;
import com.cityconstruction.blcheung.videolivedemo.R;
import com.cityconstruction.blcheung.videolivedemo.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by BLCheung.
 * Date:2018/6/20 0:07
 */
public class ItemSelectorAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<String> programNameList;
    private OnItemClickListener listener;

    public ItemSelectorAdapter(Context context, List<String> programNameList) {
        this.context = context;
        this.programNameList = programNameList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.program_item, parent, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvProgramItem.setText(programNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return programNameList.size();
    }

    /**
     * 暴露给外部调用的item点击事件接口
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
