package com.android.cautruccoban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

	private MainThread thread; //a. khai bao bien thread
	
	public GamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		thread =new MainThread(getHolder(),this); //b. khoi tao bien thread 
		setFocusable(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
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
