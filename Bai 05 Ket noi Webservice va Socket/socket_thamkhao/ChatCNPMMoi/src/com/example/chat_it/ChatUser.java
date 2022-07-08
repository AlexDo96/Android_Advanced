package com.example.chat_it;

import java.util.ArrayList;

import Adapter.ChatAdapter;
import Data.DataMessage;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ChatUser extends Activity {

	//Adapter chuyen doi noi dung vao Listview
	ChatAdapter adapter;
	//Danh sach cac Message gui va nhan
	ArrayList<DataMessage> arrayMessage;
	//Hien thi noi dung chao mung
	TextView textview1;
	//Listview hien thi noi dung chat gui va nhan
	ListView lstNoiDung;
	//EditText hien thi noi dung chat muon gui
	EditText etxtChat;
	//Button gui noi dung chat
	Button btnSend;
	String strTargetName;
	EditText etxtTargetName;
	//Khai bao Lop chuc nang
		//ChatService mChatService;
	public static final int SEND_MESSAGE = 5;
	public static final int RECIEVE_MESSAGE=6;
   // public static final int MESSAGE_TOAST 	= 3;
	private final Handler mHandler=new Handler(){
		 public void handleMessage(Message msg) {	        	
	            switch (msg.what) {	            	
	            case SEND_MESSAGE:
	            	break;
		            case RECIEVE_MESSAGE:
		            	Log.d("ChatIT", "Message Recieve");
		            	//Nhan du lieu
		                DataMessage mReceiveMessage= (DataMessage)msg.obj;
		                //Them tin nhan nhan vao danh sach tin nhan gui va nhan
		                if(mReceiveMessage.userSend.equals(strTargetName))
		                {
			                arrayMessage.add(mReceiveMessage);
			                //Thuc thi thay doi
			                adapter.notifyDataSetChanged();	
		                }
		                break;              
	            }
	        }
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_user);
       
    }
    //Ham khi bat dau activity
    public void onStart() {
        super.onStart();
        Log.e("ChatIT", "+Chat Activity ON START +");
        setupChat();
    }
    public void onResume() {
        super.onResume();
        Log.e("ChatIT", "+Chat Activity ON RESUME +");
       // DangNhap.mChatService.start();
    }
    
    public void onPause() {
        super.onPause();
        Log.e("ChatIT", "-Chat Activity ON PAUSE -");
       // if (DangNhap.mChatService != null) DangNhap.mChatService.stop();
    }
    
    public void onStop() {
        super.onStop();
        Log.e("ChatIT", "--Chat Activity ON STOP --");
       // if (DangNhap.mChatService != null) DangNhap.mChatService.stop();
    }
    
    public void onDestroy() {
        super.onDestroy();
        Log.e("ChatIT", "---Chat Activity ON DESTROY ---");
        // Stop the Broadcast chat services
      //  if (DangNhap.mChatService != null) DangNhap.mChatService.stop();
        
    }
    /**
     * Ham thiet lap ban dau
     */
    private void setupChat()
    {
    	//Khoi tao chatservice
    	Login.mConnected.mHandler=mHandler;
        Log.e("ChatIT", "Bat Dau");     
        // Xac dinh theo id cac button,listview,edittext de su ly
        textview1=(TextView)findViewById(R.id.txtHello);
        lstNoiDung=(ListView)findViewById(R.id.listView1);
        btnSend=(Button)findViewById(R.id.btnSend);
        etxtChat=(EditText)findViewById(R.id.etxtChat);
        //Lay du lieu duoc truyen sang
        strTargetName=getIntent().getExtras().getString("TargetName");
        textview1.setText("Send to "+ strTargetName);
        //Bat su kien click cho button Send
        btnSend.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//Goi ham gui message		
				sendMessage(ChatRoom.username,strTargetName, etxtChat.getText().toString());			
				//set lai etxtChat bang rong
				etxtChat.setText("");
			}
		});
        //Khoi tao danh sach tin nhan
        arrayMessage=new ArrayList<DataMessage>();
        //Khoi tao adapter 
        adapter=new ChatAdapter(this, R.layout.message, arrayMessage);
        //Set adapter cho LístView
        lstNoiDung.setAdapter(adapter);   
        
    	
    }
/**
 * Ham gui tin nhan 
 * @param targetname ten nguoi nhan
 * @param message tin nhan
 */
    public void sendMessage(String username,String targetname,String message)
    { 
    	//Khai bao va khoi tao DataMessage duoc gui
    	DataMessage mSendMessage =new DataMessage(username, targetname, message);
    	//Them DataMessage vao danh sach gui va nhan
    	arrayMessage.add(mSendMessage);
    	//Thuc thi cac thay doi
    	adapter.notifyDataSetChanged();
    	Log.e("Function", "Send Message");
    	//Gui yeu cau va du lieu len server
    	Login.mConnected.sendToServer(SEND_MESSAGE, mSendMessage);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    

    
}
