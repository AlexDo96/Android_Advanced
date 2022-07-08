package com.example.administrator.androidhttpgetdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HaiActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hai);
        tv=(TextView)findViewById(R.id.textView4);

        String chuoi=getIntent().getExtras().getString("dulieu"); //Lay du lieu thong qua cờ "dulieu"
        tv.setText(chuoi);
    }
}
