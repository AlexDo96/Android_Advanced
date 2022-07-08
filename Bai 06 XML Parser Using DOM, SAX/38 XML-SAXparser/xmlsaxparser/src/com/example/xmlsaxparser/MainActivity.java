package com.example.xmlsaxparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	List<Item> items=new ArrayList<Item>();
	String path="http://10.0.2.2:8080/testandroid/dulieu.xml";
    InputStream is;

    String chuoi="";
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView1);
        
        MyAsyncTask a=new MyAsyncTask();
        a.execute();
    }
	class MyAsyncTask extends AsyncTask<Void,Void,Void>
	{

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			try {
				URL url=new URL(path);
				URLConnection connection=url.openConnection();
				InputStream is=connection.getInputStream();
				MySaxParser mysaxparser=new MySaxParser();
				items=mysaxparser.xmlParser(is);
				for(int i=0;i<items.size();i++)
				{
					chuoi+=items.get(i).name+" ";
					chuoi+=items.get(i).cost+ "\n";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tv.setText(chuoi);
		}
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
