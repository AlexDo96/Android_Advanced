package mypack2;

import java.io.*;
import java.net.*;

class UDPCS
{
	public static void main(String[] args) throws Exception
	{
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet;
		byte []data;
		InetAddress ipS = InetAddress.getByName("localhost");
		int portS = 2812;
		BufferedReader in = new BufferedReader(new
			InputStreamReader(System.in));
		System.out.print("Nhap so bat ky : ");
		double so = Double.parseDouble(in.readLine());
		String st = String.valueOf(so);
		data = st.getBytes();
		packet = new DatagramPacket(data,data.length,ipS,portS);
		socket.send(packet);
		data = new byte[1024];
		packet = new DatagramPacket(data,data.length);
		socket.receive(packet);
		String kqS = new String(packet.getData(),0,packet.getLength());
		double kqD = Double.parseDouble(kqS);
		System.out.println("Can Bac Hai Tinh Duoc : " + kqD);
		socket.close();
	}
}
