package com.shenzhou.newsclint.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.baidutranslate.openapi.TranslateClient;
import com.baidu.baidutranslate.openapi.callback.IDownloadCallback;
import com.baidu.baidutranslate.openapi.entity.OfflinePackage;
import com.shenzhou.newsclint.R;

public class OfflinePackageAdapter {

	private static final String TAG = "OfflinePackageAdapter";

	private TranslateClient client;
	private List<OfflinePackage> datas;

	public OfflinePackageAdapter(TranslateClient client) {
		this.client = client;
	}

	public void setData(List<OfflinePackage> datas) {
		this.datas = datas;
	}

	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	public OfflinePackage getItem(int position) {
		return datas.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Context context = parent.getContext();
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_offline_package, null);
		}
		OfflinePackage data = datas.get(position);

		// 标题
		TextView title = (TextView) convertView
				.findViewById(R.id.download_title_text);
		title.setText(data.title);

		// 下载状态（未下载、已下载、有更新）
		TextView state = (TextView) convertView
				.findViewById(R.id.download_state_text);
		Button downloadBtn = (Button) convertView
				.findViewById(R.id.download_btn);

		downloadBtn.setOnClickListener(new OnDownloadClickListener(context,
				data));// 设置点击监听

		if (data.state == OfflinePackage.States.DOWNLOADED) {// 已下载
			state.setText("(" + context.getString(R.string.downloaded) + ")");
			downloadBtn.setText(R.string.delete);
		} else if (data.state == OfflinePackage.States.HAS_UPDATE) {// 有更新
			state.setText("(" + context.getString(R.string.has_update) + ")");
			downloadBtn.setText(R.string.update);
		} else {// 未下载
			state.setText("(" + context.getString(R.string.undownload) + ")");
			downloadBtn.setText(R.string.download);
		}

		// 版本已经文件大小信息
		TextView info = (TextView) convertView
				.findViewById(R.id.download_info_text);
		info.setText("版本:" + data.show_ver + " 大小:" + data.size);

		// 下载地址
		TextView url = (TextView) convertView
				.findViewById(R.id.download_url_text);
		url.setText("URL:" + data.url);

		return convertView;
	}

	private class OnDownloadClickListener implements OnClickListener {
		private Context context;
		private OfflinePackage pkg;

		int percent;

		public OnDownloadClickListener(Context context, OfflinePackage pkg) {
			this.context = context;
			this.pkg = pkg;
		}

		@Override
		public void onClick(View v) {
			final TextView tv = (TextView) v;
			String text = tv.getText().toString();
			if (text.equals(context.getString(R.string.download))
					|| text.equals(context.getString(R.string.update))) {

				tv.setText(R.string.pause);// 点击下载以后，将按钮设置为暂停

				// 没有下载或者有更新的状态
				client.downloadOfflinePackage(pkg, new IDownloadCallback() {

					@Override
					public void onStart() {// 开始下载
						Log.d(TAG, "onStart");
					}

					@Override
					public void onProgress(long downloaded, long totalCount) {
						int curpercent = (int) (downloaded * 100 / totalCount);
						// curpercent就是下载进度百分比
						if (curpercent != percent) {
							percent = curpercent;
							Log.d(TAG, "onProgress:" + percent + "%");
						}

					}

					@Override
					public void onPause() {// 暂停下载
						// 在调用client.pauseDownloadOfflinePackage(pkg)该函数的时候的回调
						// 暂停以后，可以调用client.resumeDownloadOfflinePackage(pkg)继续下载
						Log.d(TAG, "onPause");
					}

					@Override
					public void onFinish() {// 下载完成
						Log.d(TAG, "onFinish");

						tv.post(new Runnable() { // 抛到UI线程执行该操作
							@Override
							public void run() {
								tv.setText(R.string.delete);// 下载完成，将按钮设置为 删除
							}
						});
					}

					@Override
					public void onFailue(int error_code, String error_msg) {// 下载失败
						Log.d(TAG, "onFailue:" + error_msg);

					}

					@Override
					public void onCancel() {// 取消下载
						Log.d(TAG, "onCancel:");
					}
				});

			} else if (text.equals(context.getString(R.string.pause))) {
				// 当前状态为正在下载，点击以后需要暂停
				Log.d(TAG, "暂停:");

				tv.setText(R.string.resume);// 点击暂停以后，将按钮设置为继续

				client.pauseDownloadOfflinePackage(pkg);

			} else if (text.equals(context.getString(R.string.resume))) {
				// 当前状态为暂停，点击以后需要继续下载

				tv.setText(R.string.pause);// 点击继续以后，将按钮设置为暂停

				client.resumeDownloadOfflinePackage(pkg);

			} else if (text.equals(context.getString(R.string.delete))) {
				// 当前状态为已经下载，点击以后删除

				client.delOfflinePackage(pkg);

				tv.setText(R.string.download);// 点击删除以后，将按钮设置为下载

			}

		}
	}
}
