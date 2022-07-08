package com.example.administrator.dangkidangnhapdemo1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 5/13/2017.
 */
public class xulygetallproducts extends AsyncTask<Void,Void,Void>
{

    MyFunctions myfunctions;
    ArrayList<HashMap<String,String>> ds_sanpham;
    Context context;
    ListView lv;
    ProgressDialog pg_dialog;

    xulygetallproducts(Context context,ListView lv)
    {
        this.context=context;
        ds_sanpham=new ArrayList<HashMap<String,String>>();
        this.lv=lv;
    }


    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        pg_dialog=new ProgressDialog(context);
        pg_dialog.setMessage("dang nap du lieu");
        pg_dialog.setIndeterminate(false);
        pg_dialog.setCancelable(false);//co the cancel bang phim back
        pg_dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        String thanhcong=null;
        try {
            myfunctions=new MyFunctions(context);
            JSONObject jsonobject=myfunctions.getAllProducts();
            thanhcong=jsonobject.getString("thanhcong");

            //doc tat ca du lieu tu json bo vao ArrayList
            if(Integer.parseInt(thanhcong)==1)//thanh cong
            {
                //truy mang ten sanpham trong json
                JSONArray jsonarray=jsonobject.getJSONArray("sanpham");
                //duyet mang
                for(int i=0;i<jsonarray.length();i++)
                {
                    JSONObject item=jsonarray.getJSONObject(i);
                    String id=item.getString("id");
                    String name=item.getString("name");

                    //tao hasmap va bo vao arraylist
                    HashMap<String,String> hm=new HashMap<String, String>();
                    hm.put("id", id);
                    hm.put("name", name);
                    ds_sanpham.add(hm);
                }
            }
            else //that bai
            {
                /*Intent i=new Intent(context,NewProductActivity.class);
                startActivity(i);
                finish();*/
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        pg_dialog.dismiss();
/*        ListAdapter adapter=new SimpleAdapter(context,
                ds_sanpham,R.layout.list_item,
                new String[]{"id","name"},
                new int[]{R.id.productid,R.id.productname});*/
        AdapterSP adapter=new AdapterSP(context,ds_sanpham,lv);
        lv.setAdapter(adapter);
    }

}
