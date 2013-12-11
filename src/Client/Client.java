package Client;
import java.io.*;
import java.net.Socket;

public class Client {

	private Socket connectionToServer = null;
	private PrintWriter outStream = null;
	private BufferedReader inStream = null;

	public void connect(String serverAddress, int serverPort) throws IOException {
		connectionToServer = new Socket(serverAddress, serverPort);

		outStream = new PrintWriter(new OutputStreamWriter(connectionToServer.getOutputStream()));
		inStream = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));
		System.out.println("Connected");
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
