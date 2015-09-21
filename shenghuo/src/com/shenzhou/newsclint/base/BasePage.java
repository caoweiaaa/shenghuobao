package com.shenzhou.newsclint.base;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shenzhou.newsclint.MainActivity;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.utils.CommonUtil;
import com.shenzhou.newsclint.utils.CustomToast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BasePage implements OnClickListener {
	protected Context ct;
	protected View contentView;
	protected SlidingMenu sm;
	protected Button leftBtn;
	protected ImageButton rightBtn;
	protected ImageButton leftImgBtn;
	protected ImageButton rightImgBtn;
	protected TextView titleTv;
	protected LinearLayout loadfailView;
	public boolean isLoadSuccess=false;
	@ViewInject(R.id.loading_view)
	protected View loadingView;
	public BasePage(Context context) {
		ct = context;
		contentView = intView((LayoutInflater)ct
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		loadingView = contentView.findViewById(R.id.loading_view);
		loadfailView = (LinearLayout) contentView
				.findViewById(R.id.ll_load_fail);
		 if(ct instanceof MainActivity){
				sm = ((MainActivity) ct).getSlidingMenu();
		   }
		
		// initData();
	}
	public void dismissLoadingView() {
		if (loadingView != null)
			loadingView.setVisibility(View.INVISIBLE);
	}
	protected void initTitleBar(View view) {
		leftBtn = (Button) view.findViewById(R.id.btn_left);
		rightBtn = (ImageButton) view.findViewById(R.id.btn_right);
		leftImgBtn = (ImageButton) view.findViewById(R.id.imgbtn_left);
		rightImgBtn = (ImageButton) view.findViewById(R.id.imgbtn_right);
		//leftImgBtn.setImageResource(R.drawable.img_menu);
		titleTv = (TextView) view.findViewById(R.id.txt_title);
		leftBtn.setVisibility(View.GONE);
		rightBtn.setVisibility(View.GONE);
		if(leftImgBtn!=null)
		leftImgBtn.setOnClickListener(this);
	}
	public void onResume() {
		initData();
	}
	public View getContentView() {
		return contentView;
	}
	public abstract View intView(LayoutInflater inflater); 
	
	public abstract void initData();

	protected abstract void processClick(View v);
	
	protected void loadData(HttpRequest.HttpMethod method, String url,
			RequestParams params, RequestCallBack<String> callback) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 1);
		LogUtils.allowD = true;
		if (params != null) {
			if (params.getQueryStringParams() != null)
				LogUtils.d(url + params.getQueryStringParams().toString());
		} else {
			params = new RequestParams();
		}
		//设备ID
//		params.addHeader("x-deviceid", app.deviceId);
		//渠道，统计用
//		params.addHeader("x-channel", app.channel);
		if (0 == CommonUtil.isNetworkAvailable(ct)) {
			showToast("无网络，请检查网络连接！");
		} else {
			http.send(method, url, params, callback);
		}
	}
	public void showToast(String msg) {
		showToast(msg, 0);
	}
	public void showToast(String msg, int time) {
		CustomToast customToast = new CustomToast(ct, msg, time);
		customToast.show();
	}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.imgbtn_left:
//			Handler handler  = new Handler();
//			handler.postDelayed(new Runnable() {
//				@Override
//				public void run() {
//					sm.toggle();
//				}
//			}, 100);
			break;
		default:
			break;
		}
		
	}

}
