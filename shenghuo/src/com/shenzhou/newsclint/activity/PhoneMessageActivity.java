package com.shenzhou.newsclint.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.bean.IpSearch;
import com.shenzhou.newsclint.bean.PhoneMessage;
import com.shenzhou.newsclint.utils.CommonUtil;
import com.shenzhou.newsclint.utils.CustomToast;
import com.shenzhou.newsclint.utils.GsonUtil;
import com.shenzhou.newsclint.utils.SHApi;
import com.umeng.analytics.MobclickAgent;

public class PhoneMessageActivity extends Activity {
	 @ViewInject(R.id.btn_phone_search)
		private Button btn_ok;
	    @ViewInject(R.id.et_phone_input)
	    private EditText et_input;
	    @ViewInject(R.id.tv_phone_display1)
	    private TextView tv_display;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_message);
		ViewUtils.inject(this);
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String phone = et_input.getText().toString();
				loadData(HttpMethod.GET,SHApi.PHONEMESSAGE_URL+phone,null,
						new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						if(responseInfo.result!=null){
						String result = responseInfo.result;
						String json = result.substring(result.indexOf("(")+1, result.lastIndexOf(")"));
						PhoneMessage message = GsonUtil.changeGsonToBean(json, PhoneMessage.class);
						display(message);
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						
					}
				});
			}
		});
	}
	private void display(PhoneMessage message) {
		tv_display.setText("厂商："+message.getData().getOperator()+"   城市："+message.getData().getArea()
				+"   地域厂商："+message.getData().getArea_operator());
	}
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
		if (0 == CommonUtil.isNetworkAvailable(this)) {
			showToast("无网络，请检查网络连接！",0);
		} else {
			http.send(method, url, params, callback);
		}
	}
	public void showToast(String msg, int time) {
		CustomToast customToast = new CustomToast(this, msg, time);
		customToast.show();
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
