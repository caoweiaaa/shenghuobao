package com.shenzhou.newsclint.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.shenzhou.newsclint.bean.BaseBean;

public class WeatherParser {
	public static <T extends BaseBean> T parser (String jsonString,Class<T> cls){
		if(jsonString!=null){
			String json = beforeParser(jsonString);
			if(!TextUtils.isEmpty(json)){
				return GsonUtil.changeGsonToBean(jsonString, cls);
			}
		}
		return null;
	}
	private static String beforeParser(String jsonString){
		String desc;
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			desc = jsonObject.getString("desc");
			if(desc.equals("OK")){
			return jsonString;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
}
