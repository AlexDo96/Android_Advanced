package com.example.administrator.banmaybay1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/20/2017.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread; //a. khai bao bien thread
    Element myelement;
    ParallaxBackground background; //bien hinh nen chuyen dong
    ArrayList<Bullet> bullets=new ArrayList<Bullet>();
    int thoigiannapdan=0; //bang 10 moi ban tiep duoc, tao do tre khi ban

    ArrayList<Enemies>enemies=new ArrayList<Enemies>();
    int thoigiankethu=0;//thoigianrakethu, 10 sera
    Enemies motkethu;

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
        thoigiannapdan++;
        thoigiankethu++;
        if(myelement!=null) {
            myelement.doDraw(canvas);
            this.doDrawBullet(canvas); //ve tap hop vien dan
            this.doDrawEnemies(canvas);//ve tap hop Enemies
            xetvacham(canvas);//xetvacham
        }
    }

    //ve tap hop cac vien dan
    public void doDrawBullet(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(20);
        canvas.drawText("napdan:" + thoigiannapdan, 20, 20, p);
        //left, top, right, bottom, paint
        canvas.drawRect(10, 10, thoigiannapdan * 10, 20, p);
        canvas.drawArc(new RectF(20, 20, 50, 50), 0, thoigiannapdan*36,true,p);


        if(thoigiannapdan>=10) {
            thoigiannapdan=0;
            //ban tu dong
            Bullet motviendan =
                    new Bullet(getResources(), myelement.mX, myelement.mY, R.drawable.lua);
            bullets.add(motviendan);

            //dan 3 tia
            /*Bullet motviendantren =
                    new Bullet(getResources(), myelement.mX, myelement.mY, R.drawable.lua,-5);
            bullets.add(motviendantren);
            Bullet motviendanduoi =
                    new Bullet(getResources(), myelement.mX, myelement.mY, R.drawable.lua,+5);
            bullets.add(motviendanduoi);
            */

        }
        Log.d("sodan", "so vien dan" + bullets.size());
        for(int i=0;i<bullets.size();i++)
            bullets.get(i).doDraw(canvas);
        for(int i=0;i<bullets.size();i++)
            if(bullets.get(i).x>canvas.getWidth())
                bullets.remove(i);


    }

    public void xetvacham(Canvas canvas)
    {
        try{
            for(int i=0;i<bullets.size();i++)
                for(int j=0;j<enemies.size();j++)
                {
                    if(vc_b_e(bullets.get(i), enemies.get(j))==true)
                    {
                        Paint p = new Paint();
                        p.setColor(Color.RED);
                        canvas.drawCircle(bullets.get(i).gettamX(),bullets.get(i).gettamY(),100,p);
                        bullets.remove(i);
                        enemies.remove(j);
                    }
                }
        }catch(Exception e)
        {
            Log.d("loi",e.toString());
        }

    }

    //va cham vien dan va ke thu
    public boolean vc_b_e(Bullet bullet,Enemies enemies)
    {

        float nuarong_b=(float)bullet.getWidth()/2;
        float nuacao_b=bullet.getHeight()/2;
        float nuarong_e=(float)enemies.getWidth()/2;
        float nuacao_e=enemies.getHeight()/2;
        //khoangcach 2 tamtheo x
        float kc_ht_x=Math.abs(bullet.gettamX()-enemies.gettamX());
        //khoangcach 2 tamtheo y
        float kc_ht_y=Math.abs(bullet.gettamY()-enemies.gettamY());
        if(kc_ht_x<=nuarong_b+nuarong_e && kc_ht_y<=nuacao_b+nuacao_e)
            return true;
        else
            return false;
    }


    //ve tap hop kethu
    public void doDrawEnemies(Canvas canvas)
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
            if(enemies.get(i).x<0)
                enemies.remove(i);
        Log.d("viendan","so vien: "+enemies.size());
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
            //click de ban
/*            Bullet motviendan=
                    new Bullet(getResources(), myelement.mX, myelement.mY, R.drawable.lua);
            bullets.add(motviendan);*/
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
