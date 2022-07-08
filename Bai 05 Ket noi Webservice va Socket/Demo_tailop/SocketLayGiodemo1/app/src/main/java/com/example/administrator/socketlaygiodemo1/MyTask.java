package com.example.administrator.socketlaygiodemo1;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 4/16/2017.
 */
public class MyTask extends AsyncTask<Void,Void,Void> {
    String host="time.nist.gov";
    int port=13;
    String dulieu;
    Context c;
    TextView tv;

    public MyTask(Context c, TextView tv) {
        this.c = c;
        this.tv = tv;
    }


    @Override
    protected Void doInBackground(Void... params) {
        try
        {
            Socket thesocket = new Socket(host, port);
            InputStreamReader isr=new InputStreamReader(thesocket.getInputStream());
            BufferedReader in=new BufferedReader(isr);
            dulieu="dong 1:"+in.readLine();
            dulieu=dulieu+"dong 2:"+in.readLine();
            thesocket.close();
        }
        catch (UnknownHostException ex)
        {
            System.err.println(ex);
        } catch (IOException ex)
        {
            System.err.println(ex);
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        tv.setText(dulieu);
        Toast.makeText(c, "abc", Toast.LENGTH_SHORT).show();
        super.onPostExecute(aVoid);
    }
}
