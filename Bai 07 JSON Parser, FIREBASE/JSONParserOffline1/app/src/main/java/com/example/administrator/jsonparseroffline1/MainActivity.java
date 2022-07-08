package com.example.administrator.jsonparseroffline1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> ds=new ArrayList<Contact>();
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);

        try {
            InputStream is=getAssets().open("contacts");
            JSONParser jsonParser=new JSONParser();
            JSONObject jsonObject=jsonParser.getJSONFromUri(is);
            ds=jsonParser.getArrayListFromJSON(jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<ds.size();i++)
        {
            Contact ct=ds.get(i);
            tv.append(ct.id+ " " + ct.name + " "+ ct.mobile + "\n");
        }
    }
}
