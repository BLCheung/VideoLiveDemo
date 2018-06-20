package com.cityconstruction.blcheung.videolivedemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cityconstruction.blcheung.videolivedemo.R;

import java.util.List;

/**
 * Created by BLCheung.
 * Date:2018/6/20 0:07
 */
public class ItemSelectorAdapter extends RecyclerView.Adapter<ItemSelectorAdapter.ViewHolder> {
    private String TAG = "ItemSelectorAdapter";
    private Context context;
    private List<String> programNameList;

    private String[] programUrlList = new String[]{
            "http://223.110.243.138/PLTV/2510088/224/3221227177/index.m3u8",
            "http://223.110.243.136/PLTV/2510088/224/3221227199/index.m3u8",
            "http://223.110.243.140/PLTV/2510088/224/3221227165/index.m3u8",
            "http://223.110.243.138/PLTV/2510088/224/3221227162/index.m3u8",
            "http://223.110.243.140/PLTV/2510088/224/3221227202/index.m3u8",
            "http://223.110.243.140/PLTV/2510088/224/3221227145/index.m3u8",
            "http://223.110.243.136/PLTV/2510088/224/3221227142/index.m3u8",
            "http://223.110.245.167/ott.js.chinamobile.com/PLTV/3/224/3221227204/index.m3u8",
            "http://223.110.243.140/PLTV/2510088/224/3221227156/index.m3u8",
            "http://223.110.243.140/PLTV/2510088/224/3221227161/index.m3u8",
            "http://223.110.243.140/PLTV/2510088/224/3221227174/index.m3u8",
            "http://223.110.243.140/PLTV/2510088/224/3221227171/index.m3u8",
            "http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221225508/index.m3u8",
            "http://223.110.243.140/PLTV/2510088/224/3221227247/index.m3u8",
            "http://183.207.249.7/PLTV/3/224/3221225568/index.m3u8"
    };

    public ItemSelectorAdapter(Context context, List<String> programNameList) {
        this.context = context;
        this.programNameList = programNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.program_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvProgramItem.setText(programNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return programNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvProgramItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvProgramItem = itemView.findViewById(R.id.tv_program_item);
        }
    }
}
