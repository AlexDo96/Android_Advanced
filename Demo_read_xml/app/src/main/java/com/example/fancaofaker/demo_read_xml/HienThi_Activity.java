package com.example.fancaofaker.demo_read_xml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import fpoly.tiepnguyen.demoxml.R;

public class HienThi_Activity extends AppCompatActivity {
    ListView read ;
    public static String url ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_);
        read = (ListView) findViewById(R.id.listread);
        Custom_list adt = new Custom_list(this,R.layout.custom_new,MainActivity.listNews);
        read.setAdapter(adt);

      read.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              url = MainActivity.listNews.get(position).getLink();
              Intent intent11 = new Intent(getApplicationContext(),ChiTietView_Activity.class);
              startActivity(intent11);
          }
      });





    }
}
