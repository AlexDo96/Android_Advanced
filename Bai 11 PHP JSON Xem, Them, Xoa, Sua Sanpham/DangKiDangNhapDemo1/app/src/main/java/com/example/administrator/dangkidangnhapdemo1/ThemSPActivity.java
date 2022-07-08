package com.example.administrator.dangkidangnhapdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThemSPActivity extends AppCompatActivity {

    EditText et_name,et_price,et_description;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sp);
        et_name=(EditText)findViewById(R.id.editText6);
        et_price=(EditText)findViewById(R.id.editText7);
        et_description=(EditText)findViewById(R.id.editText8);

        bt=(Button)findViewById(R.id.button8);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulycreateproduct a=new xulycreateproduct(ThemSPActivity.this,
                        et_name.getText().toString(),
                        et_price.getText().toString(),
                        et_description.getText().toString());
                a.execute();
            }
        });
    }
}
