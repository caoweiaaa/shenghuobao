package com.shenzhou.newsclint.view;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shenzhou.newsclint.MainActivity;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.base.BasePage;
import com.shenzhou.newsclint.bean.NewsCenterCategories;
import com.shenzhou.newsclint.bean.NewsCenterCategories.NewsCategory;
import com.shenzhou.newsclint.fragment.MenuFragment;
import com.shenzhou.newsclint.utils.GsonUtil;
import com.shenzhou.newsclint.utils.SHApi;
import com.shenzhou.newsclint.utils.QLParser;
import com.shenzhou.newsclint.utils.SharePrefUtil;
import com.shenzhou.newsclint.utils.SharePrefUtil.KEY;

public class NewCenterPage extends BasePage {
    public ArrayList<NewsCategory>arrayList;
    public ArrayList<String>newsMenuList ;
    private ArrayList<BasePage> pageList;
    @ViewInject(R.id.txt_title)
    private TextView tv_title;
	public NewCenterPage(Context ct) {
		super(ct);
	}

	@Override
	public View intView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.news_center_frame, null);
		ViewUtils.inject(this, view);
		initTitleBar(view);
		tv_title.setText("新闻");
		return view;
	}
	public void onResume() {
		super.onResume();
	}
	@Override
	public void initData() {
		newsMenuList = new ArrayList<String>();
		pageList = new ArrayList<BasePage>();
		if(newsMenuList.size()==0){
			String result = SharePrefUtil.getString(ct, SHApi.NEWS_CENTER_CATEGORIES, "");
          if(!TextUtils.isEmpty(result)){
        	  parserData(result);
          }				
          getNewsCenterCategories();
		}
	}

	private void getNewsCenterCategories() {
		loadData(HttpMethod.GET, SHApi.NEWS_CENTER_CATEGORIES, null, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				SharePrefUtil.saveString(ct,
						 SHApi.NEWS_CENTER_CATEGORIES, responseInfo.result);
				parserData(responseInfo.result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				
			}
		});
	}

	protected void parserData(String result) {
		NewsCenterCategories categories = QLParser.parser(result, NewsCenterCategories.class);
		if(categories.retcode != 200){
			return ;
		}
		arrayList = categories.data;
//		newsMenuList.clear();
//		for (NewsCategory cate : categories.data) {
//			newsMenuList.add(cate.title);
//		}
//		((MainActivity)ct).getMenuFragment().initNewsCenterMenu(newsMenuList);
		NewsCategory newsCategory = arrayList.get(0);
		SharePrefUtil.saveString(ct, KEY.CATE_ALL_JSON,
				GsonUtil.createGsonString(newsCategory.children));
		SharePrefUtil.saveString(ct, KEY.CATE_EXTEND_ID,
				GsonUtil.createGsonString(categories.extend));
		pageList.clear();
		BasePage newsPage = new NewsPage(ct, newsCategory);
		pageList.add(newsPage);
		switchFragment(MenuFragment.newsCenterPosition);
	}
	@ViewInject(R.id.news_center_fl)
	private FrameLayout news_center_fl;

	public void switchFragment(int newsCenterPosition) {
		BasePage page = pageList.get(newsCenterPosition);
		switch (newsCenterPosition) {
		case 0:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		case 1:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		case 2:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		case 3:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		case 4:
			news_center_fl.removeAllViews();
			news_center_fl.addView(page.getContentView());
			break;
		}
		page.initData();
	}
	@Override
	protected void processClick(View v) {

	}

}
