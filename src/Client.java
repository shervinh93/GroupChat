import java.io.*;
import java.net.Socket;

public class Client {

	private Socket connectionToServer = null;
	private PrintWriter outStream = null;
	private BufferedReader inStream = null;

	public Client(String serverAddress, int serverPort) throws IOException{
		connectionToServer = new Socket(serverAddress, serverPort);

		//The getOutputStream and getInputStream methods return byte streams
		outStream = new PrintWriter(new OutputStreamWriter(connectionToServer.getOutputStream()));
		inStream  = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));
	}

	public void send(String sMsg) {
		outStream.println(sMsg);
		outStream.flush();
	}

	public String receive() {
		String rMsg = null;

		return rMsg;
	}
}
