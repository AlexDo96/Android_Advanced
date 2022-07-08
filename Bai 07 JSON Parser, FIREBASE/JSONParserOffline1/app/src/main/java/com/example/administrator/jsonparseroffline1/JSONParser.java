package com.example.administrator.jsonparseroffline1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 4/23/2017.
 */
public class JSONParser {

    InputStream in=null;
    JSONObject jobj=null;
    String json;

    public JSONObject getJSONFromUri(InputStream is)
    {
        try {
            BufferedReader reader=new BufferedReader((new InputStreamReader(is)));
            StringBuilder sb=new StringBuilder();
            String line="";
            while((line=reader.readLine())!=null)
            {
                sb.append(line);
            }
            json=sb.toString();
            Log.d("dulieu",json);
            jobj=new JSONObject(json);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return jobj;
    }

    public JSONObject getJSONFromUri(String diachiurl)
    {
        try {
            URL url=new URL(diachiurl);
            HttpURLConnection httpurl=(HttpURLConnection)url.openConnection();
            BufferedReader reader=new BufferedReader((new InputStreamReader(httpurl.getInputStream())));
            StringBuilder sb=new StringBuilder();
            String line="";
            while((line=reader.readLine())!=null)
            {
                sb.append(line);
            }
            json=sb.toString();
            Log.d("dulieu",json);
            jobj=new JSONObject(json);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return jobj;
    }

    public ArrayList<Contact> getArrayListFromJSON(JSONObject jobj)
    {
        ArrayList<Contact> ds=new ArrayList<Contact>();
        try {
            JSONArray arraycontact = jobj.getJSONArray("contacts");
            for(int i=0;i<arraycontact.length();i++)
            {
                Contact c=new Contact();
                JSONObject mot_contact=arraycontact.getJSONObject(i);
                c.id=mot_contact.getString("id");
                c.name=mot_contact.getString("name");
                c.email=mot_contact.getString("email");
                c.address=mot_contact.getString("address");
                c.gender=mot_contact.getString("gender");

                JSONObject phoneobj=mot_contact.getJSONObject("phone");
                c.mobile=phoneobj.getString("mobile");
                c.home=phoneobj.getString("home");
                c.office=phoneobj.getString("office");

                ds.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return ds;
    }
}
