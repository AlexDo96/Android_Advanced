package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Data.*;


public class Threading extends Thread implements Function{

	String user;
	//Khoi tao socket
	Socket socket;
	//Khoi tao luong ghi du lieu gui sang server
	ObjectOutputStream ops;
	//Khoi tao luong nhan du lieu tu server
	ObjectInputStream ips;
	Boolean isWorking=true;	
	//Constructor khoi tao nhan vao mot socket
	public Threading(Socket socket)
	{		
		try {
			
			//Gan socket
			this.socket=socket;
			//Gan luong ghi du lieu
			ops=new ObjectOutputStream(socket.getOutputStream());
			//Gan luong nhan du lieu
			ips=new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Ham bat buoc su dung neu ke thua tu Thread
		start();	
	}
	public void run()
	{		
		while(isWorking)
		{	
			
			try {
				//Doc du lieu 
				int function =ips.readInt();
				System.out.println("Function : "+function);
				switch (function) {
				case LOGIN:		
					login();
					break;
				case LOGOUT:
					logOut();
					break;
				case SEND_MESSAGE:	
					sendMessage();
					break;
				case SEND_MESSAGE_CHAT_ROOM:	
					sendMessageChatRoom();
					break;
				case GET_ONLINE_USER_LIST:
					getUserList();
					break;
				}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				Server.lstThreading.remove(this);
				cancel();
				stop();
			}			
			//Truyen lai du lieu cho client				
		}		
		
					
	}
	private void sendMessageChatRoom(){
		String message=(String)readFromClient();
		System.out.println(user+" gui tin nhan chat room");
		for (Threading thread : Server.lstThreading) {		
			thread.receiveMessageChatRoom(message);
		}
	}
	public void receiveMessageChatRoom(String message){
		System.out.println(user+" nhan tin nhan chat room");
		sendToClient(RECEIVE_MESSAGE_CHAT_ROOM, message);	
	}
	private void logOut(){
		//Loai bo threading khoi danh sach threading cua server
		Object o=readFromClient();
		Server.lstThreading.remove(this);
		Server.lstonlineusers.remove(user);
		alertOfflineToALL();
		cancel();
	}

	/**
	 * thuc thi chuc nang gui tin nhan
	 */
	private  void sendMessage()
	{
		//Khai bao va khoi tao DataMessge lay tu client
		DataMessage mSendMessage=(DataMessage)readFromClient();
		System.out.println("Dang send message");
		//Tim kiem user can gui tin nhan
		for (Threading e : Server.lstThreading) {
			if(mSendMessage.userTarget.equals(e.user))
			{		
				mSendMessage.receive=true;
				System.out.println(" Gui tin nhan thanh cong ");
				//Goi ham gui tin nhan cho user can gui
				e.receiveMessage(mSendMessage);
				break;
			}
		}		
	}
	/**
	 * Thuc thi chuc nang nhan tin nhan
	 * @param strUsernameSend nguoi gui
	 * @param strMessage tin nhan
	 */
	public  void receiveMessage(DataMessage message){		
		sendToClient(RECIEVE_MESSAGE,message);
	}
	
	/**
	 * Thuc thi chuc nang dang nhap
	 */
	private  void login()
	{
		
		System.out.println("Dang dang nhap");
		//Doc du lieu tu client
		DataLogin dataDangnhap= (DataLogin)readFromClient();
		//Tao co false la tai khoan khong ton tai hay user password sai 
		// true la dung username va password				
		boolean flag=false;
		//Vong lap kiem tra tat ca danh sach tai khoan
		for(int j=0;j<Server.listuser.length;j++)
		{
			//Kiem tra username, password voi tung tai khoan 
			if(dataDangnhap.username.equals(Server.listuser[j].getUserName())
					&& dataDangnhap.password.equals(Server.listuser[j].getPassWord()))
			{
				//Gan co la true thoat ra
				flag=true;
				break;
			}
		}
		if(flag==true)
		{
			System.out.println("Dang nhap thanh cong");	
			user=dataDangnhap.username;
			sendToClient(LOGIN_SUCCESS, null);	
			Server.lstThreading.add(this);
			Server.lstonlineusers.add(user);
			alertOnlineToAll();
		}
		else
		{
			System.out.println("Dang nhap that bai");	
			sendToClient(LOGIN_FAIL, null);
		}		
	}
	private void alertOnlineToAll(){
		System.out.println("Thong bao online cho tat ca");
		for (Threading thread : Server.lstThreading) {
			thread.receiveAlertOnlineUserToClient(user);
		}
				
	}
	/**
	 * Gui thong bao toi client  co mot nguoi ban  vua online 
	 */
	public void receiveAlertOnlineUserToClient(String friendname){	
		if(friendname.equals(user)==false)
		{
			sendToClient(ONLINE_USER,friendname);
		}
	}
	/**
	 * Thong bao toi ta ca friend da offline
	 */
	private void alertOfflineToALL(){
		System.out.println("Thong bao offline cho tat ca cac ban");
		for (Threading thread : Server.lstThreading) {
			thread.receiveAlertOfflineUsertoClient(user);
		}
				
	}
	/**
	 * Gui thong bao toi client co mot nguoi ban vua offline
	 */
	public void receiveAlertOfflineUsertoClient(String friendname){		
		if(friendname.equals(user)==false){	
			sendToClient(OFFLINE_USER, friendname);		
		}
	}
	
	/**
	 * Lay danh sach ban be va gui lai cho client
	 */
	private void getUserList(){
		Object obj= readFromClient();
		System.out.println("Lay danh sach user");		
		sendToClient(GET_ONLINE_USER_LIST,Server.lstonlineusers);	
	}
	public void cancel()
	{
		try {
			ips.close();
			ops.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
	}
	/**
	 * Ham doc du lieu tu client
	 * @return du lieu
	 */
	private Object readFromClient()
	{
		
		try {
			Object tempobject=ips.readObject();
			return tempobject;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block		
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	/**
	 * Ham gui chuc nang va du lieu ve client
	 * @param fuction chuc nang
	 * @param object du lieu
	 */
	private void sendToClient(int fuction,Object object)
	{
		try {
					
			ops.writeInt(fuction);		
			ops.writeObject(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
