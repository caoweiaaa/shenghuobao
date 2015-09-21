package com.shenzhou.newsclint.view;



import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shenzhou.newsclint.MainActivity;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.adapter.MyOnClickListener;
import com.shenzhou.newsclint.adapter.ShiPinYuLeAdapter;
import com.shenzhou.newsclint.base.BasePage;
import com.shenzhou.newsclint.fragment.VideoGaoxiaoFragment;
import com.shenzhou.newsclint.fragment.VideoJingPinFragment;
import com.shenzhou.newsclint.fragment.VideoReDianFragment;
import com.shenzhou.newsclint.fragment.VideoYuLeFragment;

public class ShiPinYuLePage extends BasePage {
	 @ViewInject(R.id.txt_title)
	 private TextView tv_title;
	 @ViewInject(R.id.vPager)
	    protected ViewPager mViewPager;
	 @ViewInject(R.id.video_redian)
	    protected RadioButton mReDian;
	 @ViewInject(R.id.video_yule)
	    protected RadioButton mYuLe;
	 @ViewInject(R.id.video_gaoxiao)
	    protected RadioButton mGaoXiao;
	 @ViewInject(R.id.video_jingpin)
	    protected RadioButton mJingPin;
	 private ArrayList<Fragment> fragments;
	
	 public ShiPinYuLePage(Context ct) {
		super(ct);
	 }
	@Override
	public View intView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.shipin_center, null);
		ViewUtils.inject(this, view);
		initTitleBar(view);
		tv_title.setText("视频娱乐");
		return view;
	}

	@Override
	public void initData() {
		fragments = new ArrayList<Fragment>();
        fragments.add(new VideoReDianFragment());
        fragments.add(new VideoYuLeFragment());
        fragments.add(new VideoGaoxiaoFragment());
        fragments.add(new VideoJingPinFragment());
        mViewPager.setOffscreenPageLimit(2);
        ShiPinYuLeAdapter mAdapetr = new ShiPinYuLeAdapter(
                ((MainActivity) ct).getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mReDian.setOnClickListener(new MyOnClickListener(0, mViewPager));
        mYuLe.setOnClickListener(new MyOnClickListener(1, mViewPager));
        mGaoXiao.setOnClickListener(new MyOnClickListener(2, mViewPager));
        mJingPin.setOnClickListener(new MyOnClickListener(3, mViewPager));
	}

	@Override
	protected void processClick(View v) {

	}
	 public class MyOnPageChangeListener implements OnPageChangeListener {
	        @Override
	        public void onPageScrollStateChanged(int arg0) {

	        }

	        @Override
	        public void onPageScrolled(int arg0, float arg1, int arg2) {

	        }

	        @Override
	        public void onPageSelected(int position) {
	            mViewPager.setCurrentItem(position);
	            switch (position) {
	                case 0:
	                    setRadioButtonCheck(true, false, false, false);
	                    break;
	                case 1:
	                    setRadioButtonCheck(false, true, false, false);
	                    break;
	                case 2:
	                    setRadioButtonCheck(false, false, true, false);
	                    break;
	                case 3:
	                    setRadioButtonCheck(false, false, false, true);
	                    break;
	            }
	        }

	    }

	    private void setRadioButtonCheck(boolean b, boolean c, boolean d, boolean e) {
	        mReDian.setChecked(b);
	        mYuLe.setChecked(c);
	        mGaoXiao.setChecked(d);
	        mJingPin.setChecked(e);
	    }
}
