package com.example.administrator.demo3;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ng.max.slideview.SlideView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        ((SlideView) findViewById(R.id.slideView)).setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideView slideView) {
                // vibrate the device
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);

                // go to a new activity
                //startActivity(new Intent(MainActivity.this, NewActivity.class));

            }
        });*/
    }
}
