package com.example.administrator.webservicedemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button bt;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText)findViewById(R.id.editText);
        bt=(Button)findViewById(R.id.button);
        tv=(TextView)findViewById(R.id.textView);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenthanhpho=et.getText().toString();

                MyTask task=new MyTask(MainActivity.this,tenthanhpho,tv);
                task.execute();
            }
        });
    }
}
