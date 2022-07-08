package com.example.administrator.clientsocket;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Administrator on 4/16/2017.
 */
public class MyTask extends AsyncTask<Void,Void,Void> {


    //chu y: nho dung lenh ipconfig /all de lay ipserver
    //hoac khi chay console xuat ra ip,lay ip dua vao day
    String host="192.168.56.1";//"192.168.10.26";//"10.0.2.2";
    String dulieu;

    int loai;
    Context c;

    public MyTask(Context c, int loai) {
        this.c = c;
        this.loai = loai;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if(loai==1) //nut ket noi duoc nhan
            connect();
        else if(loai==2) //nut ngat ket noi duoc nhan
        {
            try {
                ((MainActivity)c).socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(loai==3) //nut gui text duoc nhan
        {
            guitext();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(loai==1)
            ((MainActivity)c).tv.setText(dulieu);
        else if(loai==2)
        {
            ((MainActivity)c).tv.setText("ngat ket noi");
        }

    }

    public void connect()
    {
        try {
            ((MainActivity)c).socket=new Socket(host,((MainActivity)c).port);//thang server

            if(((MainActivity)c).socket.isConnected()==true)//ket noi thanh cong
            {
                //doc tren server xuong
                BufferedReader reader=new BufferedReader(
                        new InputStreamReader(((MainActivity)c).socket.getInputStream()));
                String textline=reader.readLine();
                dulieu=textline+"\n";
            }
            else
            {
                dulieu="ket noi that bai\n";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } ;

    }

    public void guitext()
    {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(((MainActivity)c).socket.getOutputStream()));
            String input = "client:"+((MainActivity)c).et.getText().toString();
            writer.write(input + "\n", 0, input.length() + 1);
            writer.flush();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
