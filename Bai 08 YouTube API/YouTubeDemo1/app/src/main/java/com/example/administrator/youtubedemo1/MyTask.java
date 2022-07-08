package com.example.administrator.youtubedemo1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 4/29/2017.
 */
public class MyTask extends AsyncTask<Void,Void,Void> {


    ArrayList<HashMap<String,String>> menuitems=new ArrayList<HashMap<String,String>>();
    ListView lv;
    Context context;
    ProgressDialog dialog;
    String keyweb="AIzaSyBmqprXA0h6ckah-w_CuxOTErLtYBtIvv8";
    String keyandroid; //ko can xai nua
    String playlist="PLM5NQydODud6GOUhZoyWkjZ_A8FFHkPN-";

    public MyTask(Context context,ListView lv_video) {
        this.context=context;
        this.lv=lv_video;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=ProgressDialog.show(context,"doi chut","khong thich doi di cho khac choi");
        dialog.setCancelable(true);
    }

    @Override
    protected Void doInBackground(Void... params) {
        URL jsonURL;
        String PageToken="";
        HttpURLConnection jc;
        try {
            String stringurlgoc = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+playlist+ "&maxResults=50&key="+keyweb;

            do {
                String stringurl=stringurlgoc+PageToken;
                jsonURL = new URL(stringurl);
                jc = (HttpURLConnection) jsonURL.openConnection();
                InputStream is = jc.getInputStream();

                // doc du lieu
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "utf-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                String jsonTxt = sb.toString();

    ///////////////// //parse json lay du lieu
                JSONObject jo = new JSONObject(jsonTxt);
                PageToken = "";
                try{
                    PageToken = jo.getString("nextPageToken");
                }catch(Exception e)
                {

                }
                JSONArray items = jo.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    try{
                        JSONObject item = items.getJSONObject(i);
                        JSONObject snippet = item.getJSONObject("snippet");
                        String title = snippet.getString("title");
                        JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                        JSONObject high = thumbnails.getJSONObject("high");
                        String url = high.getString("url");
                        JSONObject resourceId = snippet.getJSONObject("resourceId");
                        String videoId = resourceId.getString("videoId");
                        HashMap<String, String> hashmap = new HashMap<String, String>();
                        hashmap.put("title", title);
                        hashmap.put("url", url);
                        hashmap.put("videoId", videoId);
                        menuitems.add(hashmap);
                        Log.d("dulieu", title);

                    }catch(Exception e)
                    {

                    }

                }
                if(!PageToken.equals(""))
                    PageToken="&pageToken="+PageToken;

            } while (!PageToken.equals(""));


        }catch (Exception e)
        {

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismiss();
        super.onPostExecute(aVoid);
        MyAdapter adapter=new MyAdapter(context,menuitems);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String videoId=menuitems.get(position).get("videoId");
                Intent i=new Intent(context,PlayActivity.class);
                i.putExtra("videoId",videoId);
                context.startActivity(i);
            }
        });
    }
}
