package com.shenzhou.newsclint.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.shenzhou.newsclint.MainActivity;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.base.BaseFragment;
import com.shenzhou.newsclint.base.QLBaseAdapter;
import com.shenzhou.newsclint.bean.WeatherInfo;
import com.shenzhou.newsclint.bean.WeatherInfo.Weather;
import com.shenzhou.newsclint.utils.WeatherIconUtils;
import com.shenzhou.newsclint.view.NewCenterPage;
import com.umeng.analytics.MobclickAgent;

public class MenuFragment extends BaseFragment{
	public static final int NEWS_CENTER = 1;
	private MainActivity act; 
    private WeatherInfoAdapter menuAdapter;
    public static int newsCenterPosition = 0;
    @ViewInject(R.id.ll_weather)
    private LinearLayout ll_background;
    @ViewInject(R.id.lv_menu_weatherinfo)
    private ListView lv_weatherInfo;
    @ViewInject(R.id.tv_menu_city)
    private TextView tv_menu_city;
    @ViewInject(R.id.tv_menu_weather)
    private TextView tv_menu_weather;
    @ViewInject(R.id.tv_menu_temputer)
    private TextView tv_menu_temputer;
    @ViewInject(R.id.tv_menu_wind)
    private TextView tv_menu_wind;
    @ViewInject(R.id.tv_menu_ganmao)
    private TextView tv_menu_ganmao;
    @ViewInject(R.id.iv_menu_weather)
    private ImageView iv_menu_weather;
    private WeatherInfo info;
    private int menuType = 0;
	private ArrayList<Weather> weathers = new ArrayList<Weather>();
	@Override
	protected View intView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.layout_left_menu, null);
		ViewUtils.inject(this, view);
		return view;
	}
    
	@Override
	protected void intData(Bundle savedInstanceState) {
          act = (MainActivity) ct;	
	}
    public void initWeatherInfo(WeatherInfo info){
    	String type = info.data.forecast.get(0).type;
    	ll_background.setBackgroundResource(WeatherIconUtils.getRawNromalBg(type));
    	tv_menu_city.setText(info.data.city);
    	tv_menu_weather.setText(type);
    	tv_menu_temputer.setText(info.data.forecast.get(0).low+","+info.data.forecast.get(0).high);
    	tv_menu_wind.setText(info.data.forecast.get(0).fengli+","+info.data.forecast.get(0).fengxiang);
    	tv_menu_ganmao.setText(info.data.ganmao);
    	iv_menu_weather.setImageResource(WeatherIconUtils.getWeatherIcon(type));
    	if(menuAdapter==null){
    		menuAdapter = new WeatherInfoAdapter(ct, info.data.forecast);
    		lv_weatherInfo.setAdapter(menuAdapter);
    	}else{
    		menuAdapter.notifyDataSetChanged();
    	}
    }
    @OnItemClick(R.id.lv_menu_weatherinfo)
    public void onNewsCenterItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		// 当前位置等于点击位置直接切换
		if (position == newsCenterPosition) {
			sm.toggle();
			return;
		}

	}
    public void setMenuType(int menuType) {
		this.menuType = menuType;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt("newsCenter_position", newsCenterPosition);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void processClick(View v) {
		
	}
    class WeatherInfoAdapter extends QLBaseAdapter<Weather, ListView>{
		public WeatherInfoAdapter(Context ct, List<Weather> list) {
			super(ct, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView==null){
				convertView = View.inflate(ct, R.layout.weather_item, null);
				holder = new ViewHolder();
				holder.iv_image = (ImageView) convertView.findViewById(R.id.iv_item_weather);
				holder.tv_time = (TextView) convertView.findViewById(R.id.tv_item_time);
				holder.tv_temputer = (TextView) convertView.findViewById(R.id.tv_item_temputer);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			Weather weather = list.get(position);
			holder.tv_time.setText(weather.date);
			holder.tv_temputer.setText(weather.low+","+weather.high);
			holder.iv_image.setImageResource(WeatherIconUtils.getWeatherIcon(weather.type));
			return convertView;
		}
    	class ViewHolder{
    		ImageView iv_image;
    		TextView tv_time;
    		TextView tv_temputer;
    	}
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen"); // 统计页面
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }
}
