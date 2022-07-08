package com.example.administrator.dangkidangnhapdemo1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 5/13/2017.
 */
public class AdapterSP extends BaseAdapter
{
    Context context;
    ArrayList<HashMap<String,String>> ds_sanpham;
    ListView lv;
    public AdapterSP(Context c,ArrayList<HashMap<String,String>> ds_sanpham,ListView lv)
    {
        context=c;
        this.ds_sanpham=ds_sanpham;
        this.lv=lv;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ds_sanpham.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    class View_Mot_O
    {
        TextView id, name;
        ImageView thungrac;
    }
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        View_Mot_O mot_o;
        LayoutInflater layoutinflater= ((Activity)context).getLayoutInflater();

        if(arg1==null)
        {
            mot_o = new View_Mot_O();
            arg1 = layoutinflater.inflate(R.layout.list_item, null);
            mot_o.id = (TextView) arg1.findViewById(R.id.productid);
            mot_o.name = (TextView)arg1.findViewById(R.id.productname);
            mot_o.thungrac = (ImageView)arg1.findViewById(R.id.imageView);
            arg1.setTag(mot_o);
        }
        else
            mot_o=(View_Mot_O)arg1.getTag();




        mot_o.id.setText(ds_sanpham.get(arg0).get("id"));
        mot_o.name.setText(ds_sanpham.get(arg0).get("name"));
        mot_o.thungrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, arg0+" "+ds_sanpham.get(arg0).get("id"), Toast.LENGTH_SHORT).show();
                String iddatabase=ds_sanpham.get(arg0).get("id");
                xulyxoaproduct a=new xulyxoaproduct(context,iddatabase,ds_sanpham,lv);
                a.execute();

            }
        });


        return arg1;
    }

}

