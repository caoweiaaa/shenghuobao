package com.shenzhou.newsclint.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.baidu.baidutranslate.openapi.TranslateClient;
import com.baidu.baidutranslate.openapi.callback.ITransResultCallback;
import com.baidu.baidutranslate.openapi.entity.TransResult;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.adapter.FromToLangAdapter;
import com.umeng.analytics.MobclickAgent;

public class BaiDuFanYIActivity extends Activity implements View.OnClickListener {

	// TODO 【重要】将API_KEY换成自己的
	// API_KEY获取地址 http://developer.baidu.com/console
	public static final String API_KEY = "M59gCZDyU8dIjt7dcjm59FRx";

	private TranslateClient client;
	private EditText contentEditText;
	private TextView translateResutlText;
	private Spinner fromSpinner;
	private Spinner toSpinner;

	private FromToLangAdapter fromAdapter;
	private FromToLangAdapter toAdapter;

	private String fromLang = "auto";// 源语言
	private String toLang = "auto";// 目标语言

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fanyi_baidu);

		initTransClient();// 初始化翻译相关功能
		initView();// 初始化界面
		initLang();// 初始化语音方向
	}

	// 【重要】 onCreate时候初始化翻译相关功能
	private void initTransClient() {
		client = new TranslateClient(this, API_KEY);

		// 这里可以设置为在线优先、离线优先、 只在线、只离线 4种模式，默认为在线优先。
		client.setPriority(TranslateClient.Priority.OFFLINE_FIRST);
	}

	private void initLang() {
		fromAdapter = new FromToLangAdapter(this);
		toAdapter = new FromToLangAdapter(this);

		fromSpinner.setAdapter(fromAdapter);
		toSpinner.setAdapter(toAdapter);

		fromSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				fromLang = fromAdapter.getLang(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				fromLang = "auto";
			}
		});

		toSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				toLang = fromAdapter.getLang(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				toLang = "auto";
			}
		});
	}

	private void initView() {

		contentEditText = (EditText) findViewById(R.id.source_content_text);
		translateResutlText = (TextView) findViewById(R.id.translate_result_text);
		fromSpinner = (Spinner) findViewById(R.id.from_lang_spinner);
		toSpinner = (Spinner) findViewById(R.id.to_lang_spinner);

		findViewById(R.id.translate_btn).setOnClickListener(this);
		findViewById(R.id.offline_pakage_btn).setOnClickListener(this);
	}

	// 【重要】onDestroy时候注销掉翻译功能
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (client != null) {
			client.onDestroy();
		}
	}

	private void translate() {

		String content = contentEditText.getText().toString();// 原文
		if (TextUtils.isEmpty(content))
			return;

		// 【重要】翻译功能调用 4个参数分别为 原文、源语言方向、目标语言方向、回调
		client.translate(content, fromLang, toLang, new ITransResultCallback() {

			@Override
			public void onResult(TransResult result) {// 翻译结果回调
				if (result == null) {
					Log.d("TransOpenApiDemo", "Trans Result is null");

				} else {
					Log.d("TransOpenApiDemo",
							"MainActivity->" + result.toJSONString());
					if (result.error_code == 0) {// 没错
						translateResutlText.setText(result.trans_result);
					} else {
						translateResutlText.setText("亲！没有找到结果，请检查您的网络是否正常");
					}
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.translate_btn:
			hideSoftInput();
			translate();
			break;

		case R.id.offline_pakage_btn:
			getOfflinePackageInfo();
			break;

		default:
			break;
		}

	}

	private void getOfflinePackageInfo() {
		Intent intent = new Intent(this, OfflinePackageActivity.class);
		this.startActivity(intent);
	}

	private void hideSoftInput() {
		InputMethodManager imm = (InputMethodManager) this
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(contentEditText.getWindowToken(), 0);
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
