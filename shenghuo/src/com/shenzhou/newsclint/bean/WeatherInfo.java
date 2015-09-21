package com.shenzhou.newsclint.bean;

import java.util.ArrayList;

public class WeatherInfo extends BaseBean{
	public WeatherInfoData data;
	public String desc;	
	public int status;

	public static class WeatherInfoData{
		public int aqi;
		public String city;
		public String ganmao;
		public String wendu;
		public ArrayList<Weather>forecast = new ArrayList<WeatherInfo.Weather>();
		public Yesterday yesterday = new Yesterday();
	}  
	public static class Weather{
		public String date;
		public String fengli;
		public String fengxiang;
		public String high;
		public String low;
		public String type;
	}
	public static class Yesterday{
		public String date;
		public String fl;
		public String fx;
		public String high;
		public String low;
		public String type;
	}
	@Override
	public String toString() {
		return super.toString();
	}

}
