package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket = null;
	private Socket connectionToClient = null;
	private ObserverHandler obh;


	public void init(int port) {
		log("trying to initialize the server");

		obh = new ObserverHandler();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log("Succeded");
	}

	public void waitForConnections() {
		log("waiting for connections");
		while (true) {
			try {
				connectionToClient = serverSocket.accept();
				System.out.println("Connection received");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Thread newThread = new Thread(new ServerConnection(connectionToClient, obh));
			newThread.start();
		}
	}

	public void log(String log_message) {
		System.out.println(log_message);
	}
}
