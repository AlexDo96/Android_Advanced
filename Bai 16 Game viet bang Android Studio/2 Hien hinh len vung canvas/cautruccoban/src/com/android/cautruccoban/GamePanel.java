package com.android.cautruccoban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

	private MainThread thread; //a. khai bao bien thread
	Element myelement;
	
public GamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);

		thread =new MainThread(getHolder(),this); //b. khoi tao bien thread 
		setFocusable(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.BLACK);
		
		if(myelement!=null)
			myelement.doDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
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

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		//c. gán trạng thái cho thread và kích cho thread chay
		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		//d. huy thread
		if(thread.isAlive())
			thread.setRunning(false);
		/*
		boolean retry=true;
		while(retry==true)
		{
			try{
				thread.join();
				retry=false;
			}
			catch(InterruptedException e)
			{
				/////
			}
		}*/
	}
}
