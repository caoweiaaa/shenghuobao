package com.shenzhou.newsclint.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.base.BasePage;

public class HomePage extends BasePage {
	@ViewInject(R.id.txt_title)
    private TextView tv_title;
	@ViewInject(R.id.news_center_fl)
	private FrameLayout news_center_fl;
	public HomePage(Context ct) {
		super(ct);
	}
	@Override
	public View intView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.news_center_frame, null);
		ViewUtils.inject(this, view);
		initTitleBar(view);
		tv_title.setText("生活宝");
		BasePage page = new BianMinPage(ct);
		news_center_fl.removeAllViews();
		news_center_fl.addView(page.getContentView());
		return view;
	}
	@Override
	public void initData() {
	}
	@Override
	protected void processClick(View v) {

	}

}
