package com.example.customview;



import java.io.IOException;  
import java.util.Timer;  
import java.util.TimerTask;  

/*import com.eastday.listen_news.bean.AudioScore;
import com.eastday.listen_news.bean.HttpUtil;
import com.eastday.listen_news.utils.ToastUtils;
import com.google.gson.Gson;
*/

import android.content.Context;
import android.media.AudioManager;  
import android.media.MediaPlayer;  
import android.media.MediaPlayer.OnCompletionListener;  
import android.os.Handler;  
import android.os.Message;  
import android.util.Log;  


public class StudyDaywordCiclePlayer implements OnCompletionListener, MediaPlayer.OnPreparedListener{  
	public MediaPlayer mediaPlayer;  
	private CustomCircleProgress skbProgress;  
	public Timer mTimer=new Timer();  
	
	private  int  finalProgress;
	
	private boolean over = false;
	
	String urlPathAudio;
	//String paramsUrlAudio;
	
	Context context;

	public StudyDaywordCiclePlayer(CustomCircleProgress skbProgress,Context context)  
	{  
		this.skbProgress=skbProgress;  
		this.context = context;
		
		try {  
			mediaPlayer = new MediaPlayer();  
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);  
			//mediaPlayer.setOnBufferingUpdateListener(this);  
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setOnCompletionListener(this);
		} catch (Exception e) {  
			Log.e("mediaPlayer", "error", e);  
		}  
		
	
		mTimer.schedule(mTimerTask, 0, 100); //100
	}  

	/******************************************************* 
	 * 通过定时器和Handler来更新进度条 
	 ******************************************************/  
	TimerTask mTimerTask = new TimerTask() {  
		@Override  
		public void run() {  
			if(mediaPlayer==null)  
				return;  
			if (mediaPlayer.isPlaying() && skbProgress.isPressed() == false) {  
				handleProgress.sendEmptyMessage(0);  
				
			}  
			if(finalProgress<100 &&  isPlayOver() && (skbProgress.getStatus() == CustomCircleProgress.Status.End||skbProgress.getStatus() == CustomCircleProgress.Status.Starting)){
				handleProgress.sendEmptyMessage(1);  
			}
			if(finalProgress==100 &&  isPlayOver() && (skbProgress.getStatus() == CustomCircleProgress.Status.End||skbProgress.getStatus() == CustomCircleProgress.Status.Starting)){
				handleProgress.sendEmptyMessage(2);  
			}
		}  
	};  

	
	
	
	Handler handleProgress = new Handler() {  
		public void handleMessage(Message msg) {  
			int position = 0;
			int duration = 0;
			if(mediaPlayer!=null) {
				position = mediaPlayer.getCurrentPosition();  
				duration = mediaPlayer.getDuration();  
			}

			if (duration > 0) {  
				long pos = skbProgress.getMax() * position / duration;  

				switch (msg.what) {
				case 0:
					
						if(!isPlayOver()){
							skbProgress.setProgress((int) pos);//进度条
							finalProgress = (int) pos;
						}
				
					break;
				case 1:

					if(isPlayOver()){
						
						if(finalProgress >= 100){
						    finalProgress = 0;
						    handleProgress.removeMessages(1);
							MainActivity.progress = 0;
							skbProgress.setProgress(0);
							skbProgress.setStatus(CustomCircleProgress.Status.End);//修改显示状态为完成
							setPlayOver(false);
						
						
						}else{
							finalProgress = finalProgress+3;
							if(finalProgress>=100)
								finalProgress = 100;
							skbProgress.setProgress(finalProgress);
							handleProgress.sendEmptyMessageDelayed(0, 5);//10
							
						}
					}
					break;
				case 2:
					 finalProgress = 0;
					    handleProgress.removeMessages(2);
						MainActivity.progress = 0;
						skbProgress.setProgress(0);
						skbProgress.setStatus(CustomCircleProgress.Status.End);//修改显示状态为完成
						setPlayOver(false);

					break;
				
				default:
					break;
				}
				
			}  

			
		};  
	};  
	//*****************************************************  

	public void play()  
	{  
		mediaPlayer.start();  
	}  

	public void playUrl(String videoUrl,int progress,String urlPathAudio/*, String paramsUrlAudio*/)  
	{  
		
		//ToastUtils.showToast(videoUrl+"==="+urlPathAudio);
		//this.urlPathAudio = urlPathAudio;
		//this.paramsUrlAudio = "6ca05576-6254-11e8-bb4b-00155df13106&audioSrc=resource%2Faudiodirect%2F13f1dfc7e8f2a3a41ecaf9fe4bab3abe.mp3&checkCode=982cbd4cfe2f331ec3a66c254e4231ed"/*paramsUrlAudio*/;
		try {  
			
			mediaPlayer.reset();
			mediaPlayer.setDataSource(videoUrl);
			mediaPlayer.prepare();//prepare之后自动播放  
			mediaPlayer.seekTo(progress);
		
			//mediaPlayer.start();  
		} catch (IllegalArgumentException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		} catch (IllegalStateException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		} catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
	}  



	public void  pause()  
	{  

		mediaPlayer.pause();  

	}  

	public void stop()  
	{  
		if (mediaPlayer != null) {   
			mediaPlayer.stop();  
			mediaPlayer.release();   
			
			mediaPlayer = null; 
		
		}   
	}  


	public int getPlayerRate(){
		int rate;
		rate = skbProgress.getMax()*mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration();
		
		return rate;
	}
	
	
	
	@Override  
	/**  
	 * 通过onPrepared播放  
	 */  
	public void onPrepared(MediaPlayer arg0) {  
		setPlayOver(false);
		arg0.start();  
		Log.e("mediaPlayer", "onPrepared");  
	}  

	@Override  
	public void onCompletion(MediaPlayer arg0) {  
		Log.e("mediaPlayer", "onCompletion");  
		setPlayOver(true);
	
	}  

	/*@Override  
	public void onBufferingUpdate(MediaPlayer arg0, int bufferingProgress) {  
		skbProgress.setSecondaryProgress(bufferingProgress);  
		int currentProgress=skbProgress.getMax()*mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration();  
		Log.e(currentProgress+"% play", bufferingProgress + "% buffer");  
	}  */

	public boolean isPlayOver() {
		return over;
	}

	public void setPlayOver(boolean over) {
		this.over = over;
	}
	
		public void shutdownTimer() {
			mTimer.cancel();
			mTimer = null;
			mTimerTask.cancel();
			mTimerTask = null;
		}
		
		
		

		/**
		 * 来电话了
		 */
		public void callIsComing() {
			if (mediaPlayer.isPlaying()) {
				MainActivity.progress = mediaPlayer.getCurrentPosition();// 获得当前播放位置
				mediaPlayer.stop();
			}
		}

		/**
		 * 通话结束
		 */
		public void callIsDown(String videoUrl,int progress,String urlPathAudio/*, String paramsUrlAudio*/) {
			if (MainActivity.progress > 0 && !isPlayOver()) {
				playUrl(videoUrl,progress,urlPathAudio); 
			}
		}

}  