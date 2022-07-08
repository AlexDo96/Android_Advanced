package com.example.fancaofaker.demo_read_xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import fpoly.tiepnguyen.demoxml.R;

public class ChiTietView_Activity extends AppCompatActivity {
    WebView wv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_view_);
        wv = (WebView) findViewById(R.id.webview);
        wv.loadUrl(HienThi_Activity.url);

    }
}
