package com.shenzhou.newsclint.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.shenzhou.newsclint.R;


public class WeatherIconUtils {

	private WeatherIconUtils() {
	}
	
	/**
	 * 获取天气图标
	 * @param type
	 * @return
	 */
	public static int getWeatherIcon(String type) {
		// 如果是晚上
		if (isNight(System.currentTimeMillis()))
			if(Constants.SUNNY.equals(type))
				return R.drawable.ic_nightsunny_big;
		if(Constants.CLOUDY.equals(type))
				return R.drawable.ic_nightcloudy_big;
//			case Constants.HEAVY_RAIN:
//			case Constants.LIGHT_RAIN:
//			case Constants.MODERATE_RAIN:
//			case Constants.SHOWER:
				if(Constants.STORM.equals(type))
				return R.drawable.ic_nightrain_big;
//			case Constants.SNOWSTORM:
//			case Constants.LIGHT_SNOW:
//			case Constants.MODERATE_SNOW:
//			case Constants.HEAVY_SNOW:
				if(Constants.HEAVY_SNOW.equals(type))
				return R.drawable.ic_nightsnow_big;
		// 如果是白天
		if(Constants.SUNNY.equals(type))
			return R.drawable.ic_sunny_big;
			if(Constants.CLOUDY.equals(type))
			return R.drawable.ic_cloudy_big;
			if(Constants.OVERCAST.equals(type))
			return R.drawable.ic_overcast_big;
			if(Constants.FOGGY.equals(type))
			return R.drawable.tornado_day_night;
			if(Constants.SEVERE_STORM.equals(type))
			return R.drawable.hurricane_day_night;
			if(Constants.HEAVY_STORM.equals(type))
			return R.drawable.ic_heavyrain_big;
			if(Constants.STORM.equals(type))
			return R.drawable.ic_heavyrain_big;
			if(Constants.THUNDERSHOWER.equals(type))
			return R.drawable.ic_thundeshower_big;
			if(Constants.SHOWER.equals(type))
			return R.drawable.ic_shower_big;
			if(Constants.HEAVY_RAIN.equals(type))
			return R.drawable.ic_heavyrain_big;
			if(Constants.MODERATE_RAIN.equals(type))
			return R.drawable.ic_moderraterain_big;
			if(Constants.LIGHT_RAIN.equals(type))
			return R.drawable.ic_lightrain_big;
			if(Constants.SLEET.equals(type))
			return R.drawable.ic_sleet_big;
			if(Constants.SNOWSTORM.equals(type))
			return R.drawable.ic_snow_big;
			if(Constants.SNOW_SHOWER.equals(type))
			return R.drawable.ic_snow_big;
			if(Constants.HEAVY_SNOW.equals(type))
			return R.drawable.ic_heavysnow_big;
			if(Constants.MODERATE_SNOW.equals(type))
			return R.drawable.ic_snow_big;
			if(Constants.LIGHT_SNOW.equals(type))
			return R.drawable.ic_snow_big;
			if(Constants.STRONGSANDSTORM.equals(type))
			return R.drawable.ic_sandstorm_big;
			if(Constants.SANDSTORM.equals(type))
			return R.drawable.ic_sandstorm_big;
			if(Constants.SAND.equals(type))
			return R.drawable.ic_sandstorm_big;
			if(Constants.BLOWING_SAND.equals(type))
			return R.drawable.ic_sandstorm_big;
			if(Constants.ICE_RAIN.equals(type))
			return R.drawable.freezing_rain_day_night;
			if(Constants.DUST.equals(type))
			return R.drawable.ic_dust_big;
			if(Constants.HAZE.equals(type))
			return R.drawable.ic_haze_big;
//			if(Constants.SUNNY.equals(type))
//		default:
			return R.drawable.ic_default_big;
	}
	
