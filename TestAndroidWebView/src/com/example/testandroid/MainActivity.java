package com.example.testandroid;



import com.example.testandroid.webview.TestWebViewActivity;
import com.example.testandroid.webviewaction.TestWebViewActionActivity;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener{

	
   Button btn1,btn2,btn3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		
		
		btn1 = findViewById(R.id.button1);

		btn2 = findViewById(R.id.button2);
		btn3 = findViewById(R.id.button3);
		
		
		
	    setClickListener();

	}
	
	   private void setClickListener(){

			btn1.setOnClickListener(this);

			btn2.setOnClickListener(this);
			btn3.setOnClickListener(this);
			
	    }
	   
	   
	   @Override
	    public void onClick(View v) {
	        switch (v.getId()){
	            case R.id.button1:
	            	Intent intent = new Intent();
					intent.setClass(this, TestWebViewActivity.class);
					intent.putExtra("title","title");
					intent.putExtra("loadurl","https://www.openrice.com/zh/hongkong");
					this.startActivity(intent);
	            	break;
	            case R.id.button2:
	            	Intent intent1 = new Intent();
					intent1.setClass(this, TestWebViewActionActivity.class);
					intent1.putExtra("title","title");
					intent1.putExtra("loadurl","http://192.168.146.156:9001/image/preferential/files/activity_5.html");
					this.startActivity(intent1);
	                break;
	            case R.id.button3:
	            	break;

	        }
	    }




	
}
