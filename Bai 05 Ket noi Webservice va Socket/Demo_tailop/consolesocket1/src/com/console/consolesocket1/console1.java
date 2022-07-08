package com.console.consolesocket1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;


public class console1 {
	
	InetAddress ip; //ip cua may
	int port=8889; 
	String TimeStamp; //lay thoi gian
	ServerSocket server; //socket phia server
	Socket client;  //socket phia client
	
	public console1()
	{
		System.out.print("chay ne, dang doi client ket noi.\n");
		try { //lay ip may
			ip=InetAddress.getLocalHost();
			System.out.print("ip may chu la " + ip.getHostAddress()+"\n");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			server=new ServerSocket(port);
			TimeStamp =new Date().toString();

			client=server.accept();
			System.out.print("client da ket noi\n");
			
			//thong bao xuong cho client
			BufferedWriter writer=new BufferedWriter(
					new OutputStreamWriter(client.getOutputStream()));
			String input = "server: da ket noi luc"+TimeStamp;
			writer.write(input + "\n", 0, input.length() + 1);
			writer.flush();
			
			while(true) //nghe lien tuc
			{
					System.out.print("dang nghe\n");
					//doc tu client len
					BufferedReader reader=new BufferedReader(
							new InputStreamReader(client.getInputStream()));
					String textline=reader.readLine();
					if(textline.equalsIgnoreCase("client:exit"))
					{
						System.out.print("ngung ket noi\n");
						break; //đóng client
					}
					if(textline.equalsIgnoreCase("client:tat may"))
					{
						Runtime r=Runtime.getRuntime();
						Process p=r.exec("shutdown -s -t 60");  
					}
					System.out.print(textline);
			}
				System.out.print("client da dong ket noi");
				client.close();//dong client lai
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			try { //kich server lai khi android nhan back se phat sinh loi
				System.out.print("client ngat ket noi, server chay lai\n");
				server.close();
				client.close();
				new console1();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		
	}


	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.out.print("test");
		new console1();
	}
	
}
