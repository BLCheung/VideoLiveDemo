package com.cityconstruction.blcheung.videolivedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LiveActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";
    private final String TAG = this.getClass().getSimpleName();
    private String mUrl;
    private String mTitle;

    private LinearLayout llTopPanel, llBottomPanel;
    private ProgressBar pbLoading;
    private ImageView ivBack, ivPlay;
    private TextView tvTitleName, tvSystemTime, tvLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);
        initView();
    }

    private void initView() {
        tvTitleName = findViewById(R.id.tv_titleName);
        tvTitleName.setText(mTitle);
        tvSystemTime = findViewById(R.id.tv_system_time);
        tvSystemTime.setText(getCurrentTime());
        tvLoading = findViewById(R.id.tv_loading);
        ivBack = findViewById(R.id.iv_back);
        // 返回
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivPlay = findViewById(R.id.iv_play);
        pbLoading = findViewById(R.id.pb_loading);
        llTopPanel = findViewById(R.id.ll_top_panel);
        llBottomPanel = findViewById(R.id.ll_bottom_panel);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        String time = sdf.format(calendar.getTime());
        return time;
    }
}
