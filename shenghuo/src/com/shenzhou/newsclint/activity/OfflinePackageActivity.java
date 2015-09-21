package com.shenzhou.newsclint.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.baidu.baidutranslate.openapi.TranslateClient;
import com.baidu.baidutranslate.openapi.callback.ICheckOfflineCallback;
import com.baidu.baidutranslate.openapi.entity.OfflinePackage;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.adapter.OfflinePackageAdapter;
import com.umeng.analytics.MobclickAgent;

public class OfflinePackageActivity extends Activity {

	// TODO 【重要】将API_KEY换成自己的
	// API_KEY获取地址 http://developer.baidu.com/console
	public static final String API_KEY = "M59gCZDyU8dIjt7dcjm59FRx";

	private TranslateClient client;

	private LinearLayout mOfflinePackageList;
	private OfflinePackageAdapter mOfflinePackageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_offline_package);

		initView();
		initTransClient();
		getOfflinePackageInfo();
	}

	private void initView() {
		mOfflinePackageList = (LinearLayout) findViewById(R.id.offline_package_list);

	}

	// 【重要】onDestroy时候注销掉翻译功能
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (client != null) {
			client.onDestroy();
		}
	}

	// 【重要】 onCreate时候初始化翻译相关功能
	private void initTransClient() {
		client = new TranslateClient(this, API_KEY);

	}

	/**
	 * 获取离线包信息
	 */
	private void getOfflinePackageInfo() {
		client.getOfflinPackageInfo(new ICheckOfflineCallback() {

			@Override
			public void onSuccess(List<OfflinePackage> packages) {
				// OfflinePackage表示现在服务器端可以下载的离线包信息. 其中state字段标识离线包状态,
				// 有IDLE(初始状态)、DOWNLOADED(已经下载)、HAS_UPDATE(有更新)三种状态。title标识离线包的标题。lang可以用来区分不同的离线包,
				// 中英(chs_eng)、中日(chs_jpa)、中韩(chs_kor)
				System.out.println(packages);
				// download(packages.get(1));
				showList(packages);
			}

			@Override
			public void onFailue(String msg) {

			}
		});
	}

	private void showList(List<OfflinePackage> packages) {
		mOfflinePackageList.removeAllViews();
		if (mOfflinePackageAdapter == null) {
			mOfflinePackageAdapter = new OfflinePackageAdapter(client);
		}
		mOfflinePackageAdapter.setData(packages);

		for (int i = 0; i < mOfflinePackageAdapter.getCount(); i++) {
			mOfflinePackageList.addView(mOfflinePackageAdapter.getView(i, null,
					mOfflinePackageList));
		}
	}
	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
