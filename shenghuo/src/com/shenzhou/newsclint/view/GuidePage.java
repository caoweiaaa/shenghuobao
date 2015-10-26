package com.shenzhou.newsclint.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.shenzhou.newsclint.base.BasePage;

public class GuidePage extends BasePage {

	public GuidePage(Context ct) {
		super(ct);
	}

	@Override
	public View intView(LayoutInflater inflater) {
		TextView textView = new TextView(ct);
		textView.setText("我是指南");
		return textView;
	}

	@Override
	public void initData() {

	}

	@Override
	protected void processClick(View v) {

	}

}
