package com.cityconstruction.blcheung.videolivedemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class LiveActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";
    private static final int RETRY_TIMES = 5;
    private static final int AUTO_HIDE_TIME = 5000;
    private final String TAG = this.getClass().getSimpleName();
    private int count = 0;
    private String mUrl;
    private String mTitle;
    private VideoView mVideoView;
    /**
     * 顶部底部panel
     */
    private LinearLayout llTopPanel, llBottomPanel;
    private RelativeLayout rlLoadingLayout, rlRootView;
    private ImageView ivBack, ivPlay;
    private TextView tvTitleName, tvSystemTime;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);
        Log.d(TAG, "onCreate: " + mTitle + ": " + mUrl);
        initView();
        initVideoView();

        rlRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llTopPanel.getVisibility() == View.VISIBLE
                        || llBottomPanel.getVisibility() == View.VISIBLE) {
                    llTopPanel.setVisibility(View.GONE);
                    llBottomPanel.setVisibility(View.GONE);
                    return;
                }
                llTopPanel.setVisibility(View.VISIBLE);
                llBottomPanel.setVisibility(View.VISIBLE);
                // 5秒后无操作就GONE掉
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        llTopPanel.setVisibility(View.GONE);
                        llBottomPanel.setVisibility(View.GONE);
                    }
                }, AUTO_HIDE_TIME);
            }
        });
    }


    private void initView() {
        mVideoView = findViewById(R.id.io_surface_view);
        tvTitleName = findViewById(R.id.tv_titleName);
        tvTitleName.setText(mTitle);
        tvSystemTime = findViewById(R.id.tv_system_time);
        tvSystemTime.setText(getCurrentTime());
        ivBack = findViewById(R.id.iv_back);
        // 返回
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivPlay = findViewById(R.id.iv_play);
        rlLoadingLayout = findViewById(R.id.rl_loading_layout);
        rlRootView = findViewById(R.id.rl_rootView);
        llTopPanel = findViewById(R.id.ll_top_panel);
        llBottomPanel = findViewById(R.id.ll_bottom_panel);
    }

    private void initVideoView() {
        Vitamio.isInitialized(getApplicationContext());
        mVideoView.setVideoURI(Uri.parse(mUrl));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (count > RETRY_TIMES) {
                    new AlertDialog.Builder(LiveActivity.this)
                            .setMessage("直播源出错或超时,请重试!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setCancelable(false)
                            .show();
                } else {
                    mVideoView.stopPlayback();
                    mVideoView.setVideoURI(Uri.parse(mUrl));
                }
                count++;
                return false;
            }
        });

        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        rlLoadingLayout.setVisibility(View.VISIBLE);
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        // 边下边播
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        // 解码时音频先出来,视频后出来
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                        rlLoadingLayout.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
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
