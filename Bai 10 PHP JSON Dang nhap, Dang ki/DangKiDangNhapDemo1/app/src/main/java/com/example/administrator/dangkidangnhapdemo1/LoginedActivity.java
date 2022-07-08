package com.example.administrator.dangkidangnhapdemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginedActivity extends AppCompatActivity {

    TextView tv;
    Button bt_logout;
    MyFunctions myfunctions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logined);

        tv=(TextView)findViewById(R.id.textView4);
        bt_logout=(Button)findViewById(R.id.button5);

        myfunctions=new MyFunctions(getApplicationContext());
        String s=tv.getText().toString();
        tv.setText(s+"\n"+myfunctions.getEmail());

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myfunctions.logOut();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}
