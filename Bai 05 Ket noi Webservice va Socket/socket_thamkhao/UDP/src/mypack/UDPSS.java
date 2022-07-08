package mypack;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class UDPSS 
{
	public static void main(String[] args) throws Exception
	{
		int port = 2812;
		DatagramSocket socket = new DatagramSocket(port);
		DatagramPacket packet;
		byte []data;
		try{
			data = new byte[1024];
			packet = new DatagramPacket(data,data.length);
			socket.receive(packet);
			String st = new String(packet.getData(),0,packet.getLength());	
			double so = Double.parseDouble(st);
			InetAddress ipC = packet.getAddress();
			int portC = packet.getPort();

			double kqD = XuLy(so);
			String kqS = String.valueOf(kqD);
			data = kqS.getBytes();
			packet = new DatagramPacket(data,data.length,ipC,portC);
			socket.send(packet);
			socket.close();
		}
		catch (UnknownHostException evt){ evt.printStackTrace(); }
	}
	public static double XuLy(double so)
	{
		return Math.sqrt(so);
	}
}
