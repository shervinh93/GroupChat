import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
	private ServerSocket serverSocket = null;
	private Socket connectionToClient = null;
	private ArrayList<Observer> observerList;

	public void init(int port){
		log("trying to initialize the server");
		
		ObserverHandler obh = new ObserverHandler();
		observerList = new ArrayList<Observer>();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log("Succeded");
	}

	public void waitForConnections(){
		log("waiting for connections");
		while(true){
			try {
				connectionToClient = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ServerConnection connection = new ServerConnection(connectionToClient);
			Thread newThread = new Thread(connection);
			Observer observer = (Observer) connection;

			newThread.start();
		}
	}

	public void close(){
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void log(String log_message){
		System.out.println(log_message);
	}
	
	public static void main(String[] args){
		Server server = new Server();
		server.init(52000);
		server.waitForConnections();
	}
}
