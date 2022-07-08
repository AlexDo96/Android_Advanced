package com.example.administrator.xmlparserdomdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView);

        MyTask a=new MyTask(MainActivity.this,lv);
        a.execute();

        //khong nen bat su kien click vao item cua listview o day
        //vi co the ham bat su kien se chay truoc truoc khi MyTask chay
/*        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
    }
}
