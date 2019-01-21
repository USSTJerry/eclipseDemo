package com.example.testandroid.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;



/**
 * Created by rui on 2019/1/5.
 */

public class ProgressWebView extends WebView {
    private ProgressView progressView;//������
    private Context context;

    public ProgressWebView(Context context) {
        this(context, null);
        Log.e("test11", "1111");
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.e("test22", "2222");
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
        Log.e("test33", "3333");
    }

    private void init() {
        //��ʼ��������
        progressView = new ProgressView(context);
        progressView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(context, 2)));
        progressView.setColor(Color.parseColor("#D8D8D8"));
        progressView.setProgress(10);
        //�ѽ������ӵ�Webview��
        addView(progressView);
        //��ʼ������
      //  initWebSettings();
        setWebChromeClient(new MyWebCromeClient());
        //setWebViewClient(new MyWebviewClient());
    }

    private class MyWebCromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                //������Ͻ�������ʧ
                progressView.setVisibility(View.GONE);
            } else {
                //���½���
                progressView.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }


   /* private class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Uri uri = Uri.parse(url);
            String scheme = uri.getScheme();
            if (TextUtils.isEmpty(scheme)) return true;
            if (scheme.equals("nativeapi")) {
                //�綨��nativeapi://showImg�������鿴��ͼ��������Ӳ鿴��ͼ�߼�
                return true;
            } else if (scheme.equals("http") || scheme.equals("https")) {
                //����httpЭ��
                if (Uri.parse(url).getHost().equals("www.example.com")) {
                    // �ڲ���ַ�������أ����Լ���webview����
                    return false;
                } else {
                    //��ת�ⲿ�����
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }*/
    /**
     * dpת����px
     *
     * @param context Context
     * @param dp      dp
     * @return pxֵ
     */
    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}

