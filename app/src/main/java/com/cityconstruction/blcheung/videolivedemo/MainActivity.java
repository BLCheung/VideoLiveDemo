package com.cityconstruction.blcheung.videolivedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cityconstruction.blcheung.videolivedemo.adapter.ItemSelectorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private RecyclerView recyclerView;
    private List<String> programNameList = new ArrayList<>();
    private String[] programName = {
            "CCTV-1综合", "CCTV-2财经", "CCTV-3综艺", "CCTV-4中文国际", "CCTV-5体育", "CCTV-6电影",
            "CCTV-7军事农业", "CCTV-8电视剧", "CCTV-9纪录片", "CCTV-10科教", "CCTV-11戏曲",
            "CCTV-12社会与法", "CCTV-13新闻", "CCTV-14少儿", "CCTV-15音乐"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initProgram();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new ItemSelectorAdapter(this, programNameList));

    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }


    private void initProgram() {
        for (int i = 0; i < programName.length; i++) {
            programNameList.add(programName[i]);
        }
    }
}
