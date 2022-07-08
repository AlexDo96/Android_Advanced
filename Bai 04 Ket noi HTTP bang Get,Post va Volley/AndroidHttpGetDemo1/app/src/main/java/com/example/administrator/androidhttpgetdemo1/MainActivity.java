package com.example.administrator.androidhttpgetdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_ten,et_tuoi;
    Button bt;
    TextView tv_kq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_ten=(EditText)findViewById(R.id.editText);
        et_tuoi=(EditText)findViewById(R.id.editText2);
        tv_kq=(TextView)findViewById(R.id.textView3);
        bt=(Button)findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten=et_ten.getText().toString();
                int tuoi=Integer.parseInt(et_tuoi.getText().toString());

                //goi kieu get
/*                MyTask task=new MyTask(MainActivity.this,ten,tuoi,tv_kq);
                task.execute();*/

                //goi kieu post
                MyTaskPost taskpost=new MyTaskPost(MainActivity.this,ten,tuoi,tv_kq);
                taskpost.execute();

            }
        });
    }
}
