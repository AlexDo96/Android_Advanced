package com.example.administrator.banmaybay1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //cua so khong co thanh title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //tao doi tuong
        GamePanel d=new GamePanel(this);
        setContentView(d);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("looptest", "huy thread");
    }
}
