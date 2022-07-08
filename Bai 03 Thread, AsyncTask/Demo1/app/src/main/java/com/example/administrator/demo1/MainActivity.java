package com.example.administrator.demo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv,tv2;
    ProgressBar pb;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        bt=(Button)findViewById(R.id.button);
        tv.setText("");

/*        MyThread thread=new MyThread();
        thread.start();*/

/*        MyRunnable runnable=new MyRunnable(MainActivity.this,tv);
        Thread thread=new Thread(runnable);
        thread.start();*/

/*       MyTask task =new MyTask(tv);
        task.execute();*/

        tv.append(" chay tiep ");


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask2 task2=new MyTask2(tv2,pb);
                task2.execute(100000);

                // Cac kieu thuc thi ham execute voi truong hop đối số co dau "..."
                /*a.execute(100000,543,534534,6756,334,6546);
                a.execute(new Integer[]{543,454,656,344});
                a.execute();*/
            }
        });


    }

    @Override
    protected void onDestroy() {
//        task.cancel(true);
        Log.d("chay","task da dung");
        super.onDestroy();

    }
}
