package com.example.administrator.dangkidangnhapdemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginedActivity extends AppCompatActivity {

    Button bt_qlsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logined);
        bt_qlsp=(Button)findViewById(R.id.button6);

        bt_qlsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginedActivity.this, QLSPActivity.class);
                startActivity(i);
            }
        });
    }
}
