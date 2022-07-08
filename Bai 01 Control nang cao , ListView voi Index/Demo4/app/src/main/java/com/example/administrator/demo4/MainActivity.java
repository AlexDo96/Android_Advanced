package com.example.administrator.demo4;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button)findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.root), "chac chua", 5000)
                        .setActionTextColor(Color.RED)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//xulygi do o day
                            }
                        })
                        .show();
            }


        });
    }
}
