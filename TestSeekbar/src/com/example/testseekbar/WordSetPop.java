package com.example.testseekbar;

import java.util.ArrayList;


import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class WordSetPop extends PopupWindow{

    public static int TEXT_SIZE = 0;

	private Context mContext;

	private CustomSeekbar1 seekBar;


	public void showPopwindow(final Context mContext) {


		this.mContext = mContext;
	

		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popView = inflater.inflate(R.layout.word_setting_pop, null);



		seekBar = (CustomSeekbar1) popView.findViewById(R.id.myCustomSeekBar);
		//这个集合用于给自定义SeekBar设置界点级别，集合里有几个数据，就有几个界点
		ArrayList<String> volume_sections = new ArrayList<String>();
		volume_sections.add("小");
		volume_sections.add("中");
		volume_sections.add("大");
		volume_sections.add("特大");
		seekBar.initData(volume_sections);

		//seekBar.setProgress(SpUtils.getInt(mContext, Params.FONT, 0)); //设置默认级别


		seekBar.setResponseOnTouch(new ResponseOnTouch() {
			
			@Override
			public void onTouchResponse(int volume) {
				// TODO Auto-generated method stub
				//参数volume就是级别，如果我们集合有4个数据 那么volume的取值就为0、1、2、3
				Constant.TEXT_SIZE = volume;
				Toast.makeText(mContext,"volume-->"+volume,Toast.LENGTH_SHORT).show();
				//SpUtils.putInt(mContext, Params.FONT, volume+1);
				//EventBus.getDefault().post(new RefreshFont());
			}
		});

		TextView tv_cancel_word_popview = (TextView)popView.findViewById(R.id.tv_cancel_word_popview);
		Button btnCancel = (Button) popView.findViewById(R.id.btn_word_set_pop_cancel);



		// 重写onKeyListener

		/*popView.setOnKeyListener(new View.OnKeyListener() {

			@Override

			public boolean onKey(View v, int keyCode, KeyEvent event) {

				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dismiss();
					return true;
				}

				return false;

			}
		});*/

		View.OnClickListener listener = new View.OnClickListener() {
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.tv_cancel_word_popview:
						dismiss();
						break;
					case R.id.btn_word_set_pop_cancel:
						dismiss();
						break;

				}
			
			}
		};
		tv_cancel_word_popview.setOnClickListener(listener);
		btnCancel.setOnClickListener(listener);


		this.setContentView(popView);
		this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.getContentView().setFocusableInTouchMode(true);
		this.setAnimationStyle(R.style.popwin_anim_style);
		showAtLocation(popView,  Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


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

	
	}
}
