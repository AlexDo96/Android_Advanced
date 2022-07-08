package com.example.administrator.demojsonparser;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/23/2017.
 */
public class MyTask extends AsyncTask<Void,Void,Void> {

    String diachi="http://192.168.56.1/webandroid/contacts";
    JSONObject jsonObject;
    ArrayList<Contact> ds=new ArrayList<Contact>();
    Context c;
    TextView tv;
    public MyTask(Context c, TextView tv) {
        this.c=c;
        this.tv=tv;
    }

    @Override
    protected Void doInBackground(Void... params) {
        JSONParser jsonParser=new JSONParser();
        jsonObject=jsonParser.getJSONFromUri(diachi);
        ds=jsonParser.getArrayListFromJSON(jsonObject);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        for(int i=0;i<ds.size();i++)
        {
            Contact ct=ds.get(i);
            tv.append(ct.id+ " " + ct.name + " "+ ct.mobile + "\n");
        }
        super.onPostExecute(aVoid);

    }
}
