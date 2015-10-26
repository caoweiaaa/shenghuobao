package com.shenzhou.newsclint.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.base.BasePage;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

public class SettingPage extends BasePage {
	@ViewInject(R.id.txt_title)
    private TextView tv_title;
    @ViewInject(R.id.newest)
    public TextView mTextViewNewest;
    @ViewInject(R.id.checkUpdate)
    public TextView TextViewNewest;
    @ViewInject(R.id.about)
    public TextView about;
    @ViewInject(R.id.update_progress)
    public ProgressBar mProgressBar;
	public SettingPage(Context ct) {
		super(ct);
	}

	@Override
	public View intView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.setting, null);
		ViewUtils.inject(this, view);
		initTitleBar(view);
		tv_title.setText("设置");
		TextViewNewest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 mTextViewNewest.setVisibility(View.GONE);
			        mProgressBar.setVisibility(View.VISIBLE);
			        checkUpdate();
			}
		});
		about.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new AlertDialog.Builder(ct)
				 .setTitle(ct.getString(R.string.abouat))
				 .setMessage(ct.getString(R.string.abouatme))
				 	.setPositiveButton(ct.getString(R.string.yes), null)
				 	.show();
			}
		});
		
		return view;
	}
	 /**
     * 手动检查更新
     */
    private void checkUpdate() {
        UmengUpdateAgent.setUpdateAutoPopup(false);
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                switch (updateStatus) {
                    case UpdateStatus.Yes: // 发现最新版
                        UmengUpdateAgent.showUpdateDialog(ct, updateInfo);
                        break;
                    case UpdateStatus.No: // 已为最新版
                        mTextViewNewest.setVisibility(View.VISIBLE);
                        break;
                    case UpdateStatus.NoneWifi: // 仅在wifi下更新
                    	showToast("没有wifi连接");
                        break;
                    case UpdateStatus.Timeout: // 连接超时
                    	showToast("连接超时");
                        break;
                }
                mProgressBar.setVisibility(View.GONE);
            }
        });
        UmengUpdateAgent.forceUpdate(ct);
    }
	@Override
	public void initData() {

	}

	@Override
	protected void processClick(View v) {

	}

}
