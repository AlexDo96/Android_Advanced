package com.example.docsach;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class ChuongActivity extends ActionBarActivity {

	WebView webview;
	int fontSize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chuong);
		webview=(WebView)findViewById(R.id.webView1);
		
	    String displayString = getIntent().getExtras().getString("display");
	    if(displayString != null)
	    {
	        WebSettings settings = webview.getSettings();
	        settings.setDefaultTextEncodingName("utf-8");
	        settings.setDefaultFontSize(15);
	       //int fontSize = webview.getSettings().getDefaultFontSize();
	        webview.loadDataWithBaseURL(null, displayString, "text/html", "utf-8", null);
		    webview.getSettings().setBuiltInZoomControls(true);
		    webview.getSettings().setSupportZoom(true);
		    webview.setVerticalScrollBarEnabled(true);
	    }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chuong, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}