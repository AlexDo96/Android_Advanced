package com.example.administrator.dangkidangnhapdemo1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by Administrator on 5/7/2017.
 */
public class ThucThiLogin extends AsyncTask<Void,Void,String>
{
    MyFunctions myfunctions;
    String email;
    String password;
    Context c;
    public ThucThiLogin(Context c,String email,String password)
    {
        this.c=c;
        this.email=email;
        this.password=password;
    }
    @Override
    protected String doInBackground(Void... arg0) {
        // TODO Auto-generated method stub

        String thanhcong=null;

        try{
            myfunctions=new MyFunctions(c);
            JSONObject jsonobject=myfunctions.loginUser(email, password);

            thanhcong=jsonobject.getString("thanhcong");
            Log.d("thanhcong",thanhcong + " ");

        }catch(Exception e)
        {
            Log.d("loi", "khong tao json duoc " + e.toString());
        }
        return thanhcong;
    }

    @Override
    protected void onPostExecute(String thanhcong) {
        // TODO Auto-generated method stub
        super.onPostExecute(thanhcong);
        if(Integer.parseInt(thanhcong)==1) //dang nhap thanh cong
        {
            myfunctions.setemaillogin(email);//luu mail lai
            Intent i=new Intent(c,LoginedActivity.class);
            c.startActivity(i);
            ((MainActivity)c).finish();
        }
        else //dang nhap that bai
        {
            Toast.makeText(c,
                    "khong dang nhap duoc email hoac pass sai",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