	/**
	 * 获取天气清晰背景
	 * @param type
	 * @return
	 */
//	public static int getWeatherNromalBg(String type) {
//		if (isNight(System.currentTimeMillis()))
//			if(Constants.SUNNY.equals(type))
//				return R.drawable.bg_fine_night;
//		if(Constants.CLOUDY.equals(type))
//				return R.drawable.bg_cloudy_night;
//				if(Constants.SUNNY.equals(type))
//			case Constants.FOGGY:
//				return R.drawable.foggy_n;
//			case Constants.HEAVY_RAIN:
//			case Constants.LIGHT_RAIN:
//			case Constants.MODERATE_RAIN:
//			case Constants.SHOWER:
//			case Constants.ICE_RAIN:
//				if(Constants.SUNNY.equals(type))
//				return R.drawable.rain_n;
//			case Constants.STORM:
//				if(Constants.SUNNY.equals(type))
//				return R.drawable.storm_n;
//			case Constants.SNOWSTORM:
//			case Constants.LIGHT_SNOW:
//			case Constants.MODERATE_SNOW:
//			case Constants.HEAVY_SNOW:
//			case Constants.SNOW_SHOWER:
//				return R.drawable.snow_n;
//			default:
//				break;
//		// 如果是白天
//		case Constants.SUNNY:
//			return R.drawable.bg_fine_day;
//		case Constants.CLOUDY:
//			return R.drawable.bg_cloudy_day;
//		case Constants.OVERCAST:
//			return R.drawable.bg_overcast;
//		case Constants.FOGGY:
//			return R.drawable.bg_fog;
//		case Constants.SEVERE_STORM:
//		case Constants.HEAVY_STORM:
//		case Constants.STORM:
//			return R.drawable.storm_d;
//		case Constants.THUNDERSHOWER:
//			return R.drawable.bg_thunder_storm;
//		case Constants.SHOWER:
//		case Constants.HEAVY_RAIN:
//		case Constants.MODERATE_RAIN:
//		case Constants.LIGHT_RAIN:
//		case Constants.SLEET:
//			return R.drawable.bg_rain;
//		case Constants.SNOWSTORM:
//		case Constants.SNOW_SHOWER:
//		case Constants.HEAVY_SNOW:
//		case Constants.MODERATE_SNOW:
//		case Constants.LIGHT_SNOW:
//			return R.drawable.bg_snow;
//		case Constants.STRONGSANDSTORM:
//		case Constants.SANDSTORM:
//		case Constants.SAND:
//		case Constants.BLOWING_SAND:
//			return R.drawable.bg_sand_storm;
//		case Constants.ICE_RAIN:
//			return R.drawable.bg_rain;
//		case Constants.DUST:
//		case Constants.HAZE:
//			return R.drawable.bg_haze;
//
//		default:
//			return R.drawable.bg_na;
//	}
	/**
	 * 获取天气模糊背景
	 * @param type
	 * @return
	 */
//	public static int getWeatherBlurBg(int type) {
//		if (isNight(System.currentTimeMillis()))
//			switch (type) {
//			case Constants.SUNNY:
//				return R.drawable.bg_fine_night_blur;
//			case Constants.CLOUDY:
//				return R.drawable.bg_cloudy_night_blur;
//			case Constants.FOGGY:
//				return R.drawable.foggy_n_blur;
//			case Constants.HEAVY_RAIN:
//			case Constants.LIGHT_RAIN:
//			case Constants.MODERATE_RAIN:
//			case Constants.SHOWER:
//			case Constants.ICE_RAIN:
//				return R.drawable.rain_n_blur;
//			case Constants.STORM:
//				return R.drawable.storm_n_blur;
//			case Constants.SNOWSTORM:
//			case Constants.LIGHT_SNOW:
//			case Constants.MODERATE_SNOW:
//			case Constants.HEAVY_SNOW:
//			case Constants.SNOW_SHOWER:
//				return R.drawable.snow_n_blur;
//			default:
//				break;
//			}
//		// 如果是白天
//		switch (type) {
//		case Constants.SUNNY:
//			return R.drawable.bg_fine_day_blur;
//		case Constants.CLOUDY:
//			return R.drawable.bg_cloudy_day_blur;
//		case Constants.OVERCAST:
//			return R.drawable.bg_overcast_blur;
//		case Constants.FOGGY:
//			return R.drawable.bg_fog_blur;
//		case Constants.SEVERE_STORM:
//		case Constants.HEAVY_STORM:
//		case Constants.STORM:
//			return R.drawable.storm_d_blur;
//		case Constants.THUNDERSHOWER:
//			return R.drawable.bg_thunder_storm_blur;
//		case Constants.SHOWER:
//		case Constants.HEAVY_RAIN:
//		case Constants.MODERATE_RAIN:
//		case Constants.LIGHT_RAIN:
//		case Constants.SLEET:
//			return R.drawable.bg_rain_blur;
//		case Constants.SNOWSTORM:
//		case Constants.SNOW_SHOWER:
//		case Constants.HEAVY_SNOW:
//		case Constants.MODERATE_SNOW:
//		case Constants.LIGHT_SNOW:
//			return R.drawable.bg_snow_blur;
//		case Constants.STRONGSANDSTORM:
//		case Constants.SANDSTORM:
//		case Constants.SAND:
//		case Constants.BLOWING_SAND:
//			return R.drawable.bg_sand_storm_blur;
//		case Constants.ICE_RAIN:
//			return R.drawable.bg_rain_blur;
//		case Constants.DUST:
//		case Constants.HAZE:
//			return R.drawable.bg_haze_blur;
//		default:
//			return R.drawable.bg_na_blur;
//		}
//	}

