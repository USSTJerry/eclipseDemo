package com.example.testseekbar;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements ResponseOnTouch{

	private CustomSeekbar1 seekBar;
	private ArrayList<String> volume_sections = new ArrayList<String>();   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		seekBar = findViewById(R.id.myCustomSeekBar);
		volume_sections.add("静音");  
		volume_sections.add("低");  
		volume_sections.add("中");  
		volume_sections.add("高");  
		seekBar.initData(volume_sections);              
		seekBar.setProgress(0);     
		seekBar.setResponseOnTouch(this);//activity实现了下面的接口ResponseOnTouch，每次touch会回调onTouchResponse  
	}


	@Override
	public void onTouchResponse(int volume) {
		// TODO Auto-generated method stub
		Toast.makeText(this,"volume-->"+volume,Toast.LENGTH_SHORT).show();
        //参数volume就是级别，如果我们集合有4个数据 那么volume的取值就为0、1、2、3
       Constant.TEXT_SIZE = volume;
       //这里写sharedpreferences保存该静态变量
       //刷新列表 ，查看文字改变后的效果　　
        //adapter.notifyDataSetChanged();
	}


}
