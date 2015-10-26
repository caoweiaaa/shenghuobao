package com.shenzhou.newsclint.adapter;

import com.shenzhou.newsclint.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class FromToLangAdapter extends BaseAdapter {
	private Context context;
	private String[] languages;
	private String[] languages_short;

	private LayoutInflater inflater;

	public FromToLangAdapter(Context ctx) {
		context = ctx;
		inflater = LayoutInflater.from(context);

		languages = ctx.getResources().getStringArray(R.array.language);
		languages_short = ctx.getResources().getStringArray(R.array.language_short);
	}

	@Override
	public int getCount() {
		return languages == null ? 0 : languages.length;
	}

	@Override
	public String getItem(int position) {
		return languages[position];
	}

	public String getLang(int position) {
		return languages_short[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = inflater.inflate(R.layout.item_language_choose, null);

		TextView language = (TextView) convertView.findViewById(R.id.language);
		language.setText(getItem(position));

		return convertView;
	}

}
