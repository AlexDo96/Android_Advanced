package com.example.administrator.banmaybay1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 5/20/2017.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread; //a. khai bao bien thread
    Element myelement;
    ParallaxBackground background; //bien hinh nen chuyen dong
    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread =new MainThread(getHolder(),this); //b. khoi tao bien thread
        setFocusable(true);
        background=new ParallaxBackground(this.getResources());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        background.doDrawRunning(canvas);
        if(myelement!=null)
            myelement.doDraw(canvas);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //c. gán trạng thái cho thread và kích cho thread chay
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //d. huy thread
        if(thread.isAlive())
            thread.setRunning(false);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(myelement==null)
        {
            myelement=new Element(getResources(),(int)event.getX(),(int)event.getY());
            Log.d("abc","khoi tao dau tien");
            return true;
        }
        else
        {
            myelement.mX=(int)event.getX()-myelement.bitmap.getWidth()/2;
            myelement.mY=(int)event.getY()-myelement.bitmap.getHeight()/2;
        }

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            myelement.mX=(int)event.getX()-myelement.bitmap.getWidth()/2;
            myelement.mY=(int)event.getY()-myelement.bitmap.getHeight()/2;
            Log.d("abc","ddddddddddddddddddddddddddddown");
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            myelement.mX=(int)event.getX()-myelement.bitmap.getWidth()/2;
            myelement.mY=(int)event.getY()-myelement.bitmap.getHeight()/2;
            Log.d("abc","uuuuuuuuuuuuuuuuuuuuuuuuuuuup");
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE)
        {
            myelement.mX=(int)event.getX()-myelement.bitmap.getWidth()/2;
            myelement.mY=(int)event.getY()-myelement.bitmap.getHeight()/2;
            Log.d("abc","mmmmmmmmmmmmmmmmmmmmmmmmmmove");
        }



        return true;//super.onTouchEvent(event);
    }
}
