package com.example.tuananh.rssdemo;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Tuan Anh on 17/05/2017.
 */

public class TinTheoLoai extends Activity {
    String diachi_rss;
    ArrayList<Item> items = new ArrayList<Item>();
    InputStream is;

    ListView lv_tin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tintheoloai);

        lv_tin = (ListView) findViewById(R.id.listview_tintheoloai);

        diachi_rss = getIntent().getExtras().getString("diachi_rss");
        Toast.makeText(getApplicationContext(), diachi_rss, Toast.LENGTH_SHORT).show();
        MyAsyncTask gandulieu = new MyAsyncTask();
        gandulieu.execute();

    }

    public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL(diachi_rss);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                items = (ArrayList<Item>) MySaxParser.xmlParser(is);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{
                MyAdapter adapter=new MyAdapter(getApplicationContext(),R.layout.layout_tintheoloai_itemlistview,items);
                lv_tin.setAdapter(adapter);
            }catch(Exception e)
            {
                Log.d("title","adapter khong duoc");
            }

        }
    }

    class MyAdapter extends ArrayAdapter<Item> {

        Context context;
        ArrayList<Item> items;

        public MyAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
            this.context = context;
            this.items = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            LayoutInflater inf = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowview = inf.inflate(R.layout.layout_tintheoloai_itemlistview, parent, false);
            TextView tv_title = (TextView) rowview.findViewById(R.id.title);
            TextView tv_description = (TextView) rowview.findViewById(R.id.description);
            TextView tv_pubdate = (TextView) rowview.findViewById(R.id.pubdate);

            tv_title.setText(items.get(position).getTitle().toString());
            tv_description.setText(items.get(position).getDescription().toString());
            tv_pubdate.setText(items.get(position).getPubdate().toString());

            return rowview;

        }

    }
}
