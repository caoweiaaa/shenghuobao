package com.shenzhou.newsclint.fragment;

import java.util.ArrayList;
import java.util.Currency;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shenzhou.newsclint.MainActivity;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.base.BaseFragment;
import com.shenzhou.newsclint.base.BasePage;
import com.shenzhou.newsclint.view.GuidePage;
import com.shenzhou.newsclint.view.HomePage;
import com.shenzhou.newsclint.view.NewCenterPage;
import com.shenzhou.newsclint.view.ShiPinYuLePage;
import com.shenzhou.newsclint.view.SettingPage;
import com.shenzhou.newsclint.widget.CustomViewPager;
import com.shenzhou.newsclint.widget.LazyViewPager.OnPageChangeListener;
import com.umeng.analytics.MobclickAgent;

public class HomeFragment extends BaseFragment {
    public RadioGroup radioGroup;
    private MenuFragment menuFragment;
    private HomePageAdapter adapter;
    private CustomViewPager viewPager;
    private int curIndex;
    private ArrayList<BasePage> pages = new ArrayList<BasePage>();
    private int curCheckId;
	@Override
	protected View intView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.frag_home, null);
		viewPager = (CustomViewPager) view.findViewById(R.id.viewpager);
		radioGroup = (RadioGroup) view.findViewById(R.id.main_radio);
		return view;
	}

	@Override
	protected void intData(Bundle savedInstanceState) {
		menuFragment = (MenuFragment)((MainActivity)getActivity())
				.getSupportFragmentManager().findFragmentByTag("Menu");
		pages.add(new HomePage(ct));
		pages.add(new NewCenterPage(ct));
		pages.add(new ShiPinYuLePage(ct));
		//pages.add(new GuidePage(ct));
		pages.add(new SettingPage(ct));
		adapter = new HomePageAdapter(pages, ct);
		viewPager.setAdapter(adapter);
		viewPager.setScrollable(false);
		viewPager.setOffscreenPageLimit(0);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
		viewPager.setCurrentItem(0);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home :
					sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
					curIndex = 0;
					viewPager.setCurrentItem(0,false);
					break;
				case R.id.rb_news_center :
					sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
					NewCenterPage page = (NewCenterPage) pages.get(1);
//					page.initData();
					pages.get(1).onResume();
					curIndex=1;
					viewPager.setCurrentItem(1, false);
					if (menuFragment != null) {
						menuFragment.setMenuType(MenuFragment.NEWS_CENTER);
					}
					break;
				case R.id.rb_service :
					curIndex=2;
					ShiPinYuLePage spage = (ShiPinYuLePage) pages.get(2);
					spage.initData();
					viewPager.setCurrentItem(2, false);
					break;
				case R.id.rb_setting :
					sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
					curIndex=3;
					viewPager.setCurrentItem(4, false);
					break;
				}
				curCheckId = checkedId;
			}
		});
		radioGroup.check(curCheckId);
	}
	public NewCenterPage getNewsCenterPage() {
		NewCenterPage page =(NewCenterPage) pages.get(1);
		return page;

	}
    class HomePageAdapter extends PagerAdapter {
        private ArrayList<BasePage> basePages;
        private Context context;
        
		public HomePageAdapter(ArrayList<BasePage> basePages, Context context) {
			super();
			this.basePages = basePages;
			this.context = context;
		}

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((CustomViewPager) container).removeView(pages.get(position)
					.getContentView());
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((CustomViewPager)container).addView(pages.get(position).getContentView());
			return pages.get(position).getContentView();
		}
    	
    }
	@Override
	protected void processClick(View v) {

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
