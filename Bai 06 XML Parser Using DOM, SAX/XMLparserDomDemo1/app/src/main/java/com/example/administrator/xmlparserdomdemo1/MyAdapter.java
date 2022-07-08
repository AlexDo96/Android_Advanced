package com.example.administrator.xmlparserdomdemo1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/22/2017.
 */
public class MyAdapter extends BaseAdapter {
    Context c;
    ArrayList<Item> ds;

    public static class View_Mot_O
    {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
    }

    public MyAdapter(Context c, ArrayList<Item> ds) {
        this.c = c;
        this.ds = ds;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View_Mot_O mot_o;
        LayoutInflater inf = ((Activity)c).getLayoutInflater();
        if(convertView==null)
        {
            mot_o=new View_Mot_O();
            convertView=inf.inflate(R.layout.listview_item,null);
            mot_o.tv1=(TextView)convertView.findViewById(R.id.textView);
            mot_o.tv2=(TextView)convertView.findViewById(R.id.textView2);
            mot_o.tv3=(TextView)convertView.findViewById(R.id.textView3);
            mot_o.tv4=(TextView)convertView.findViewById(R.id.textView4);
            convertView.setTag(mot_o);
        }
        else
        {
            mot_o=(View_Mot_O)convertView.getTag();
        }
        mot_o.tv1.setText(ds.get(position).id);
        mot_o.tv2.setText(ds.get(position).name);
        mot_o.tv3.setText(ds.get(position).cost);
        mot_o.tv4.setText(ds.get(position).description);


        return convertView;
    }
}
