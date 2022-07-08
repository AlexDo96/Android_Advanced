package com.example.administrator.androidhttpgetdemo1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 4/15/2017.
 */
public class MyTask extends AsyncTask<Void,Void,Void>{


    Context context;
    TextView tv;
    String diachiget="http://192.168.10.26/webandroid/nhanget.php";
    String dulieu="";

    public MyTask(Context context,String ten,int tuoi,TextView tv)
    {
        this.tv=tv;
        this.context=context;
        try {
            String param1 = URLEncoder.encode(ten, "utf-8");
            String param2=URLEncoder.encode(tuoi+"","utf-8");
            diachiget = diachiget + "?ten=" + param1 + "&tuoi=" + param2;
            Log.d("duongdan",diachiget);
        }catch(Exception e)
        {

        }
    }
    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url=new URL(diachiget);
            HttpURLConnection httpurl=(HttpURLConnection)url.openConnection();

            BufferedReader reader=new BufferedReader(new InputStreamReader(httpurl.getInputStream())); //Gan vao luong Input Stream va gan vao Buffer Reader
            String line;
            StringBuffer sb=new StringBuffer("");
            while((line=reader.readLine())!=null)  //Ktra xem co du lieu khong, khac null tuc la co du lieu
            {
                sb.append(line);    //Doc du lieu vao StringBuffer
            }
            Log.d("dulieu",sb.toString());
            dulieu=sb.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        tv.setText(dulieu);
        Intent i=new Intent(context,HaiActivity.class); // Tao intent chuyen du lieu qua Activity khac
        i.putExtra("dulieu",dulieu);    //gắn cờ "dulieu" va put dữ liệu qua HaiActivity
        context.startActivity(i);
        super.onPostExecute(aVoid);
    }
}
