package com.example.test;



import android.content.Context;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


public class SharePopwindow extends PopupWindow {
	public void showShare(Context mContext) {


		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.share_alert, null);




		TextView tv_cancel_popview = (TextView) view.findViewById(R.id.tv_cancel_share_popview);

		LinearLayout ll_share_whatsapp = (LinearLayout) view.findViewById(R.id.ll_share_whatsapp);
		LinearLayout ll_share_facebook =  (LinearLayout) view.findViewById(R.id.ll_share_facebook);
		LinearLayout ll_share_line =  (LinearLayout) view.findViewById(R.id.ll_share_line);
		LinearLayout ll_share_wechat =  (LinearLayout) view.findViewById(R.id.ll_share_wechat);
		LinearLayout ll_share_wechatmoments =  (LinearLayout) view.findViewById(R.id.ll_share_wechatmoments);
		LinearLayout ll_share_qq =  (LinearLayout) view.findViewById(R.id.ll_share_qq);
		LinearLayout ll_share_qzone =  (LinearLayout) view.findViewById(R.id.ll_share_qzone);
		LinearLayout ll_share_sina =  (LinearLayout) view.findViewById(R.id.ll_share_sina);


        LinearLayout ll_copy_url =  (LinearLayout) view.findViewById(R.id.ll_copy_url);
        LinearLayout ll_collection =  (LinearLayout) view.findViewById(R.id.ll_collection);
        LinearLayout ll_word_set =  (LinearLayout) view.findViewById(R.id.ll_word_set);
        LinearLayout ll_report_error =  (LinearLayout) view.findViewById(R.id.ll_report_error);

		Button btnCancel = (Button) view.findViewById(R.id.btn_share_pop_cancel);
		

		View.OnClickListener listener = new View.OnClickListener() {
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.ll_share_whatsapp:
						
						break;
					case R.id.ll_share_facebook:
						break;
					case R.id.ll_share_line:
						break;
					case R.id.ll_share_wechat:
						break;
					case R.id.ll_share_wechatmoments:
						break;
					case R.id.ll_share_qq:
						break;
					case R.id.ll_share_qzone:
						break;
					case R.id.ll_share_sina:
						break;
					case R.id.ll_copy_url:
					
						break;
					case R.id.ll_collection:
					
						break;
					case R.id.ll_word_set:
						dismiss();
					
						break;
					case R.id.ll_report_error:
						dismiss();
						
						break;
					case R.id.btn_share_pop_cancel:
						dismiss();
						break;
					case R.id.tv_cancel_share_popview:
							dismiss();
						break;

				}
				dismiss();
			}


		};

		tv_cancel_popview.setOnClickListener(listener);


		ll_share_whatsapp.setOnClickListener(listener);
		ll_share_facebook.setOnClickListener(listener);
		ll_share_line.setOnClickListener(listener);
		ll_share_wechat.setOnClickListener(listener);
		ll_share_wechatmoments.setOnClickListener(listener);
		ll_share_qq .setOnClickListener(listener);
		ll_share_qzone.setOnClickListener(listener);
		ll_share_sina.setOnClickListener(listener);
		ll_copy_url.setOnClickListener(listener);
		ll_collection.setOnClickListener(listener);
		ll_word_set.setOnClickListener(listener);
		ll_report_error.setOnClickListener(listener);
		btnCancel.setOnClickListener(listener);




		this.setContentView(view);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setFocusable(true);
		this.setOutsideTouchable(false);

		this.getContentView().setFocusableInTouchMode(true);

		this.setAnimationStyle(R.style.popuStyle);
		showAtLocation(view,  Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


		
		
		this.getContentView().setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dismiss();

					return false;
				}
				return false;
			}
		});

		//在Android 6.0以上 ，只能通过拦截事件来解决
		/*this.setTouchInterceptor(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				final int x = (int) event.getX();
				final int y = (int) event.getY();

				if ((event.getAction() == MotionEvent.ACTION_DOWN)
						&& ((x < 0) || (x >= mWidth) || (y < 0) || (y >= mHeight))) {
					// donothing
					// 消费事件
					LogUtils.error("sharetest2222");
					return false;
				} else if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					//Log.e(TAG,"out side ...");
					LogUtils.error("sharetest33333");
					return true;
				}
				return false;
			}

		});
*/

	}

}
