package com.example.testandroid.webview;



import com.example.testandroidwebh5.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class TestWebViewActivity extends Activity implements View.OnClickListener{

	
    LinearLayout ll_back;

    ImageView iv_service_close;
    ProgressWebView wb_service;

    TextView tv_service_title;
    String title ;
    String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		init();
	}

	private void init() {
		  Intent intent = getIntent();

	        title = intent.getStringExtra("title");
	        url = intent.getStringExtra("loadurl");
		
		   ll_back = findViewById(R.id.ll_back);

		   iv_service_close = findViewById(R.id.iv_progress_close);
		   wb_service = findViewById(R.id.wb_progress);

		   tv_service_title = findViewById(R.id.tv_progress_title);
		
		
		
		   tv_service_title.setText(title);
	       WebSettings webSettings = wb_service.getSettings();
	        //����WebView���ԣ��ܹ�ִ��Javascript�ű�
	        webSettings.setJavaScriptEnabled(true);
	        //���ÿ��Է����ļ�
	       // webSettings.setAllowFileAccess(true);
	        //����֧������
	      //  webSettings.setBuiltInZoomControls(true);
	        //������Ҫ��ʾ����ҳ
	        wb_service.loadUrl(url);
	        //����Web��ͼ
	        wb_service.setWebViewClient(new webViewClient ());
	        initHardwareAccelerate();

	        setClickListener();

	}
	
	   private void setClickListener(){
	        ll_back.setOnClickListener(this);
	        iv_service_close.setOnClickListener(this);
	    }
	   
	   
	   @Override
	    public void onClick(View v) {
	        switch (v.getId()){
	            case R.id.ll_back:
	                if (wb_service.canGoBack()) {
	                    wb_service.goBack();//�����ϸ�ҳ��
	                    iv_service_close.setVisibility(View.VISIBLE);
	                }else{
	                    finish();
	                }
	                break;
	            case R.id.iv_progress_close:
	                finish();
	                break;

	        }
	    }




	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK && wb_service.canGoBack()) {
	            iv_service_close.setVisibility(View.VISIBLE);
	            wb_service.goBack();//�����ϸ�ҳ��
	            return true;
	        }else{
	            finish();
	            return super.onKeyDown(keyCode, event);//�˳�H5����
	        }

	    }

	    //Web��ͼ
	    private class webViewClient extends WebViewClient {
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return true;
	        }
	    }

	    private void initHardwareAccelerate() {
	        try {
	            if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
	                getWindow()               .setFlags(
	                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
	                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
	            }
	        } catch (Exception e) {

	        }
	    }
	
}
