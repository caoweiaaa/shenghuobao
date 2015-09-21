package com.shenzhou.newsclint.bean;

import java.util.ArrayList;


public class NewsCenterCategories extends BaseBean{
    public int [] extend ;
    public ArrayList<NewsCategory> data;
    public static class NewsCategory{
    	public int id;
		public String title = "";
		public int type;
		public String url = "";
		public String url1 ="";
		public String excurl;
		public String weekurl;
		public String dayurl;
		public ArrayList<ChildNewsCate> children = new ArrayList<NewsCenterCategories.ChildNewsCate>();

    } 
    public static class ChildNewsCate{
    	public int id;
		public String title = "";
		public int type;
		public String url = "";

    }
}
