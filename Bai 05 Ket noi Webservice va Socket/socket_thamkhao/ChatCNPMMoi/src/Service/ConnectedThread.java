package Service;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import android.content.Context;
import android.os.Handler;
import android.util.Log;


/**
 * Class cung cap cac chuc nang
 */
	public class ConnectedThread extends Thread implements Function
	{		
		//Khai bao mot Handler lay du lieu
		public  Handler mHandler;
		private ConnectedThread mConnectedThread;
		Context mContext ;
		//Khai bao socket
		Socket socket;
		//Khai bao luong ghi du lieu gui sang server
		ObjectOutputStream ops;
		//Khai bao luong nhan du lieu tu server
		ObjectInputStream ips;
		
		//Khai bao co lam viec
		Boolean isworking=true;
		
		public ConnectedThread(Context context, Handler handler)
		{
		
			try {
				mContext = context;
		        mHandler = handler;
				//Khoi tao ket noi voi ip cua server va cong
				//Ip mac dinh cho android la 10.0.2.2
				//mHandler=handler;
				//socket=new Socket("10.0.2.2",2048);
				socket=new Socket("10.0.2.2",2048);
				//Khoi tao luong ghi du lieu
				ops=new ObjectOutputStream(socket.getOutputStream());
				//Khoi tao luong nhan du lieu
				ips= new ObjectInputStream(socket.getInputStream());
			
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void run()
		{
			while(isworking)
			{
				
				try {
			
					int function=ips.readInt();
					Object object=readFromServer();
					switch (function) {					
					case LOGIN_SUCCESS:							
						Log.i("Function", "Login Success");
						mHandler.obtainMessage(LOGIN_SUCCESS).sendToTarget();
						break;
					case LOGIN_FAIL:						
						Log.i("Function", "Login Fail");
						mHandler.obtainMessage(LOGIN_FAIL).sendToTarget();
						break;
					case RECIEVE_MESSAGE:
						Log.i("Function", "Receive message");						
						mHandler.obtainMessage(RECIEVE_MESSAGE,object).sendToTarget();
						break;		
					case RECEIVE_MESSAGE_CHAT_ROOM:
						Log.i("Function", "RECEIVE MESSAGE CHAT ROOM");						
						mHandler.obtainMessage(RECEIVE_MESSAGE_CHAT_ROOM,object).sendToTarget();
						break;
					case ONLINE_USER:
						Log.i("Function", "ONLINE USER");						
						mHandler.obtainMessage(ONLINE_USER,object).sendToTarget();
						break;
					case OFFLINE_USER:
						Log.i("Function", "OFFLINE USER");						
						mHandler.obtainMessage(OFFLINE_USER,object).sendToTarget();
						break;
					case GET_ONLINE_USER_LIST:
						Log.i("Function", "GET ONLINE USER LIST");						
						mHandler.obtainMessage(GET_ONLINE_USER_LIST,object).sendToTarget();
						break;
					}
				} catch (OptionalDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}						
			}
				
		}
		
		private Object readFromServer()
		{			
			try {
				Object tempobject=ips.readObject();
				return tempobject;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		public void sendToServer(int fuction,Object object)
		{
			try {				
					ops.writeInt(fuction);		
					ops.writeObject(object);
			} catch (IOException e) {
				Log.e("Loi Nay 1", e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void cancel()
		{
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


}

