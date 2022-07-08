package com.example.tuananh.rssdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv_rss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_rss=(ListView)findViewById(R.id.listview);

        String [] ten_loai={"Giáo dục","Thể thao","Sống khỏe","Nhịp sống trẻ"};

        final String [] rss_loai={"http://tuoitre.vn/rss/tt-giao-duc.rss",
                "http://tuoitre.vn/rss/tt-the-thao.rss",
                "http://tuoitre.vn/rss/tt-song-khoe.rss",
                "http://tuoitre.vn/rss/tt-nhip-song-tre.rss"
        };

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ten_loai);
        lv_rss.setAdapter(adapter);

      lv_rss.setOnClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                  long arg3
          ) {
              Intent intent=new Intent(getApplicationContext(),TinTheoLoai.class);
              intent.putExtra("diachi_rss", rss_loai[arg2]);
              startActivity(intent);

          }
      });



    }


}
