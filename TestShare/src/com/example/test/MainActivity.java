package com.example.test;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//MainActivity需要实现自定义接口
public class MainActivity extends Activity{

 

    Button  btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init();
        btnTest = (Button)findViewById(R.id.bt_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharePopwindow sharePopwindow = new SharePopwindow();
				sharePopwindow.showShare(MainActivity.this);
				
			}
		});
    }

    
}