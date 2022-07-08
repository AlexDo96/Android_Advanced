package com.example.administrator.youtubedemo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import lazylist.ImageLoader;

/**
 * Created by Administrator on 4/29/2017.
 */
public class MyAdapter extends BaseAdapter{
    Context c;
    ArrayList<HashMap<String,String>> ds=new ArrayList<HashMap<String,String>>();
    public ImageLoader imageLoader;

    public MyAdapter(Context c, ArrayList<HashMap<String, String>> ds) {
        this.c = c;
        this.ds = ds;
        imageLoader=new ImageLoader(c);

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
        LayoutInflater inf=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview=inf.inflate(R.layout.one_item_listview,parent,false);

        TextView textviewtitle=(TextView)rowview.findViewById(R.id.title);
        //TextView textviewlength=(TextView)rowview.findViewById(R.id.length);
        ImageView imageview=(ImageView)rowview.findViewById(R.id.image1);

        textviewtitle.setText(ds.get(position).get("title").toString());
       // textviewlength.setText(menuitems.get(position).get("length").toString());

        imageLoader.DisplayImage(ds.get(position).get("url"), imageview);


        return rowview;
    }
}
