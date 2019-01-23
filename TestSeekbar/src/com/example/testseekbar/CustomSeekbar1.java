package com.example.testseekbar;

import android.content.Context;  
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;  
import android.graphics.Canvas;  
import android.graphics.Color;  
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;  
import android.util.TypedValue;  
import android.view.MotionEvent;  
import android.view.View;  
  

  
import java.util.ArrayList;  
  
  
public class CustomSeekbar1 extends View {
    private final String TAG = "CustomSeekbar";
    private int width;
    private int height;
    private int downX = 0;
    private int downY = 0;
    private int upX = 0;
    private int upY = 0;
    private int moveX = 0;
    private int moveY = 0;
    private float scale = 0;
    private int perWidth = 0;
    private Paint mPaint;
    private Paint mTextPaint;
    private Paint buttonPaint;
    private Canvas canvas;
    private Bitmap bitmap;
    private Bitmap thumb;
    private Bitmap word_large;
    private Bitmap word_small;
    //private Bitmap spot;
    //private Bitmap spot_on;
    private int hotarea = 100;//���������
    private int cur_sections = 2;
    private ResponseOnTouch responseOnTouch;
    private int bitMapHeight = 50;//��һ�������ʼλ����ʼ��ͼƬ�ĳ�����76������ȡһ��ľ���
    private int textMove = 60;//�����·���ľ��룬��Ϊ����������40px���ټ���10�ļ��
    private int[] colors = new int[]{0xff0000,0x33000000};//�������ĳ�ɫ,�������Ļ�ɫ,����Ļ�ɫ
    private int textSize;
    private int circleRadius;
    private ArrayList<String> section_title;
    public CustomSeekbar1(Context context) {
        super(context);
    }
    public CustomSeekbar1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public CustomSeekbar1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cur_sections = 0;
        bitmap = Bitmap.createBitmap(900, 1100, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        thumb = BitmapFactory.decodeResource(getResources(), R.drawable.word_setting_thumb);   //����ǻ���ͼ��
        word_large = BitmapFactory.decodeResource(getResources(),R.drawable.word_large);            //�����δ�������Ľ���ͼ��
        word_small = BitmapFactory.decodeResource(getResources(),R.drawable.word_small);         //������Ѿ��������Ľ���ͼ��
        bitMapHeight = thumb.getHeight()/2;   //����Ӱ����е�ͼ���λ��  ������� ���ø�
        textMove = bitMapHeight+ 5;   //xqx  ���������СҪ�ģ����ǹ̶��ģ����忴��ĿЧ��
        textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics());   //���ִ�С���ڶ���������������
        circleRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);//��ݲ���ʾ
        mPaint.setStrokeWidth(3);
        mTextPaint = new Paint(Paint.DITHER_FLAG);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(0xffb5b5b4);
        buttonPaint = new Paint(Paint.DITHER_FLAG);
        buttonPaint.setAntiAlias(true);

    }
    /**
     * ʵ��������ã�����bar�Ķ���������
     */
    public void initData(ArrayList<String> section){
        if(section != null){
            section_title = section;
        }else {
            //���û�д�����ȷ�ķ��༶�����ݣ���Ĭ��ʹ�á��͡����С����ߡ�
            String[] str = new String[]{"��", "��", "��"};
            section_title = new ArrayList<String>();
            for (int i = 0; i < str.length; i++) {
                section_title.add(str[i]);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        width = widthSize;
        float scaleX = widthSize / 1080;
        float scaleY = heightSize / 1920;
        scale = Math.max(scaleX,scaleY);
        //�ؼ��ĸ߶�
        //height = 185;
        height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 62, getResources().getDisplayMetrics());
        setMeasuredDimension(width, height);
        width = width-bitMapHeight/2;
       // perWidth = (width - section_title.size()*spot.getWidth() - thumb.getWidth()/2) / (section_title.size()-1);
        perWidth = (width - 160)/3;
        hotarea = perWidth/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(0);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        canvas.drawBitmap(bitmap, 0, 0, null);
        mPaint.setAlpha(255);
        mPaint.setColor(colors[1]);
        
        
       /* canvas.drawText("A-", 10, height * 2 / 3, mTextPaint);
        canvas.drawText("A+", width - 50, height * 2 / 3, mTextPaint);
        
        canvas.drawLine(80, height * 2 / 3, width - 80, height * 2 / 3, mPaint);
        
        canvas.drawLine(80, height * 2 / 3, 80, height * 2 / 3-30, mPaint);
        canvas.drawLine(80+(width - 160)/3, height * 2 / 3, 80+(width - 160)/3, height * 2 / 3-30, mPaint);
        canvas.drawLine(80+(width - 160)/3*2, height * 2 / 3, 80+(width - 160)/3*2, height * 2 / 3-30, mPaint);
        canvas.drawLine(80+(width - 160), height * 2 / 3, 80+(width - 160), height * 2 / 3-30, mPaint);*/
        
        
        canvas.drawBitmap(word_small, 0, height * 1 / 3+10, buttonPaint);
        canvas.drawBitmap(word_large,  width - 40, height * 1 / 3, buttonPaint);

        canvas.drawLine(80, height * 2 / 3, width - 80, height * 2 / 3, mPaint);

        canvas.drawLine(80, height * 2 / 3, 80, height * 2 / 3-30, mPaint);
        canvas.drawLine(80+(width - 160)/3, height * 2 / 3, 80+(width - 160)/3, height * 2 / 3-30, mPaint);
        canvas.drawLine(80+(width - 160)/3*2, height * 2 / 3, 80+(width - 160)/3*2, height * 2 / 3-30, mPaint);
        canvas.drawLine(80+(width - 160), height * 2 / 3, 80+(width - 160), height * 2 / 3-30, mPaint);

        
        
        
      /*  canvas.drawLine(bitMapHeight, height * 2 / 3, width - bitMapHeight - spot_on.getWidth() / 2, height * 2 / 3, mPaint);
        
        canvas.drawLine(bitMapHeight, height * 2 / 3, bitMapHeight, (height * 2 / 3)-30, mPaint);
        canvas.drawLine(bitMapHeight+(width - bitMapHeight - spot_on.getWidth() / 2)/3, height * 2 / 3, bitMapHeight+(width - bitMapHeight - spot_on.getWidth() / 2)/3, (height * 2 / 3)-30, mPaint);
        canvas.drawLine(bitMapHeight+(width - bitMapHeight - spot_on.getWidth() / 2)/3*2, height * 2 / 3, bitMapHeight+(width - bitMapHeight - spot_on.getWidth() / 2)/3*2, (height * 2 / 3)-30, mPaint);
        canvas.drawLine(bitMapHeight+(width - bitMapHeight - spot_on.getWidth() / 2), height * 2 / 3, bitMapHeight+(width - bitMapHeight - spot_on.getWidth() / 2), (height * 2 / 3)-30, mPaint);*/
        int section = 0;
        while(section < section_title.size()){
            if(section < cur_sections) {
                //mPaint.setColor(colors[0]);
               /* canvas.drawLine(thumb.getWidth()/2 + section * perWidth + (section+1) * spot_on.getWidth(),height * 2 / 3,
                        thumb.getWidth()/2 + section * perWidth + (section+1) * spot_on.getWidth() + perWidth,height * 2 / 3-10,mPaint);
                
                canvas.drawLine(thumb.getWidth()/2 + section * perWidth + (section+1) * spot_on.getWidth(),height * 2 / 3,
                        thumb.getWidth()/2 + section * perWidth + (section+1) * spot_on.getWidth() + perWidth,height * 2 / 3,mPaint);
                canvas.drawBitmap(spot_on, thumb.getWidth()/2 + section * perWidth + section * spot_on.getWidth(),height * 2 / 3 - spot_on.getHeight()/2,mPaint);*/
            }else{
               /* mPaint.setAlpha(255);
                if(section == section_title.size()-1){
                    canvas.drawBitmap(spot,  width - spot_on.getWidth() - bitMapHeight/2, height * 2 / 3 - spot.getHeight() / 2, mPaint);
                }else {
                    canvas.drawBitmap(spot, thumb.getWidth()/2 + section * perWidth + section * spot_on.getWidth(), height * 2 / 3 - spot.getHeight() / 2, mPaint);
                }*/
            }
            if(section == cur_sections) {
            	if(section == section_title.size()-1) {
            		canvas.drawText(section_title.get(section), 80 + section * (width - 160)/3 - textSize, height * 2 / 3-50, mTextPaint);
                }else{
                    canvas.drawText(section_title.get(section), 80 + section * (width - 160)/3 - textSize / 2, height * 2 / 3-50, mTextPaint);
                }
            }
            
            section++;
        }
        /*if(cur_sections == section_title.size()-1){
            canvas.drawBitmap(thumb, width - spot_on.getWidth() - bitMapHeight/2 - thumb.getWidth() / 2,
                    height * 2 / 3 - bitMapHeight, buttonPaint);
        }else {*/
        	
        	canvas.drawBitmap(thumb, 80-thumb.getWidth()/2 + cur_sections * (width - 160)/3 ,
                    height * 2 / 3 - thumb.getWidth()/2, buttonPaint);
           // canvas.drawBitmap(thumb, thumb.getWidth()/2 + cur_sections * (width - 160)/3 + cur_sections * spot_on.getWidth() - thumb.getWidth()/4 ,
            //        height * 2 / 3 - bitMapHeight, buttonPaint);
       // }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                thumb = BitmapFactory.decodeResource(getResources(), R.drawable.word_setting_thumb);
                downX = (int) event.getX();
                downY = (int) event.getY();
                responseTouch(downX, downY);
                break;
            case MotionEvent.ACTION_MOVE:
                thumb = BitmapFactory.decodeResource(getResources(), R.drawable.word_setting_thumb);
                moveX = (int) event.getX();
                moveY = (int) event.getY();
                responseTouch(moveX, moveY);
                break;
            case MotionEvent.ACTION_UP:
                thumb = BitmapFactory.decodeResource(getResources(), R.drawable.word_setting_thumb);
                upX = (int) event.getX();
                upY = (int) event.getY();
                responseTouch(upX, upY);
                responseOnTouch.onTouchResponse(cur_sections);
                break;
        }
        return true;
    }
    private void responseTouch(int x, int y){
        if(x <= width-bitMapHeight/2) {
            cur_sections = (x + perWidth / 3) / perWidth;
        }else{
            cur_sections = section_title.size()-1;
        }
        invalidate();
    }

    //���ü���
    public void setResponseOnTouch(ResponseOnTouch response){
        //ע�� �������ǽӿڣ�ʵ���㵽����ļ����¼�����Ϊ����Զ���ؼ��̳е�View������SeekBar������ֻ��ʹ�ýӿ�ʵ�ּ���
        responseOnTouch = response;
    }


    //���ý���
    public void setProgress(int progress){
        cur_sections = progress;
        invalidate();
    }
}
