package com.shenzhou.newsclint.view;

import net.youmi.android.AdManager;
import net.youmi.android.listener.Interface_ActivityListener;
import net.youmi.android.offers.EarnPointsOrderList;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsChangeNotify;
import net.youmi.android.offers.PointsEarnNotify;
import net.youmi.android.offers.PointsManager;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shenzhou.newsclint.MainActivity;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.activity.BaiDuFanYIActivity;
import com.shenzhou.newsclint.activity.BaiDuMapActivity;
import com.shenzhou.newsclint.activity.CalendarActivity;
import com.shenzhou.newsclint.activity.IpSearchActivity;
import com.shenzhou.newsclint.activity.PhoneMessageActivity;
import com.shenzhou.newsclint.adapter.HomePageAdapter;
import com.shenzhou.newsclint.base.BasePage;
import com.shenzhou.newsclint.bean.WeatherInfo;
import com.shenzhou.newsclint.qrcode.CaptureActivity;
import com.shenzhou.newsclint.utils.CommonUtil;
import com.shenzhou.newsclint.utils.LocationUtils;
import com.shenzhou.newsclint.utils.LocationUtils.LocationListener;
import com.shenzhou.newsclint.utils.ProgressDialog;
import com.shenzhou.newsclint.utils.SHApi;
import com.shenzhou.newsclint.utils.WeatherParser;

public class BianMinPage extends BasePage implements PointsChangeNotify,PointsEarnNotify{
	private String[] myOptionTitles;
	private TypedArray myOptionIcons;
	private HomePageAdapter myAdatper;
	@ViewInject(R.id.home_gridview)
	private GridView gridView;
	@ViewInject(R.id.home_imageview)
	private ImageView imageview;
	private ProgressDialog dialog;
	protected LocationUtils mLocationUtils;
	private boolean isopend=false; 
	private Camera camera;
	public BianMinPage(Context context) {
		super(context);
	}

