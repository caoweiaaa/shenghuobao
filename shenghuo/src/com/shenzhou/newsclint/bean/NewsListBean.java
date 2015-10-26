package com.shenzhou.newsclint.bean;

import java.util.ArrayList;


public class NewsListBean extends BaseBean {
	public NewsList data;
	public static class NewsList{
		public String more;
		public ArrayList<TopNews> topnews;
		public ArrayList<News> news;
		public String countcommenturl;
	}
	
	public static class TopNews{
		public String id;
		public String title;
		public String topimage;
		public String url;
		public String pubdate;
		public boolean comment;
		public String commenturl;
		public String commentlist;
		public int commentcount;
		public String type;
	}
	
	public static class News{
		public String id;
		public String title;
		public String url;
		public String listimage;
		public String pubdate;
		public int commentcount;
		public boolean comment;
		public String commenturl;
		public String commentlist;
		public boolean isRead;
	}
}
