package com.example.administrator.xmlparsersaxdemo2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 4/22/2017.
 */
public class MyTask extends AsyncTask<Void,Void,Void> {

    String duongdan="http://192.168.56.1/webandroid/dulieu.xml";
    Context c;
    TextView tv;
    ArrayList<Item> ds=new ArrayList<Item>();
    public MyTask(Context c, TextView tv) {
        this.c = c;
        this.tv = tv;
    }

    @Override
    protected Void doInBackground(Void... params) {
        URL url= null;
        try {
            url = new URL(duongdan);
            HttpURLConnection httpurl=(HttpURLConnection)url.openConnection();
            InputStream is=httpurl.getInputStream();

            MySaxParser mySaxParser=new MySaxParser();
            ds=mySaxParser.xmlParser(is);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        for(int i=0;i<ds.size();i++)
        {
            tv.append(ds.get(i).id + " " + ds.get(i).name + "\n");
        }
        super.onPostExecute(aVoid);
    }
}