	public static boolean isNight(long time) {
		SimpleDateFormat df = new SimpleDateFormat("HH");
		String timeStr = df.format(new Date(System.currentTimeMillis()));
		// L.i("liweiping", "timeStr = " + timeStr);
		try {
			int timeHour = Integer.parseInt(timeStr);
			return (timeHour >= 18 || timeHour <= 6);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 获取天气清晰背景
	 * @param type
	 * @return
	 */
	public static int getRawNromalBg(String type) {
		if (isNight(System.currentTimeMillis()))
			if(Constants.SUNNY.equals(type))
				return R.raw.bg_fine_night;
		    if(Constants.CLOUDY.equals(type))
				return R.raw.bg_cloudy_night;
//			case Constants.FOGGY:
//				return R.raw.foggy_n;
//			case Constants.HEAVY_RAIN:
//			case Constants.LIGHT_RAIN:
//			case Constants.MODERATE_RAIN:
//			case Constants.SHOWER:
//			case Constants.ICE_RAIN:
//				return R.raw.rain_n;
//			case Constants.STORM:
//				return R.raw.storm_n;
//			case Constants.SNOWSTORM:
//			case Constants.LIGHT_SNOW:
//			case Constants.MODERATE_SNOW:
//			case Constants.HEAVY_SNOW:
//			case Constants.SNOW_SHOWER:
//				return R.raw.snow_n;
		// 如果是白天
		  if(Constants.SUNNY.equals(type))
			return R.raw.bg_fine_day;
			if(Constants.CLOUDY.equals(type))
			return R.raw.bg_cloudy_day;
			if(Constants.OVERCAST.equals(type))
			return R.raw.bg_overcast;
			if(Constants.FOGGY.equals(type))
			return R.raw.bg_fog;
//		case Constants.SEVERE_STORM:
//		case Constants.HEAVY_STORM:
//		case Constants.STORM:
//			return R.raw.storm_d;
			if(Constants.THUNDERSHOWER.equals(type))
			return R.raw.bg_thunder_storm;
//		case Constants.STORM:
//		case Constants.SHOWER:
//		case Constants.HEAVY_RAIN:
//		case Constants.MODERATE_RAIN:
//		case Constants.LIGHT_RAIN:
//		case Constants.SLEET:
			if(Constants.SLEET.equals(type))
			return R.raw.bg_rain;
//		case Constants.SNOWSTORM:
//		case Constants.SNOW_SHOWER:
//		case Constants.HEAVY_SNOW:
//		case Constants.MODERATE_SNOW:
//		case Constants.LIGHT_SNOW:
			if(Constants.LIGHT_SNOW.equals(type))
			return R.raw.bg_snow;
//		case Constants.STRONGSANDSTORM:
//		case Constants.SANDSTORM:
//		case Constants.SAND:
//		case Constants.BLOWING_SAND:
			if(Constants.BLOWING_SAND.equals(type))
			return R.raw.bg_sand_storm;
			if(Constants.ICE_RAIN.equals(type))
			return R.raw.bg_rain;
//		case Constants.DUST:
//		case Constants.HAZE:
			if(Constants.HAZE.equals(type))
			return R.raw.bg_haze;

			return R.raw.bg_na;
	}
	/**
	 * 获取天气模糊背景
	 * @param type
	 * @return
	 */
//	public static int getRawBlurBg(int type) {
//		if (isNight(System.currentTimeMillis()))
//			switch (type) {
//			case Constants.SUNNY:
//				return R.raw.bg_fine_night_blur;
//			case Constants.CLOUDY:
//				return R.raw.bg_cloudy_night_blur;
////			case Constants.FOGGY:
////				return R.raw.foggy_n_blur;
////			case Constants.HEAVY_RAIN:
////			case Constants.LIGHT_RAIN:
////			case Constants.MODERATE_RAIN:
////			case Constants.SHOWER:
////			case Constants.ICE_RAIN:
////				return R.raw.rain_n_blur;
////			case Constants.STORM:
////				return R.raw.storm_n_blur;
////			case Constants.SNOWSTORM:
////			case Constants.LIGHT_SNOW:
////			case Constants.MODERATE_SNOW:
////			case Constants.HEAVY_SNOW:
////			case Constants.SNOW_SHOWER:
////				return R.raw.snow_n_blur;
//			default:
//				break;
//			}
//		// 如果是白天
//		switch (type) {
//		case Constants.SUNNY:
//			return R.raw.bg_fine_day_blur;
//		case Constants.CLOUDY:
//			return R.raw.bg_cloudy_day_blur;
//		case Constants.OVERCAST:
//			return R.raw.bg_overcast_blur;
//		case Constants.FOGGY:
//			return R.raw.bg_fog_blur;
//		case Constants.SEVERE_STORM:
//		case Constants.HEAVY_STORM:
////		case Constants.STORM:
////			return R.raw.storm_d_blur;
//		case Constants.THUNDERSHOWER:
//			return R.raw.bg_thunder_storm_blur;
//		case Constants.STORM:
//		case Constants.SHOWER:
//		case Constants.HEAVY_RAIN:
//		case Constants.MODERATE_RAIN:
//		case Constants.LIGHT_RAIN:
//		case Constants.SLEET:
//			return R.raw.bg_rain_blur;
//		case Constants.SNOWSTORM:
//		case Constants.SNOW_SHOWER:
//		case Constants.HEAVY_SNOW:
//		case Constants.MODERATE_SNOW:
//		case Constants.LIGHT_SNOW:
//			return R.raw.bg_snow_blur;
//		case Constants.STRONGSANDSTORM:
//		case Constants.SANDSTORM:
//		case Constants.SAND:
//		case Constants.BLOWING_SAND:
//			return R.raw.bg_sand_storm_blur;
//		case Constants.ICE_RAIN:
//			return R.raw.bg_rain_blur;
//		case Constants.DUST:
//		case Constants.HAZE:
//			return R.raw.bg_haze_blur;
//		default:
//			return R.raw.bg_na_blur;
//		}
//	}
}
