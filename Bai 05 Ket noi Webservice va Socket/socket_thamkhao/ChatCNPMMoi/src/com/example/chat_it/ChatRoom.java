package com.example.chat_it;

import java.util.ArrayList;

import Adapter.ChatAdapter;
import Data.DataMessage;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ChatRoom extends Activity {
	public static final int LOGOUT = 4;	 
	public static final int SEND_MESSAGE_CHAT_ROOM=7;
	public static final int RECEIVE_MESSAGE_CHAT_ROOM=8;
	public static final int ONLINE_USER=9;
	public static final int OFFLINE_USER=10;
	public static final int GET_ONLINE_USER_LIST=11;
	ArrayAdapter<String> adapterFriendList;
	ArrayAdapter<String> adapterChatRoom;
	ListView lstUserList;
	ListView lstChatRoom;
	EditText edtChatRoom;
	Button btnLogout;
	Button btnSend;
	String messagesend;
	public static String username;
	final Handler mHandler=new Handler() {
		@Override
		public void handleMessage(Message msg) {
			 switch (msg.what) {	            	
	            case RECEIVE_MESSAGE_CHAT_ROOM:
	            	String message=(String)msg.obj;
	            	if(message.equals(messagesend)==false)
	            	{
		            	adapterChatRoom.add(message);
		            	adapterChatRoom.notifyDataSetChanged();
	            	}
	            	break;   
	            case ONLINE_USER:
	            	String useronline=(String)msg.obj;	            	
		            adapterFriendList.add(useronline);
		            adapterFriendList.notifyDataSetChanged();
	            	break;
	            case OFFLINE_USER:
	            	String useroffline=(String)msg.obj;
	            	adapterFriendList.remove(useroffline);
	            	adapterFriendList.notifyDataSetChanged();
	            	break;
	            case GET_ONLINE_USER_LIST:
	            	adapterFriendList.clear();
	            	ArrayList<String> userlist=(ArrayList<String>)msg.obj;
	            	for (String struser : userlist) {
						adapterFriendList.add(struser);
					}
	            	adapterFriendList.notifyDataSetChanged();
	            	break;
         }
     }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_room);
	}
	
	  //Ham khi bat dau activity
    public void onStart() {
        super.onStart();
        Log.e("ChatRoom", "+ON START+");
        setupChat();
        
    }
    
    public void onResume() {
        super.onResume();
        Log.e("ChatRoom", "+ON RESUME +");
        Login.mConnected.sendToServer(GET_ONLINE_USER_LIST, null);
        Login.mConnected.mHandler=mHandler;
        
    }
    
    public void onPause() {
        super.onPause();
        Log.e("ChatRoom", "-ON PAUSE -");   
    }   
    public void onStop() {
        super.onStop();
        Log.e("ChatRoom", "--ON STOP --");      
    }   
    public void onDestroy() {
        super.onDestroy();
        Log.e("ChatRoom", "---ON DESTROY ---");       
    }
    /**
     * Ham thiet lap ban dau
     */
    private void setupChat()
    {  
        // Xac dinh theo id cac button,listview,edittext de su ly
        lstChatRoom=(ListView)findViewById(R.id.lstChatRoom);
        lstUserList=(ListView)findViewById(R.id.lstUserList);
        btnSend =(Button)findViewById(R.id.btnSendChatRoom);
        btnLogout=(Button)findViewById(R.id.btnLogout);
        edtChatRoom=(EditText)findViewById(R.id.edtChatRoom);
        //Lay du lieu duoc truyen sang
        username=getIntent().getExtras().getString("UserName");
        adapterChatRoom=new ArrayAdapter<String>(this, R.layout.message_chat_room);
        adapterFriendList=new ArrayAdapter<String>(this, R.layout.item_user);
        lstChatRoom.setAdapter(adapterChatRoom);
        lstUserList.setAdapter(adapterFriendList);
        //Bat su kien click cho button Send
        btnSend.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				messagesend=username+": "+edtChatRoom.getText().toString();
				adapterChatRoom.add(messagesend);
				adapterChatRoom.notifyDataSetChanged();
				edtChatRoom.setText("");
				Login.mConnected.sendToServer(SEND_MESSAGE_CHAT_ROOM, messagesend);
			}
		});
        btnLogout.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Login.mConnected.sendToServer(LOGOUT, null);
				ChatRoom.this.finish();
			}
		});
        lstUserList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> a, View v, int position,long id) {
        		Log.i("Friendlist", "Chon ban de chat");
				String friend =(String) lstUserList.getItemAtPosition(position);
                   if(friend!=null && friend.equals(username)==false){

            		Intent intent = new Intent(getApplicationContext(),ChatUser.class);
            		intent.putExtra("TargetName", friend);
            		startActivity(intent);     
                   }
			}       	
		});
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_chat_room, menu);
		return true;
	}

}
