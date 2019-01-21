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
    private ProgressView progressView;//进度条
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
        //初始化进度条
        progressView = new ProgressView(context);
        progressView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(context, 2)));
        progressView.setColor(Color.parseColor("#D8D8D8"));
        progressView.setProgress(10);
        //把进度条加到Webview中
        addView(progressView);
        //初始化设置
      //  initWebSettings();
        setWebChromeClient(new MyWebCromeClient());
        //setWebViewClient(new MyWebviewClient());
    }

    private class MyWebCromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                //加载完毕进度条消失
                progressView.setVisibility(View.GONE);
            } else {
                //更新进度
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
                //如定义nativeapi://showImg是用来查看大图，这里添加查看大图逻辑
                return true;
            } else if (scheme.equals("http") || scheme.equals("https")) {
                //处理http协议
                if (Uri.parse(url).getHost().equals("www.example.com")) {
                    // 内部网址，不拦截，用自己的webview加载
                    return false;
                } else {
                    //跳转外部浏览器
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }*/
    /**
     * dp转换成px
     *
     * @param context Context
     * @param dp      dp
     * @return px值
     */
    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}

