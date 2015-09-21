package com.shenzhou.newsclint.application;


import java.util.Iterator;
import java.util.List;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.widget.Toast;

public class SHApplication extends Application {
	public static SHApplication mInstance = null;
	public boolean m_bKeyRight = true;
	public BMapManager mBMapManager = null;
	private static Camera m_Camera;
	 
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		int pid = android.os.Process.myPid();
		String processAppName = getAppName(pid);
		//如果使用到百度地图或者类似启动remote service的第三方库，这个if判断不能少
				if (processAppName == null || processAppName.equals("")) {
					// 百度定位sdk，定位服务运行在一个单独的进程，每次定位服务启动的时候，都会调用application::onCreate
					// 创建新的进程。
					// 则此application::onCreate 是被service 调用的，直接返回
					return;
				}
		initEngineManager(this);
	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}
		if (!mBMapManager.init(new MyGeneralListener())) {
			Toast.makeText(SHApplication.getInstance().getApplicationContext(), 
					"BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
		}
	}
	public static SHApplication getInstance() {
		return mInstance;
	}
	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	public static class MyGeneralListener implements MKGeneralListener {

		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(SHApplication.getInstance().getApplicationContext(), "您的网络出错啦！",
						Toast.LENGTH_LONG).show();
			}
			else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(SHApplication.getInstance().getApplicationContext(), "输入正确的检索条件！",
						Toast.LENGTH_LONG).show();
			}
			// ...
		}

		@Override
		public void onGetPermissionState(int iError) {
			//非零值表示key验证未通过
			if (iError != 0) {
				//授权Key错误：
//				Toast.makeText(SHApplication.getInstance().getApplicationContext(), 
//						"检查您的网络连接是否正常！error: "+iError, Toast.LENGTH_LONG).show();
				SHApplication.getInstance().m_bKeyRight = false;
			}
			else{
				SHApplication.getInstance().m_bKeyRight = true;
				Toast.makeText(SHApplication.getInstance().getApplicationContext(), 
						"key认证成功", Toast.LENGTH_LONG).show();
			}
		}
	}
	private String getAppName(int pID) {
		String processName = null;
		ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
		List l = am.getRunningAppProcesses();
		Iterator i = l.iterator();
		PackageManager pm = this.getPackageManager();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
			try {
				if (info.pid == pID) {
					CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
			}
		}
		return processName;
	}
	
}
