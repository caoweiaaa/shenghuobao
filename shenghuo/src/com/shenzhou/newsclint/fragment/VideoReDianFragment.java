package com.shenzhou.newsclint.fragment;

import java.util.ArrayList;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shenzhou.newsclint.MainActivity;
import com.shenzhou.newsclint.R;
import com.shenzhou.newsclint.activity.NewsDetailActivity;
import com.shenzhou.newsclint.activity.VideoPlayActivity;
import com.shenzhou.newsclint.adapter.VideoAdapter;
import com.shenzhou.newsclint.bean.ShiPinReDian;
import com.shenzhou.newsclint.bean.NewsListBean.News;
import com.shenzhou.newsclint.bean.ShiPinReDian.ShiPin;
import com.shenzhou.newsclint.bean.ShiPinYuLe;
import com.shenzhou.newsclint.utils.CommonUtil;
import com.shenzhou.newsclint.utils.Constants;
import com.shenzhou.newsclint.utils.CustomToast;
import com.shenzhou.newsclint.utils.GsonUtil;
import com.shenzhou.newsclint.utils.SHApi;
import com.shenzhou.newsclint.utils.SharePrefUtil;
import com.shenzhou.newsclint.widget.pullrefreshview.PullToRefreshBase;
import com.shenzhou.newsclint.widget.pullrefreshview.PullToRefreshListView;
import com.shenzhou.newsclint.widget.pullrefreshview.PullToRefreshBase.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class VideoReDianFragment extends ListFragment {
	@ViewInject(R.id.shipin_item_news)
	private PullToRefreshListView ptrLv;
	@ViewInject(R.id.loading_view)
	protected View loadingView;
	private int index = 0;
	private String url;
	private VideoAdapter adapter;
	 public int currentPagte = 1;
	 private ArrayList<ShiPin>list = new ArrayList<ShiPin>();
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.shipin_listview, null);
		ViewUtils.inject(this, view);
		// 上拉加载不可用 
		ptrLv.setPullLoadEnabled(false);
						 // 滚动到底自动加载可用  
		ptrLv.setScrollLoadEnabled(true);
		url = getVideoUrl(index + "", SHApi.VideoReDianId);
		ptrLv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				 loadData(HttpMethod.GET, getVideoUrl(0 + "", SHApi.VideoReDianId), null, new RequestCallBack<String>() {
	    				
	    				@Override
	    				public void onSuccess(ResponseInfo<String> responseInfo) {
	    					String result = responseInfo.result;
	    				   // LogUtils.i(result); 
	    					ShiPinReDian shiPin = GsonUtil.changeGsonToBean(result, ShiPinReDian.class);
                            adapter.clear();
                            adapter.appendList(shiPin.getV9LG4B3A0());
	    					onLoaded();
	    				}
	    				
	    				@Override
	    				public void onFailure(HttpException error, String msg) {
	    					onLoaded();
	    				}
	    			});
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				    currentPagte++;
	                index = index + 10;
	                loadData(HttpMethod.GET, getVideoUrl(index + "", SHApi.VideoReDianId), null, new RequestCallBack<String>() {
	    				
	    				@Override
	    				public void onSuccess(ResponseInfo<String> responseInfo) {
	    					String result = responseInfo.result;
	    				   // LogUtils.i(result); 
	    					ShiPinReDian shiPin = GsonUtil.changeGsonToBean(result, ShiPinReDian.class);
	    					adapter.appendList(shiPin.getV9LG4B3A0());
	    					onLoaded();
	    				}
	    				
	    				@Override
	    				public void onFailure(HttpException error, String msg) {
	    					onLoaded();
	    				}
	    			});
			}
		});
		
		return view;
    }
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		ShiPin pin = list.get(position);
		String shipinurl = pin.getMp4Hd_url();
		if(shipinurl==null)shipinurl = pin.getMp4_url();
        Intent intent = new Intent((MainActivity)getActivity(),VideoPlayActivity.class);
        intent.putExtra("playUrl", shipinurl);
        intent.putExtra("filename", pin.getTitle());
        getActivity().startActivity(intent);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		 loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {
				
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String result = responseInfo.result;
				   // LogUtils.i(result); 
					ShiPinReDian shiPin = GsonUtil.changeGsonToBean(result, ShiPinReDian.class);
					disPlay(shiPin);
					onLoaded();
				}
				
				@Override
				public void onFailure(HttpException error, String msg) {
					onLoaded();
				}
			});
	}
	protected void disPlay(ShiPinReDian shiPin) {
		if(adapter == null)
        {
		list.addAll(shiPin.getV9LG4B3A0());
		adapter = new VideoAdapter(getActivity(), list);
		}
		ptrLv.getRefreshableView().setAdapter(adapter);
	}

	public void dismissLoadingView() {
		if (loadingView != null)
			loadingView.setVisibility(View.INVISIBLE);
	}
	private void onLoaded() {
		dismissLoadingView();
		ptrLv.onPullDownRefreshComplete();
		ptrLv.onPullUpRefreshComplete();
	}
	protected void loadData(HttpRequest.HttpMethod method, String url,
			RequestParams params, RequestCallBack<String> callback) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 1);
		LogUtils.allowD = true;
		if (params != null) {
			if (params.getQueryStringParams() != null)
				LogUtils.d(url + params.getQueryStringParams().toString());
		} else {
			params = new RequestParams();
		}
		//设备ID
//		params.addHeader("x-deviceid", app.deviceId);
		//渠道，统计用
//		params.addHeader("x-channel", app.channel);
		if (0 == CommonUtil.isNetworkAvailable(getActivity())) {
			showToast("无网络，请检查网络连接！");
		} else {
			http.send(method, url, params, callback);
		}
	}
	public void showToast(String msg) {
		showToast(msg, 0);
	}
	public void showToast(String msg, int time) {
		CustomToast customToast = new CustomToast(getActivity(), msg, time);
		customToast.show();
	}
	 // ��Ƶ http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
    public String getVideoUrl(String index, String videoId) {
        String urlString = SHApi.Video + videoId + SHApi.VideoCenter + index + SHApi.videoEndUrl;
        return urlString;
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
