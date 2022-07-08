package com.example.administrator.banmaybay1;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Administrator on 5/20/2017.
 */
public class MainThread extends Thread {

    boolean running;
    private SurfaceHolder surfaceholder;
    private GamePanel gamepanel;
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
        super.run();
        long dem=0;
        Canvas canvas=null;
        while(running)
        {
            dem++;
            ////// cap nhat lai trang thai game
            /////  render du lieu ra man hinh
            canvas=surfaceholder.lockCanvas();
            if(canvas!=null)
            {
                gamepanel.onDraw(canvas);
                surfaceholder.unlockCanvasAndPost(canvas);
            }

            Log.d("testloop:", "loop " + dem);
        }

    }
}
