package com.example.administrator.dangkidangnhapdemo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_email,et_password;
    Button bt_login,bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_email=(EditText)findViewById(R.id.editText);
        et_password=(EditText)findViewById(R.id.editText2);
        bt_login=(Button)findViewById(R.id.button);
        bt_register=(Button)findViewById(R.id.button2);


        bt_register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String email=et_email.getText().toString();
                String pass=et_password.getText().toString();
                new ThucThiLogin(MainActivity.this,email,pass).execute();
            }
        });



    }
}
