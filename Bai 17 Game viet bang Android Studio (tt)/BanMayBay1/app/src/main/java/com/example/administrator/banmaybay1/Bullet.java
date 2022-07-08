package com.example.administrator.banmaybay1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Administrator on 5/21/2017.
 */
public class Bullet {
    int x;
    int y;
    Bitmap bitmap;
    int tocdo=20;
    int huong=0;
    public Bullet(Resources res,int x,int y)
    {
        this.x=x;
        this.y=y;
        bitmap= BitmapFactory.decodeResource(res, R.drawable.lua);
    }

    public Bullet(Resources res,int x,int y, int hinh)
    {
        this.x=x;
        this.y=y;
        bitmap=BitmapFactory.decodeResource(res, hinh);
    }
    public Bullet(Resources res,int x,int y, int hinh,int huong)
    {
        this.x=x;
        this.y=y;
        this.huong=huong;
        bitmap=BitmapFactory.decodeResource(res, hinh);
    }
    public void doDraw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, x,y, null);
        x+=tocdo;
        y+=huong;
    }

    public void setXY(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public void setTocDo(int x)
    {
        this.tocdo=x;
    }


    public int getWidth()
    {
        return bitmap.getWidth();
    }
    public int getHeight()
    {
        return bitmap.getHeight();
    }
    public int gettamX()
    {
        //tam x=toa do x congvoinuarong
        return x+(bitmap.getWidth()/2);
    }
    public int gettamY()
    {
        //tam y=toa do y congnuacao
        return y+(bitmap.getHeight()/2);
    }

}
