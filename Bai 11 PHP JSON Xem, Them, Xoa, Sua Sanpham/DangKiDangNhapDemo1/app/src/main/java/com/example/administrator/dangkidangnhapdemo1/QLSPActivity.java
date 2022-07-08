package com.example.administrator.dangkidangnhapdemo1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class QLSPActivity extends AppCompatActivity {

    ArrayList<HashMap<String,String>> ds_sanpham;
    Button themsp;
    ListView lv_sanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsp);
        lv_sanpham=(ListView)findViewById(R.id.listView1);
        themsp=(Button)findViewById(R.id.button7);

        xulygetallproducts a=new xulygetallproducts(QLSPActivity.this,lv_sanpham);
        a.execute();

        themsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(QLSPActivity.this,ThemSPActivity.class);
                startActivityForResult(i,999);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999 && resultCode==RESULT_OK)
        {
            xulygetallproducts a=new xulygetallproducts(QLSPActivity.this,lv_sanpham);
            a.execute();
        }
    }
}
