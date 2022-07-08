package com.example.administrator.doctruyen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ChuongActivity extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuong);
        webview=(WebView)findViewById(R.id.webView);


        String displayString = getIntent().getExtras().getString("display");
        if(displayString != null)
        {
            WebSettings settings = webview.getSettings();
            settings.setDefaultTextEncodingName("utf-8");
            settings.setDefaultFontSize(15);
//intfontSize = webview.getSettings().getDefaultFontSize();
            webview.loadDataWithBaseURL(null, displayString, "text/html", "utf-8", null);
            webview.getSettings().setBuiltInZoomControls(true);
            webview.getSettings().setSupportZoom(true);
            webview.setVerticalScrollBarEnabled(true);
        }

    }
}
