package Client;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class Client {

	protected Socket connectionToServer = null;
	private boolean connected = false;
	private PrintWriter outStream = null;
	private BufferedReader inStream = null;
	private Properties properties;

	public void connect(String serverAddress, int serverPort) throws IOException {
		connectionToServer = new Socket(serverAddress, serverPort);
		connected = true;
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

	public void loadProperties() {
		try {
			properties.load(new FileReader("prop"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
