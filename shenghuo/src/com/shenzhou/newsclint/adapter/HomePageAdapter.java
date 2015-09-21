package com.shenzhou.newsclint.adapter;


import com.shenzhou.newsclint.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePageAdapter extends BaseAdapter {
	private Context context;
	private String[] option_titles;
	private TypedArray funn_option;
	
	public HomePageAdapter(Context context, String[] option_titles,
			TypedArray funn_option) {
		super();
		this.context = context;
		this.option_titles = option_titles;
		this.funn_option = funn_option;
	}

	@Override
	public int getCount() {
		return funn_option.length();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater mInflater = LayoutInflater.from(context);
			convertView = mInflater.inflate(R.layout.home_gridview_item, null);
			holder = new ViewHolder();
			holder.imgIcon = (ImageView) convertView
					.findViewById(R.id.option_icon);
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.option_title);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.imgIcon.setImageResource(funn_option.getResourceId(position, -1));
		holder.txtTitle.setText(option_titles[position]);
		return convertView;
	}

	class ViewHolder {
		ImageView imgIcon;
		TextView txtTitle;
	}

}
