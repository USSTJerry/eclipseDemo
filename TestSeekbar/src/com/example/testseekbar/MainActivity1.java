package com.example.testseekbar;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity1 extends Activity{

	
	Button button1;
	WordSetPop wordSetPop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		
		
		button1 = findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 wordSetPop = new WordSetPop();
	             wordSetPop.showPopwindow(MainActivity1.this);
			}
		});
		 
	}



}
