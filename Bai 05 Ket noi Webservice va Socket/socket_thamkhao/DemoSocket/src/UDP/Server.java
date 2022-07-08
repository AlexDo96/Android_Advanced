package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

	public static void main(String[] args) throws IOException {
		
		DatagramSocket serverSocket;
		DatagramPacket dpRev;
		DatagramPacket dpSent;
		
		serverSocket=new DatagramSocket(5000);
		System.out.println("Server is running...");
		
		while(true){
		
			byte[] buff=new byte[1000];
			dpRev=new DatagramPacket(buff, buff.length);
			serverSocket.receive(dpRev);
			
			String s=new String(buff, 0, buff.length);
			System.out.println(s);
			
			s=s.toUpperCase();
			
			dpSent=new DatagramPacket(s.getBytes(), s.length(), dpRev.getAddress(),dpRev.getPort());
			serverSocket.send(dpSent);
			
		}
		
	}

}
