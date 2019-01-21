package com.example.testandroid.webviewaction;

import com.example.testandroid.webview.ProgressWebView;
import com.example.testandroidwebh5.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestWebViewActionActivity extends Activity implements OnClickListener {

	LinearLayout ll_back;
	ProgressWebView wb_benifit_detail;
	TextView tv_benifit_title;

	// 传递
	String title;
	String url;
	//String imageUrl;

	//截取
    String titleShare;
  //  String imgUrlShare;
    String urlShare;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview_action);
		 init();
	}

	private void init() {
		
		

		ll_back = findViewById(R.id.ll_back);
		wb_benifit_detail = findViewById(R.id.wb_action_detail);
		tv_benifit_title = (TextView)findViewById(R.id.tv_action_title);
		
		Intent intent = getIntent();

		title = intent.getStringExtra("title");
		url = intent.getStringExtra("loadurl");
		tv_benifit_title.setText(title);
		WebSettings webSettings = wb_benifit_detail.getSettings();
		wb_benifit_detail.addJavascriptInterface(new Object(), "android");

		

		// 设置WebView属性，能够执行Javascript脚本
		webSettings.setJavaScriptEnabled(true);

		// 加载需要显示的网页
		//LogUtils.error("benifitActivity===" + url);
		wb_benifit_detail.loadUrl(url);
		// 设置Web视图
		wb_benifit_detail.setWebViewClient(new webViewClient());

		setClickListener();

	}

	private void setClickListener() {
		ll_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back:
			finish();
			break;
		}
	}

	// Web视图
	private class webViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			if (url.startsWith("action://getinfo")) {
				//LogUtils.info("action://getinfo", url);
				Uri uri = Uri.parse(url);

				titleShare = uri.getQueryParameter("title");
				String newsId = uri.getQueryParameter("newsId");
				String time = uri.getQueryParameter("time");
				//imgUrlShare = uri.getQueryParameter("imgUrl");
				urlShare = uri.getQueryParameter("url");
				String videoUrl = uri.getQueryParameter("videoUrl");
				String top = uri.getQueryParameter("top");
				String infoSource = uri.getQueryParameter("infoSource");
				String abstractTest = uri.getQueryParameter("abstract");
				String icon = uri.getQueryParameter("icon");
				String newsType = uri.getQueryParameter("newsType");
				String nodeType = uri.getQueryParameter("nodeType");
				String styleType = uri.getQueryParameter("styleType");
				String pubTime = uri.getQueryParameter("pubTime");
				String duration = uri.getQueryParameter("duration");

			} else if (url.startsWith("action://share")) {
				//LogUtils.info("action://share", url);
				Uri uri = Uri.parse(url);
				String type = uri.getQueryParameter("type");
				if (type.equals("100")) {
					Toast.makeText(TestWebViewActionActivity.this, "share", Toast.LENGTH_LONG).show();
					// ZgToast.showToast("SHARE");
					//ListBean.VideolistBean videolistBean = new ListBean.VideolistBean();
					//videolistBean.setImgUrl(imgUrlShare);
					//videolistBean.setTitle(titleShare);
					//videolistBean.setUrl(urlShare);
					//SharePop sharePop = new SharePop();
					//sharePop.showPopwindow(BenifitWebViewActivity.this, videolistBean, true);
				}
			} else if (url.startsWith("action://activitylink")) {
				//LogUtils.info("action://activitylink", url);
				Uri uri = Uri.parse(url);
				String urllink = uri.getQueryParameter("url");
				//LogUtils.info("action://activitylink", urllink);

				Intent intent = new Intent();
				intent.setData(Uri.parse(urllink));// Url 就是你要打开的网址
				intent.setAction(Intent.ACTION_VIEW);
				TestWebViewActionActivity.this.startActivity(intent); // 启动浏览器
			}
			return true;

		}
	}
}
