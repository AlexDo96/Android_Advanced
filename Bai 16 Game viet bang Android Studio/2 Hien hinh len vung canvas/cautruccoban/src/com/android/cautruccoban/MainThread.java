package com.android.cautruccoban;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

	private SurfaceHolder surfaceholder;
	private GamePanel gamepanel;
	private boolean running;
	

	
	public MainThread(SurfaceHolder surfaceholder, GamePanel gamepanel)
	{
		this.surfaceholder=surfaceholder;
		this.gamepanel=gamepanel;

	}
	
	public void setRunning(boolean r)
	{
		running=r;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		long dem=0L;
		Canvas canvas=null;
		while(running)
		{
			//1.cap nhat lai trang thai game //2.render du lieu ra man hinh
			canvas=surfaceholder.lockCanvas();
			if(canvas!=null)
			{
				gamepanel.onDraw(canvas);
				surfaceholder.unlockCanvasAndPost(canvas);
			}
			Log.d("testloop", "loop "+ (dem++));
		}
	}



}
