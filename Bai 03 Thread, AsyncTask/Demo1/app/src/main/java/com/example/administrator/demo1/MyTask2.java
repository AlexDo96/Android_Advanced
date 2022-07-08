package com.example.administrator.demo1;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Administrator on 4/9/2017.
 */
public class MyTask2 extends AsyncTask<Integer,Integer,String>{

    TextView tv2;
    ProgressBar pb;

    public MyTask2(TextView tv2,ProgressBar pb)
    {
        this.tv2=tv2;
        this.pb=pb;
    }

    @Override
    protected String doInBackground(Integer... params) {
        for(int i=0;i<params[0];i++)
        {
            Log.d("chay", " " + i);
            if(i%1000==0)
                publishProgress(i/1000);
        }
        return "da chay xong";
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        pb.setProgress(values[0]);
        tv2.setText(""+values[0] + "%");

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        Log.d("chay",aVoid);
        tv2.setText(aVoid);

        super.onPostExecute(aVoid);
    }
}
