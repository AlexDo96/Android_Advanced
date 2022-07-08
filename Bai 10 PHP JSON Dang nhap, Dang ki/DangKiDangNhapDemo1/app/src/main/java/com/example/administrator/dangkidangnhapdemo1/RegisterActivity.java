package com.example.administrator.dangkidangnhapdemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    EditText et_name,et_password,et_email;
    TextView tv_thongbao;
    Button bt_register, bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name=(EditText)findViewById(R.id.editText3);
        et_password=(EditText)findViewById(R.id.editText4);
        et_email=(EditText)findViewById(R.id.editText5);
        tv_thongbao=(TextView)findViewById(R.id.textView3);
        bt_register=(Button)findViewById(R.id.button3);
        bt_login=(Button)findViewById(R.id.button4);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et_name.getText().toString();
                String email=et_email.getText().toString();
                String password=et_password.getText().toString();
                new ThucThiRegister(RegisterActivity.this,name,email,password).execute();
            }
        });


    }
}
