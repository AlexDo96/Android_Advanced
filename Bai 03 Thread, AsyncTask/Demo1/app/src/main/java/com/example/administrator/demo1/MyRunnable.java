package com.example.administrator.demo1;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 4/9/2017.
 */
public class MyRunnable extends View implements Runnable{
    TextView tv;

    public MyRunnable(Context context,TextView tv) {
        super(context);
        this.tv=tv;
    }

    @Override
    public void run() {
        for(int i=0;i<=100000;i++) {
            Log.d("chay", "gt: " + i);

            if(i==100000)
            {
                tv.post(new Runnable()
                {
                    @Override
                    public void run() {
                        tv.append("chay xong");
                    }
                });
            }
        }
    }
}
