package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Data.User;
//class server su ly yeu cau tu client
public class Server {

	//Khoi tao serversocket
	ServerSocket serversocket;
	//Khoi tao socket
	Socket socket;
	//Constructor khoi tao
	public static User[] listuser;
	public static ArrayList<Threading> lstThreading;
	public static ArrayList<String> lstonlineusers;
	int i=0;
	public Server(int port)
	{
		try {
			lstThreading=new ArrayList<>();
			//Khoi tao server voi port
			serversocket=new ServerSocket(port);
			System.out.println("Server is running");
			System.out.println(serversocket);
			//Tao co so du lieu tai khoan
			listuser=new User[3];
			listuser[0]=new User("hung", "123");
			listuser[1]=new User("vu", "123");
			listuser[2]=new User("linh", "123");	
			lstonlineusers=new ArrayList<>();
			do
			{
				//Cho phep socket ket noi
				socket=serversocket.accept();
				//socket.setTcpNoDelay(true);
				System.out.println(i+" " +socket);
				new Threading(socket);	
				i++;
			}
			while(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			try {
				serversocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server(2048);

	}

}
