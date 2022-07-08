package com.android.cautruccoban;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
	
	ArrayList<Enemies> enemies=new ArrayList<Enemies>();
	int thoigiankethu=0;//thoi gian ra ke thu, 10 se ra
	Enemies motkethu;
    
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
		thoigiankethu++;
		
		if(myelement!=null)
		{
			myelement.doDraw(canvas);//ve may bay
			this.doDrawBullet(canvas); //ve tap hop vien dan
			this.doDrawEnemies(canvas);//ve tap hop Enemies
			xetvacham(canvas);//xet va cham
		}
	}
	
	public  void xetvacham(Canvas canvas)
	{
		try{
			for(int i=0;i<bullets.size();i++)
				for(int j=0;j<enemies.size();j++)
				{
					if(vc_b_e(bullets.get(i), enemies.get(j))==true)
					{
						bullets.remove(i);
						enemies.remove(j);
					}
				}
		}catch(Exception e)
		{
			Log.d("loi",e.toString());
		}
		
	}
	
		//ve tap hop ke thu
		public  void doDrawEnemies(Canvas canvas)
		{
			if(thoigiankethu>=10)
			{ 
				thoigiankethu=0;
				Enemies motkethu=new Enemies(getResources(),
						canvas.getWidth(),canvas.getHeight());
				enemies.add(motkethu);
			}
		
			for(int i=0;i<enemies.size();i++)
				enemies.get(i).doDraw(canvas);
		
			for(int i=0;i<enemies.size();i++)
				if(enemies.get(i).y<0)
					enemies.remove(i);	
			Log.d("viendan","so vien: "+enemies.size());	
			
		}
	
	public boolean vc_b_e(Bullet bullet,Enemies enemies)
	{
		
		float nuarong_b=(float)bullet.getWidth()/2;
		int nuacao_b=bullet.getHeight()/2;
		float nuarong_e=(float)enemies.getWidth()/2;
		int nuacao_e=enemies.getHeight()/2;
		//khoang cach 2 tam theo x
		int kc_ht_x=Math.abs(bullet.gettamX()-enemies.gettamX());
		//khoang cach 2 tam theo y
		int kc_ht_y=Math.abs(bullet.gettamY()-enemies.gettamY());
		if(kc_ht_x<=nuarong_b+nuarong_e && kc_ht_y<=nuacao_b+nuacao_e)
			return true;
		else
			return false;
	}
		
	//ve tap hop cac vien dan
	public   void doDrawBullet(Canvas canvas)
	{
		Paint p=new Paint();
		p.setColor(Color.WHITE);
		p.setTextSize(20);		
		//left, top, right, bottom, paint
		canvas.drawRect(10,10, thoigiannapdan*10, 20, p);
		
		if(thoigiannapdan>=10)
		{ 
			thoigiannapdan=0;
			Bullet motviendan=new Bullet(getResources(), 
					myelement.mX, myelement.mY,R.drawable.lua);
		
			bullets.add(motviendan);
		}
		
		//ve lai tat ca cac vien
		for(int i=0;i<bullets.size();i++)
			bullets.get(i).doDraw(canvas);
		
		//vien nao ra khoi man hinh thi xoa no luon
		for(int i=0;i<bullets.size();i++)
		{
			if(bullets.get(i).x>canvas.getWidth())
				bullets.remove(i);
		}
		Log.d("viendan","so vien: "+bullets.size());
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(myelement==null)
		{
			myelement=new Element(getResources(),
					(int)event.getX(),(int)event.getY());
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
	}
}
