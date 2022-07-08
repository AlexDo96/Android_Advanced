package com.example.chat_it;

import Data.DataLogin;
import Service.ConnectedThread;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

	public static final int LOGIN=1;
	public static final int LOGIN_SUCCESS=2;
	public static final int LOGIN_FAIL=3;
	public static final int LOGOUT=4;
	
	
	//Khoi tao control EditText Username
	EditText etxtUserName;
	//Khoi tao control EditText PassWord
	EditText etxtPassWord;
	//Khoi tao control Button Login
	Button btnLogin;
	//Khoi tao control Button Cancel
	Button btnCancel;
	//Khai bao Lop chuc nang
	public static ConnectedThread mConnected;
	//Khai bao Handler lay du lieu tra ve tu ChatService
	private final Handler mHandler=new Handler(){
		
		public void handleMessage(Message msg)
		{
			
            switch (msg.what) {	           
	            case LOGIN_SUCCESS:
	            		//Mo mot activity moi ten la Main.class
	 					Intent intent=new Intent(getApplicationContext(), ChatRoom.class);
	 					//Truyen du lieu cho activity duoc mo 
	 					//Username la ten
	 					//edxUserName.getText().toString() la noi dung
	 					intent.putExtra("UserName",etxtUserName.getText().toString());		 					
	 					//Chuyen qua activity 
	 					startActivity(intent);  			            		            	
	            	break;
	            case LOGIN_FAIL:
	            	AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
 					builder1.setMessage("Nhap lai username hay password");
 					builder1.show();		
 					break;  	        
            }
			
		}
		
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
      
    }
    
    /**
     * Ham thiet lap cac Layout va su kien
     */
    private void setup()
    {
    	//Khoi tao chatservice
    	mConnected=new ConnectedThread(this, mHandler);
    	// Xac dinh theo id cac button,edittext de su ly
    	 etxtUserName=(EditText)findViewById(R.id.editUserName);
         etxtPassWord=(EditText)findViewById(R.id.editPassWord);
         btnCancel=(Button)findViewById(R.id.btnCancel);
         btnLogin=(Button)findViewById(R.id.btnDangNhap);
         //Khai bao su kien click tren Button Login
         btnLogin.setOnClickListener(new OnClickListener() {
 			
 			public void onClick(View v) {
 				//Lay username
 				
 				String strUsername=etxtUserName.getText().toString();
 				//Lay password
 				String strPassword=etxtPassWord.getText().toString();
 				if(strUsername.length()>0 && strPassword.length()>0)
 				{
 					Log.e("ChatIT", "Login");
	 				DataLogin mLogin=new DataLogin(strUsername,strPassword);
	 				mConnected.sendToServer(LOGIN,mLogin);
 				}
 							
 			}
 		});
         //Khai bao su kien click len Button Cancel
         btnCancel.setOnClickListener(new OnClickListener() {
 			
 			public void onClick(View v) {
 				//Dong ung dung
 				mConnected.sendToServer(LOGOUT,null);
 				mConnected.cancel();
 				Login.this.finish();
 			}
 		});
    }  
    public void onStart()
    {
    	super.onStart();
    	Log.e("ChatIT", "+Login Activity ON START +");
    	//Goi ham thiet lap
    	setup();
    	mConnected.start();
    }
    
    public  void onResume() {
        super.onResume();
        etxtPassWord.setText("");
        etxtUserName.setText("");
        mConnected.mHandler=mHandler;
        Log.e("ChatIT", "+Login Activity ON RESUME +");
    }
    
    public  void onPause() {
        super.onPause();
        Log.e("ChatIT", "-Login Activity ON PAUSE -");
        
    }
    
    public void onStop() {
        super.onStop();
        Log.e("ChatIT", "--Login Activity ON STOP --");
       // if (mChatService != null) mChatService.stop();
    }
    
    public void onDestroy() {
        super.onDestroy();
        Log.e("ChatIT", "---Login Activity ON DESTROY ---");
        // Stop the Broadcast chat services
       // if (mChatService != null) mChatService.stop();
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
