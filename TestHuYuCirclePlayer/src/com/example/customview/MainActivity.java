package com.example.customview;

import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;



import com.example.testcircle.R;

public class MainActivity extends Activity {

    private CustomCircleProgress circleProgress;
    public static final int PROGRESS_CIRCLE_STARTING = 0x110;

    private StudyDaywordCiclePlayer player;//播放器  
    public static int progress = 0;//音频进度
    
    
    String audioUrl = "https://hytts.eastday.com/resource/audiodirect/13f1dfc7e8f2a3a41ecaf9fe4bab3abe.mp3";
	String urlPathAudio = "https://hytts.eastday.com/sign/doListen";
    
    
    
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PROGRESS_CIRCLE_STARTING:
                    /*progress = circleProgress.getProgress();
                    circleProgress.setProgress(++progress);
                    if(progress >= 100){
                        handler.removeMessages(PROGRESS_CIRCLE_STARTING);
                        progress = 0;
                        circleProgress.setProgress(0);
                        circleProgress.setStatus(CustomCircleProgress.Status.End);//修改显示状态为完成
                    }else{
                        //延迟100ms后继续发消息，实现循环，直到progress=100
                        handler.sendEmptyMessageDelayed(PROGRESS_CIRCLE_STARTING, 100);
                    }*/
                	
                	if(progress == 0){
    					//player.playUrl(audioUrl,0,testUrlPathAudio, paramsUrlAudio);
                		
    					player.playUrl(audioUrl,0,urlPathAudio/*, paramsUrlAudio*/); 
    				}
    					
    				else{
    					//player.playUrl(audioUrl,progress,testUrlPathAudio, paramsUrlAudio);
    					player.playUrl(audioUrl,progress,urlPathAudio/*, paramsUrlAudio*/); 
    				}
    					
                    break;
            }
        }
    };
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleProgress = (CustomCircleProgress) findViewById(R.id.circleProgress);

        
    	player = new StudyDaywordCiclePlayer(circleProgress,this); 
        
        
        circleProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	playAudio();
            	/*
                if(circleProgress.getStatus() == CustomCircleProgress.Status.Starting){//如果是开始状态
                    //点击则变成关闭暂停状态
                    circleProgress.setStatus(CustomCircleProgress.Status.End);
                    //注意，当我们暂停时，同时还要移除消息，不然的话进度不会被停止
                    handler.removeMessages(PROGRESS_CIRCLE_STARTING);
                }else{
                    //点击则变成开启状态
                    circleProgress.setStatus(CustomCircleProgress.Status.Starting);
                    Message message = Message.obtain();
                    message.what = PROGRESS_CIRCLE_STARTING;
                    handler.sendMessage(message);
                }*/
            }
        });

        
        TelephonyManager telephonyManager=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new MyPhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);

    }
    
    /**
     * 只有电话来了之后才暂停音乐的播放
     */
    private final class MyPhoneListener extends android.telephony.PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
            case TelephonyManager.CALL_STATE_RINGING://电话来了
                player.callIsComing();
                break;
            case TelephonyManager.CALL_STATE_IDLE: //通话结束
                player.callIsDown(audioUrl,progress,urlPathAudio);
                break;
            }
        }
    }
    
    public void playAudio(){

		if(circleProgress.getStatus() == CustomCircleProgress.Status.Starting){//如果是开始状态
			//点击则变成关闭暂停状态
			circleProgress.setStatus(CustomCircleProgress.Status.End);
			//注意，当我们暂停时，同时还要移除消息，不然的话进度不会被停止
			handler.removeMessages(PROGRESS_CIRCLE_STARTING);
			//暂停
			player.pause();  
			progress = player.mediaPlayer.getCurrentPosition();

		}else{
			//点击则变成开启状态
			circleProgress.setStatus(CustomCircleProgress.Status.Starting);
			//String audioUrl= "http://listen.eastday.com/media/auto/2018-03-07/23fefb3f-aee7-4e9e-8280-7ff76fdfeacc.mp3"; 

			Message message = Message.obtain();
			message.what = PROGRESS_CIRCLE_STARTING;


			handler.sendMessage(message);

		}
		
	}
    
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();

		if(player.mediaPlayer.isPlaying()){
			player.shutdownTimer();
			handler.removeMessages(PROGRESS_CIRCLE_STARTING);
			player.pause();
			player.stop();
			
			
		}
		this.finish();
    }
}
