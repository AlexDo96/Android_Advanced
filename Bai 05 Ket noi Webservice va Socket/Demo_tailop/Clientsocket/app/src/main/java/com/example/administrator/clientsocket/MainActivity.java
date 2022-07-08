package com.example.administrator.clientsocket;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText et;
    Button bt1,bt2,bt3;
    TextView tv;
    int loai;
    Socket socket;
    int port=8889;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=(Button)findViewById(R.id.button);
        bt2=(Button)findViewById(R.id.button2);
        bt3=(Button)findViewById(R.id.button3);
        et=(EditText)findViewById(R.id.editText);
        tv=(TextView)findViewById(R.id.textView);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button)
            loai=1;
        else if(v.getId()==R.id.button2)
            loai=2;
        else if(v.getId()==R.id.button3)
            loai=3;

        new MyTask(MainActivity.this,loai).execute();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
