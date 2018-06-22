package com.cityconstruction.blcheung.videolivedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.cityconstruction.blcheung.videolivedemo.adapter.ItemSelectorAdapter;
import com.cityconstruction.blcheung.videolivedemo.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private RecyclerView recyclerView;
    private ItemSelectorAdapter adapter;
    private List<String> programNameList = new ArrayList<>();
    private List<String> programUrlList = new ArrayList<>();
    private String[] programName = {
            "CCTV-1综合", "CCTV-2财经", "CCTV-3综艺", "CCTV-4中文国际", "CCTV-5体育", "CCTV-6电影",
            "CCTV-7军事农业", "CCTV-8电视剧", "CCTV-9纪录片", "CCTV-10科教", "CCTV-11戏曲",
            "CCTV-12社会与法", "CCTV-13新闻", "CCTV-14少儿", "CCTV-15音乐"
    };

    private String[] programUrl = {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initProgramTitleAndUrl();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new ItemSelectorAdapter(this, programNameList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, LiveActivity.class);
                intent.putExtra(LiveActivity.EXTRA_TITLE, programNameList.get(position));
                intent.putExtra(LiveActivity.EXTRA_URL, programUrlList.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initProgramTitleAndUrl() {
        for (int i = 0; i < programName.length; i++) {
            programNameList.add(programName[i]);
        }
        for (int i = 0; i < programUrl.length; i++) {
            programUrlList.add(programUrl[i]);
        }
    }
}
