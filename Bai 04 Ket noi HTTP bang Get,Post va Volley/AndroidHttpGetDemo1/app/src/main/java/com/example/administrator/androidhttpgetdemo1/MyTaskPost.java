package com.example.administrator.androidhttpgetdemo1;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 4/15/2017.
 */
public class MyTaskPost extends AsyncTask<Void,Void,Void>{
    Context context;
    TextView tv;
    String diachipost="http://192.168.10.26/webandroid/nhanpost.php";
    String dulieu="";
    String ten;
    int tuoi;

    MyTaskPost(Context context,String ten,int tuoi,TextView tv)
    {
        this.tv=tv;
        this.context=context;
        this.ten=ten;
        this.tuoi=tuoi;
    }
    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL(diachipost);
            String param="ten=" + URLEncoder.encode(ten, "UTF-8")+
                    "&tuoi="+URLEncoder.encode(tuoi+"","UTF-8");
            HttpURLConnection httpurl = (HttpURLConnection)url.openConnection();
            httpurl.setDoOutput(true);                 //Cho phep xuat du lieu
            httpurl.setRequestMethod("POST");          //Phuong thuc ket noi Post
            httpurl.setFixedLengthStreamingMode(param.getBytes().length);
            httpurl.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            PrintWriter out = new PrintWriter(httpurl.getOutputStream());
            out.print(param);
            out.close();


            BufferedReader reader=new BufferedReader(new InputStreamReader(httpurl.getInputStream()));
            String line;
            StringBuffer sb=new StringBuffer("");
            while((line=reader.readLine())!=null)
            {
                sb.append(line);
            }
            Log.d("dulieu", sb.toString());
            dulieu=sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        tv.setText(dulieu);
        super.onPostExecute(aVoid);
    }
}
