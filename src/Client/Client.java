package Client;

import java.io.*;
import java.net.Socket;

public class Client {

	protected Socket connectionToServer = null;
	private boolean connected = false;
	private PrintWriter outStream = null;
	private BufferedReader inStream = null;

<<<<<<< HEAD:src/Client/Client.java
	public void connect(String serverAddress, int serverPort) throws IOException {
		connectionToServer = new Socket(serverAddress, serverPort);
		connected = true;
=======

	public ClientConnection(String serverAddress, int serverPort) throws IOException {
		connectionToServer = new Socket(serverAddress, serverPort);

>>>>>>> 898c15f64524c3a83c91ebc6227b48136540500e:src/ClientConnection.java
		outStream = new PrintWriter(new OutputStreamWriter(connectionToServer.getOutputStream()));
		inStream = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));
		System.out.println("Connected");
	}

	public void sendUsername(String userName) {
		outStream.println(userName);
		outStream.flush();
	}

	public void send(String sMsg) {
		outStream.println(sMsg);
		outStream.flush();
	}

	public String receive() {
		String message = null;

		try {
			message = inStream.readLine();
			System.out.println("Client received message");
		} catch (IOException e) {
			close();
		}

		return message;
	}

	public void close() {
		try {
			inStream.close();
			outStream.close();
			connectionToServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnected() {
		return connected;
	}
}
