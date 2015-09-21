package com.shenzhou.newsclint.utils;


public class SHApi {
	public static final String host = "http://c.m.163.com/";
	public final static String BASE_URL = "http://zhbj.qianlong.com";
	// 新闻中心目录+列表
	public static String NEWS_CENTER_CATEGORIES = BASE_URL+"/static/api/news/categories.json";
	//天气接口
	public static String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?city=";
	//IP地址接口(ip值为空的时候 获取本地的)
	public static String IPADDRESS_URL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
	//手机信息查询
	public static String PHONEMESSAGE_URL = "https://www.baifubao.com/callback?cmd=1059&callback=phone&phone=";
	 // 视频 http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
    public static final String Video = host + "nc/video/list/";
    public static final String VideoCenter = "/n/";
    public static final String videoEndUrl = "-10.html";
    // 热点视频
    public static final String VideoReDianId = "V9LG4B3A0";
    // 娱乐视频
    public static final String VideoYuLeId = "V9LG4CHOR";
    // 搞笑视频
    public static final String VideoGaoXiaoId = "V9LG4E6VR";
    // 精品视频
    public static final String VideoJingPinId = "00850FRB";
}
