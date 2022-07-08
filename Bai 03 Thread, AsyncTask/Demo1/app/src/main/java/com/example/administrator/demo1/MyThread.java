package com.example.administrator.demo1;

import android.util.Log;

/**
 * Created by Administrator on 4/9/2017.
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        for(int i=0;i<=100000;i++) {
            Log.d("chay", "gt: " + i);
        }
        super.run();
    }
}
