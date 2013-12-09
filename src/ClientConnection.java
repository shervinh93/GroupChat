import java.io.*;
import java.net.Socket;

public class ClientConnection {

	private Socket connectionToServer = null;
	private PrintWriter outStream = null;
	private BufferedReader inStream = null;

	public ClientConnection(String serverAddress, int serverPort) throws IOException {
		connectionToServer = new Socket(serverAddress, serverPort);

		//The getOutputStream and getInputStream methods return byte streams
		outStream = new PrintWriter(new OutputStreamWriter(connectionToServer.getOutputStream()));
		inStream = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));
	}

	public void send(String sMsg) {
		outStream.println(sMsg);
		outStream.flush();
	}

	public String receive() {
		String rMsg = null;

		try {
			rMsg = inStream.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rMsg;
	}

	public void close(){
		try{
			inStream.close();
			outStream.close();
			connectionToServer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
