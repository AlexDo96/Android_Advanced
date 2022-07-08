package TCP;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class TCPThread extends Thread {
	Socket connectionSocket;
	public TCPThread(Socket s){	
		connectionSocket=s;
		start();
	}
	public void run(){
		ObjectInputStream is = null; 
		ObjectOutputStream os=null;
		String s = null;
		while(true){
			try {
				is=new ObjectInputStream(connectionSocket.getInputStream());
				os=new ObjectOutputStream(connectionSocket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				s=(String)is.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}			
			System.out.println(s);
			s=s.toUpperCase();
			try {
				os.writeObject(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
