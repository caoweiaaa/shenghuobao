package com.shenzhou.newsclint.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.shenzhou.newsclint.bean.BaseBean;

public class QLParser {
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
		int returncode;
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			returncode = jsonObject.getInt("retcode");
			if(returncode==200){
			return jsonString;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
}
