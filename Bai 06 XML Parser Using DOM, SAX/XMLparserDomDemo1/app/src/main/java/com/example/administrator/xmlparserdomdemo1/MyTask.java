package com.example.administrator.xmlparserdomdemo1;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/22/2017.
 */
public class MyTask extends AsyncTask<Void,Void,Void> {

    String duongdan="http://192.168.56.1/webandroid/dulieu.xml";
    String K_ITEM="item";
    String K_ID="id";
    String K_NAME="name";
    String K_COST="cost";
    String K_DESCRIPTION="description";
    String dulieu="";
    ArrayList<Item> ds=new ArrayList<Item>();
    Context c;
    ListView lv;
    MyTask (Context c,ListView lv)
    {
        this.c=c;
        this.lv=lv;
    }
    @Override
    protected Void doInBackground(Void... params) {
        XMLParser xmlParser=new XMLParser();
        String xml=xmlParser.getXmlFromUrl(duongdan);
        Document document=xmlParser.getDomElement(xml);
        NodeList nl=document.getElementsByTagName(K_ITEM);
        for(int i=0;i<nl.getLength();i++)
        {
            Element e=(Element)nl.item(i);
            String id=xmlParser.getValue(e,K_ID);
            String name=xmlParser.getValue(e,K_NAME);
            String cost=xmlParser.getValue(e,K_COST);
            String description=xmlParser.getValue(e,K_DESCRIPTION);
            Item item=new Item(id,name,cost,description);
            ds.add(item);
            //Log.d("dulieu",name);
        }

        //Log.d("dulieu",xml);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        MyAdapter adapter=new MyAdapter(c,ds );
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(c, ds.get(position).id+ "", Toast.LENGTH_SHORT).show();
            }
        });
        super.onPostExecute(aVoid);
    }
}
