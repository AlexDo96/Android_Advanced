package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Clietn {

	
	public static void main(String[] args) throws IOException {
		
		DatagramSocket clientSocket;
		DatagramPacket dp;
		
		clientSocket=new DatagramSocket();
		System.out.println("clietn is running...");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			System.out.println("Nhap mot chuoi:");
			String s=br.readLine();
			
			dp=new DatagramPacket(s.getBytes(), s.length(), InetAddress.getLocalHost(), 5000);
			clientSocket.send(dp);
			
			dp=new DatagramPacket(new byte[1000], 1000);
			clientSocket.receive(dp);
			
			String t=new String(dp.getData(), 0, dp.getData().length);
			System.out.println(t);
		}
		
		
	}

}
