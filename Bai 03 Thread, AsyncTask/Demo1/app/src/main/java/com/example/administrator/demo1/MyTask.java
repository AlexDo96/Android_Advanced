package com.example.administrator.demo1;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Administrator on 4/9/2017.
 */
public class MyTask extends AsyncTask<Void,Void,Void> {

    TextView tv;
    public MyTask(TextView tv)
    {
        this.tv=tv;
    }

    // qua trinh truoc khi chay
    @Override
    protected void onPreExecute() {
        tv.setText("truoc khi chay");
        super.onPreExecute();
    }

    //nhung cong viec chay ngam
    @Override
    protected Void doInBackground(Void... params) {
        for(int i=0;i<=100000;i++) {
            Log.d("chay", "gt: " + i);
            if(this.isCancelled()==true)
                break;
            if(i==50000)
                publishProgress();//goi cap nhat giao dien, goi den ham onProgressUpdate
        }
        return null;
    }

    //trong qua trinh dang chay can cap nhat giao dien
    @Override
    protected void onProgressUpdate(Void... values) {
        tv.setText("chay duoc phan nua");
        super.onProgressUpdate(values);
    }

    //nhung cong viec sau khi chay xong, cap nhat giao dien
    @Override
    protected void onPostExecute(Void aVoid) {
        tv.setText("chay xong thread");
        super.onPostExecute(aVoid);
    }
}
