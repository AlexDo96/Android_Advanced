package TCP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
public class Client {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, 
	IOException, ClassNotFoundException {
		Socket clietnSocket;
		ObjectOutputStream os;
		ObjectInputStream is;
		BufferedReader br;		
		clietnSocket=new Socket(InetAddress.getLocalHost(), 5000);
		System.out.println("Client is running...");
		while(true){
			System.out.println("Nhap mot chuoi:");
			br=new BufferedReader(new InputStreamReader(System.in));
			String s=br.readLine();
			os=new ObjectOutputStream(clietnSocket.getOutputStream());
			os.writeObject(s);
			is=new ObjectInputStream(clietnSocket.getInputStream());
			String t=(String)is.readObject();
			System.out.println(t);
		}
	}

}
