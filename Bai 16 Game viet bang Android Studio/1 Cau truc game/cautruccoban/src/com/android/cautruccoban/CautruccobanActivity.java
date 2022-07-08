package com.android.cautruccoban;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class CautruccobanActivity extends Activity {
    /** Called when the activity is first created. */

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //cua so khong co thanh title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //full man hinh
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        //tao doi tuong
        GamePanel d=new GamePanel(this);
        setContentView(d);
        //setContentView(R.layout.main);
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		
		super.onDestroy();
		Log.d("looptest", "huy thread");
		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	
		
	}
}