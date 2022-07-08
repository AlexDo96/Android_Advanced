package com.example.administrator.dangkidangnhapdemo1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Administrator on 5/13/2017.
 */
public class xulycreateproduct extends AsyncTask<Void,Void,Integer>{
    MyFunctions myfunctions;
    Context c;
    String name;
    String price;
    String description;
    xulycreateproduct(Context c, String name, String price,String description)
    {
        this.c=c;
        this.name=name;
        this.price=price;
        this.description=description;
    }
    @Override
    protected Integer doInBackground(Void... params) {
        Integer thanhcong=new Integer(0);
        try
        {
            myfunctions=new MyFunctions(c);
            JSONObject jsonobject=myfunctions.createProduct(name, price, description);
            thanhcong=jsonobject.getInt("thanhcong");

        }catch(Exception e)
        {
            Log.d("loi", "khong tao san pham moi duoc" + e.toString());
        }

        return thanhcong;


    }

    @Override
    protected void onPostExecute(Integer thanhcong) {
        super.onPostExecute(thanhcong);
        if(thanhcong==1)
        {
            Intent i=new Intent();
            ((Activity)c).setResult(Activity.RESULT_OK);
            ((Activity)c).finish();
        }

    }
}