	@Override
	public View intView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.home_center_frame, null);
		ViewUtils.inject(this, view);
		// 有米android 积分墙sdk 5.0.0之后支持定制浏览器顶部标题栏的部分UI
					// setOfferBrowserConfig();

					// (可选)关闭有米log输出，建议开发者在嵌入有米过程中不要关闭，以方便随时捕捉输出信息，出问题时可以快速定位问题
					// AdManager.getInstance(Context context).setEnableDebugLog(false);

					// 初始化接口，应用启动的时候调用，参数：appId, appSecret
					AdManager.getInstance(ct).init("8a676b5774c1679d", "06f65e7e565c19e8");

					// (可选)开启用户数据统计服务,(sdk v4.08之后新增功能)默认不开启，传入false值也不开启，只有传入true才会调用
					AdManager.getInstance(ct).setUserDataCollect(true);

					// 如果使用积分广告，请务必调用积分广告的初始化接口:
					OffersManager.getInstance(ct).onAppLaunch();

					// （可选）注册积分监听-随时随地获得积分的变动情况
					PointsManager.getInstance(ct).registerNotify(this);

					// （可选）注册积分订单赚取监听（sdk v4.10版本新增功能）
					PointsManager.getInstance(ct).registerPointsEarnNotify(this);

					// (可选)设置是否在通知栏显示下载相关提示。默认为true，标识开启；设置为false则关闭。（sdk v4.10版本新增功能）
					// AdManager.setIsDownloadTipsDisplayOnNotification(false);

					// (可选)设置安装完成后是否在通知栏显示已安装成功的通知。默认为true，标识开启；设置为false则关闭。（sdk v4.10版本新增功能）
					// AdManager.setIsInstallationSuccessTipsDisplayOnNotification(false);

					// (可选)设置是否在通知栏显示积分赚取提示。默认为true，标识开启；设置为false则关闭。
					// PointsManager.setEnableEarnPointsNotification(false);

					// (可选)设置是否开启积分赚取的Toast提示。默认为true，标识开启；设置为false这关闭。
					// PointsManager.setEnableEarnPointsToastTips(false);

		myOptionTitles = ct.getResources().getStringArray(R.array.home_option_name);
		myOptionIcons = ct.getResources().obtainTypedArray(R.array.home_option_icon);
		myAdatper = new HomePageAdapter(ct, myOptionTitles, myOptionIcons);
		gridView.setAdapter(myAdatper);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch (position) {
				case 0:
					dialog = ProgressDialog.createDialog(ct);
					dialog.setCancelable(true);
					dialog.setMessage("寻找数据中。。。");
					dialog.show();
					startLocation(mCityNameStatus);
					sm.toggle();
					break;
				case 1:
					Intent intent = new Intent(ct,BaiDuMapActivity.class);
					ct.startActivity(intent);
					break;
				case 2:
					Intent intent2 = new Intent(ct,CaptureActivity.class);
					ct.startActivity(intent2);
					break;
				case 3:
					Intent intent3 = new Intent(ct,BaiDuFanYIActivity.class);
					ct.startActivity(intent3);
					break;
				case 4:
					refreshCamera();
					break;
				case 5:
					Intent intent5 = new Intent(ct,PhoneMessageActivity.class);
					ct.startActivity(intent5);
					break;
				case 6:
					Intent intent4 = new Intent(ct,IpSearchActivity.class);
					ct.startActivity(intent4);
					break;
				case 7:
					Intent intent6 = new Intent(ct,CalendarActivity.class);
					ct.startActivity(intent6);
					break;

				case 8:
					// 展示插播广告，可以不调用loadSpot独立使用
					SpotManager.getInstance(ct).showSpotAds(
							ct, new SpotDialogListener() {
								@Override
								public void onShowSuccess() {
									Log.i("YoumiAdDemo", "展示成功");
								}

								@Override
								public void onShowFailed() {
									Log.i("YoumiAdDemo", "展示失败");
								}

								@Override
								public void onSpotClosed() {
									Log.i("YoumiAdDemo", "展示关闭");
								}

							}); // //
					break;
					
				default:
					break;
				}
			}
		});
		imageview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// 调用方式一：直接打开全屏积分墙
				// OffersManager.getInstance(this).showOffersWall();

				// 调用方式二：直接打开全屏积分墙，并且监听积分墙退出的事件onDestory
				OffersManager.getInstance(ct).showOffersWall(
						new Interface_ActivityListener() {
							/**
							 * 但积分墙销毁的时候，即积分墙的Activity调用了onDestory的时候回调
							 */
							@Override
							public void onActivityDestroy(Context context) {
							}
						});
			}
		});
		//myOptionIcons.recycle();
		return view;
	}

	@Override
	public void initData() {
		
	}
    
	LocationListener mCityNameStatus = new LocationListener() {

		@Override
		public void detecting() {
		}
		@Override
		public void succeed(String name) {
          String city = name;
          LogUtils.i(city);
          String url = SHApi.WEATHER_URL+city;
          loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

  			@Override
  			public void onSuccess(ResponseInfo<String> info) {
  				 LogUtils.i(info.result);
  				 WeatherInfo weatherInfo = WeatherParser.parser(info.result, WeatherInfo.class);
  				((MainActivity) ct).getMenuFragment().initWeatherInfo(
  						weatherInfo);
  				dialog.dismiss();
  			}

  			@Override
  			public void onFailure(HttpException arg0, String arg1) {
  				showToast(ct.getResources().getString(R.string.networkno));
  				dialog.dismiss();
  			}
  		});
		}

		@Override
		public void failed() {
			showToast(ct.getResources().getString(R.string.locationno));
			dialog.dismiss();
		}

	};
	protected void startLocation(LocationListener cityNameStatus) {
		if (0 == CommonUtil.isNetworkAvailable(ct)) {
			showToast(ct.getResources().getString(R.string.networkno));
			dialog.dismiss();
			return;
		}
		if (mLocationUtils == null)
			mLocationUtils = new LocationUtils(ct, cityNameStatus);
		if (!mLocationUtils.isStarted()) {
			mLocationUtils.startLocation();// 开始定位
		}
	}
	@Override
	protected void processClick(View v) {
		
	}
	private void refreshCamera()
	  {
	    if(isopend==false){
	    	 camera = Camera.open();
             Parameters params = camera.getParameters();
             params.setFlashMode(Parameters.FLASH_MODE_TORCH);
             camera.setParameters(params);
             camera.startPreview(); // 开始亮灯

             isopend = true;
	    }else{
	    	camera.stopPreview(); // 关掉亮灯
            camera.release(); // 关掉照相机
            isopend = false;
	    }
	  }

	@Override
	public void onPointBalanceChange(int arg0) {
		
	}

	@Override
	public void onPointEarn(Context arg0, EarnPointsOrderList arg1) {
		
	}
}
