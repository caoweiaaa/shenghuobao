
package com.shenzhou.newsclint.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.util.LogUtils;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.base.QLBaseAdapter;
import com.shenzhou.newsclint.bean.ShiPinReDian.ShiPin;
import com.shenzhou.newsclint.bean.ShiPinYuLe;
import com.shenzhou.newsclint.bean.NewsListBean.News;

public class VideoAdapter extends QLBaseAdapter<ShiPin, ListView> {
	BitmapUtils bitmapUtil;
    public VideoAdapter(Context ct, List<ShiPin> list) {
		super(ct, list);
		bitmapUtil = new BitmapUtils(context);
	}
    public void appendList(ArrayList<ShiPin>lists) {
        if (lists != null && lists.size() > 0) {
            this.list.addAll(lists);
        }
        notifyDataSetChanged();
    }
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
        if (convertView == null) {
        	holder = new ViewHolder();
        	convertView = View.inflate(context, R.layout.item_video, null);
        	holder.video_img = (ImageView) convertView.findViewById(R.id.video_img);
        	holder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
        	holder.video_title = (TextView) convertView.findViewById(R.id.video_title);
        	holder.video_time = (TextView) convertView.findViewById(R.id.video_time);
        	convertView.setTag(holder);
        } else {
        	holder = (ViewHolder) convertView.getTag();
        }
        ShiPin pin = list.get(position);
        holder.video_title.setText(pin.getTitle()); 
        holder.video_time.setText(pin.getPtime()); 
        bitmapUtil.display(holder.video_img, pin.getCover());
		return convertView;
	}
	
	 class ViewHolder {
	    	ImageView video_img;
	    	LinearLayout layout;
	    	TextView video_title;
	    	TextView video_time;
		}
}
