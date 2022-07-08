package TCP;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, 
	ClassNotFoundException {
		ServerSocket wellcomSocket;
		Socket connectionSocket;
		wellcomSocket=new ServerSocket(5000);
		System.out.println("Server is running...");
		while(true){
			connectionSocket=wellcomSocket.accept();
			new TCPThread(connectionSocket);
		}
	}
}
