package com.example.administrator.dangkidangnhapdemo1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 5/13/2017.
 */
public class xulyxoaproduct extends AsyncTask<Void,Void,Integer> {
    MyFunctions myfunctions;
    Context c;
    String id;
    ArrayList<HashMap<String,String>> ds_sanpham;
    ListView lv;
    xulyxoaproduct(Context c, String id,ArrayList<HashMap<String,String>> ds_sanpham,ListView lv)
    {
        this.c=c;
        this.id=id;
        this.ds_sanpham=ds_sanpham;
        this.lv=lv;
    }
    @Override
    protected Integer doInBackground(Void... params) {
        Integer thanhcong=new Integer(0);
        try
        {
            myfunctions=new MyFunctions(c);
            JSONObject jsonobject=myfunctions.deleteProduct(id);
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
            xulygetallproducts a=new xulygetallproducts(c,lv);
            a.execute();
        }

    }
}
