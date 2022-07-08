package com.example.fancaofaker.demo_read_xml;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


import fpoly.tiepnguyen.demoxml.R;


public class MainActivity extends ActionBarActivity {

    Button btnParseXML;
    public static ListEntry listRssItem;
    public static List<Entry> listNews = new ArrayList<Entry>();
    private String url = "http://tuoitre.vn/rss/tt-the-thao.rss";
    private ProgressDialog progressBar;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParseXML = (Button) findViewById(R.id.btnParseXML);
        btnParseXML.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
    // TODO Auto-generated method stub
                Mystask asyscTask = new Mystask();
                asyscTask.execute();
            }
        });
    }
    private class Mystask extends AsyncTask<Void, Void, ListEntry> {

        @Override
        protected ListEntry doInBackground(Void... params) {
        // TODO Auto-generated method stub
            try {
                listRssItem = new ListEntry();
                ParseXml parse = new ParseXml(url);
                listRssItem = parse.parseXMLRSS();
                listNews = listRssItem.getListEntry();
                Log.d("//=================", "Tong so phan tu RSS" + listRssItem.getListEntry().size());
                for (int i = 0; i < listNews.size(); i++) {
                    Entry en = listNews.get(i);
                    Log.i("//===================", "//========="+en.getTitle());
                }

            } catch (Exception e) {
                listRssItem = null;
                Log.i("Error", "Here");
            }

            Intent intent_ID = new Intent(MainActivity.this, HienThi_Activity.class);
            startActivity(intent_ID);

            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ListEntry result) {
// TODO Auto-generated method stub
            Log.i("", "//=============//onPostExecute");
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
// TODO Auto-generated method stub
            Log.i("", "//=============//onPreExecute");
            progressBar = ProgressDialog.show(MainActivity.this, "",
                    "please wait for checking data...");
            super.onPreExecute();
        }
    }
    public static boolean isConnected(Context context) {
        ConnectivityManager
                cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }
}
