package com.example.administrator.dangkidangnhapdemo1;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Tuan Anh on 12/05/2017.
 */

public class ThucThiRegister  extends AsyncTask <Void,Void,String>{
    String name;
    String email;
    String password;
    MyFunctions myfunctions;
    Context c;

    public ThucThiRegister(Context c,String name, String email,String password)
    {
        this.c=c;
        this.name=name;
        this.email=email;
        this.password=password;
    }

    @Override
    protected String doInBackground(Void... params) {
        String thanhcong=null;
        try
        {
            myfunctions=new MyFunctions(c);
            JSONObject jsonobject=myfunctions.registerUser(name, email, password);
            thanhcong=jsonobject.getString("thanhcong");

        }catch(Exception e)
        {
            Log.d("loi", "khong tao json duoc, khong dang ki duoc " + e.toString());
        }
        return thanhcong;

    }

    @Override
    protected void onPostExecute(String thanhcong) {
        super.onPostExecute(thanhcong);

        if(Integer.parseInt(thanhcong)==1)//dang ki thanh cong
        {
            ((RegisterActivity)c).tv_thongbao.setText("dang ki thanh cong \n email:\n" +
                    email+ "\n nhan nut login ben duoi");
        }
        else
        {
            ((RegisterActivity)c).tv_thongbao.setText("dang ki khong thanh cong hoac email da ton tai");
        }

    }
}
