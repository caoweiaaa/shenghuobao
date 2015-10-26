package com.shenzhou.newsclint.activity;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.R.layout;
import com.shenzhou.newsclint.R.menu;
import com.shenzhou.newsclint.base.BaseActivity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VideoPlayActivity extends BaseActivity implements OnInfoListener,
OnBufferingUpdateListener, OnPreparedListener{
	@ViewInject(R.id.buffer)
	protected VideoView mVideoView;
	@ViewInject(R.id.probar)
	protected ProgressBar mProgressBar;
	@ViewInject(R.id.load_rate)
	protected TextView mLoadRate;
	@ViewInject(R.id.video_end)
	protected ImageView mVideoEnd;
	private Uri uri;
	private String playUrl;
	private String title;
	@Override
	protected void initView() {
		setContentView(R.layout.activity_video_play);
		//initTitleBar();
		ViewUtils.inject(this);
		playUrl = getIntent().getExtras().getString("playUrl");
        title = getIntent().getExtras().getString("filename");
        if ("".equals(playUrl) || playUrl == null) {
            showToast("请求地址错误");
            finish();
        }
        uri = Uri.parse(playUrl);
        mVideoView.setVideoURI(uri);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnPreparedListener(this);
		try {
            if (!LibsChecker.checkVitamioLibs(this))
                return;
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Override
	protected void initData() {
		
	}
	@Override
	protected void processClick(View v) {

	}
	@Override
	public void onPrepared(MediaPlayer mediaPlayer) {
		mediaPlayer.setPlaybackSpeed(1.0f);
	}
	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		mLoadRate.setText(percent + "%");
        mVideoView.setFileName(title);
	}
	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		 System.out.println(what);
	        switch (what) {
	            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
	                if (mVideoView.isPlaying()) {
	                    mVideoView.pause();
	                    mProgressBar.setVisibility(View.VISIBLE);
	                    mLoadRate.setText("");
	                    mLoadRate.setVisibility(View.VISIBLE);
	                }
	                break;
	            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
	                // mVideoEnd.setVisibility(View.VISIBLE);
	                mVideoView.start();
	                mProgressBar.setVisibility(View.GONE);
	                mLoadRate.setVisibility(View.GONE);
	                break;
	            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
	                break;
	        }
	        return true;
	}


}
