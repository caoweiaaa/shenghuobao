package com.shenzhou.newsclint.base;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shenzhou.newsclint.MainActivity;
import com.shenzhou.newsclint.utils.SHApi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements OnClickListener{
    public View tootView;
    protected SlidingMenu sm;
    protected Context ct;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		sm = ((MainActivity)getActivity()).getSlidingMenu();
		intData(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ct = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		tootView = intView(inflater);
		return tootView;
	}
    public View getView(){
		return tootView;
    }
	protected abstract View intView(LayoutInflater inflater);
	
	protected abstract void intData(Bundle savedInstanceState);
    
	protected abstract void processClick(View v);
	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		
	}
	 // ��Ƶ http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
    public String getVideoUrl(String index, String videoId) {
        String urlString = SHApi.Video + videoId + SHApi.VideoCenter + index + SHApi.videoEndUrl;
        return urlString;
    }

}
