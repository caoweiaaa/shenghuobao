package com.shenzhou.newsclint;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.application.SHApplication;
import com.shenzhou.newsclint.fragment.HomeFragment;
import com.shenzhou.newsclint.fragment.MenuFragment;
import com.shenzhou.newsclint.utils.CustomToast;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

import android.os.Bundle;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {
	private MenuFragment mMenuFragment;
	private HomeFragment mHomeFragment;
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setBehindContentView(R.layout.menu_frame);
        setContentView(R.layout.content_frame);
        UmengUpdateAgent.update(this);
        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        if(savedInstanceState==null){
          mMenuFragment = new MenuFragment();
          mHomeFragment = new HomeFragment();
          getSupportFragmentManager().beginTransaction()
          .replace(R.id.menu_frame, mMenuFragment,"Menu").commit();
          getSupportFragmentManager().beginTransaction()
          .replace(R.id.content_frame, mHomeFragment,"Home").commit();
        }
        sm.setMode(SlidingMenu.LEFT);
    }
    public MenuFragment getMenuFragment(){
		mMenuFragment = (MenuFragment) getSupportFragmentManager().findFragmentByTag("Menu");
		return mMenuFragment;
		
	}
    private long firstTime;

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - firstTime < 3000) {
			finish();
		} else {
			firstTime = System.currentTimeMillis();
//			CustomToast customToast = new CustomToast(this, "在点一次退出", 1);
//			customToast.show();
		}
	}
	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
