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
		volume_sections.add("����");  
		volume_sections.add("��");  
		volume_sections.add("��");  
		volume_sections.add("��");  
		seekBar.initData(volume_sections);              
		seekBar.setProgress(0);     
		seekBar.setResponseOnTouch(this);//activityʵ��������Ľӿ�ResponseOnTouch��ÿ��touch��ص�onTouchResponse  
	}


	@Override
	public void onTouchResponse(int volume) {
		// TODO Auto-generated method stub
		Toast.makeText(this,"volume-->"+volume,Toast.LENGTH_SHORT).show();
        //����volume���Ǽ���������Ǽ�����4������ ��ôvolume��ȡֵ��Ϊ0��1��2��3
       Constant.TEXT_SIZE = volume;
       //����дsharedpreferences����þ�̬����
       //ˢ���б� ���鿴���ָı���Ч������
        //adapter.notifyDataSetChanged();
	}


}
