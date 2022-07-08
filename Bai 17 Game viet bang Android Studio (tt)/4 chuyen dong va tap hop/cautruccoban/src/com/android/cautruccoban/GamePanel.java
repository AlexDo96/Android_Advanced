package com.android.cautruccoban;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

	private MainThread thread; //a. khai bao bien thread
	Element myelement;
	ParallaxBackground background; //bien hinh nen chuyen dong

	
	ArrayList<Bullet> bullets=new ArrayList<Bullet>();
	
	int thoigiannapdan=0; //bang 10 moi ban tiep duoc, tao do tre khi ban

	Bullet motviendan;
    
    public GamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);

		thread =new MainThread(getHolder(),this); //b. khoi tao bien thread 
		setFocusable(true);
		background=new ParallaxBackground(this.getResources());
		

	}
        
	@Override
	protected void onDraw( Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.BLACK);
		background.doDrawRunning(canvas);
		thoigiannapdan++;
		
		if(myelement!=null)
		{
			myelement.doDraw(canvas);//ve may bay
			this.doDrawBullet(canvas); //ve tap hop vien dan
		}
	}
	
	//ve tap hop cac vien dan
	public void doDrawBullet(Canvas canvas)
	{
		
		Paint p=new Paint();
		p.setColor(Color.WHITE);
		p.setTextSize(20);
		//canvas.drawText("napdan:"+thoigiannapdan, 20, 20,p);
		
		//left, top, right, bottom, paint
		canvas.drawRect(10,10, thoigiannapdan*10, 20, p);
		
		if(thoigiannapdan>=10)
		{ 
			thoigiannapdan=0;
			Bullet motviendan=
					new Bullet(getResources(), myelement.mX, myelement.mY,R.drawable.lua);
		
			bullets.add(motviendan);
		}
		for(int i=0;i<bullets.size();i++)
			bullets.get(i).doDraw(canvas);

		
		for(int i=0;i<bullets.size();i++)
			if(bullets.get(i).x>canvas.getWidth())
				bullets.remove(i);
		
		
		Log.d("viendan","so vien: "+bullets.size());
		
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
