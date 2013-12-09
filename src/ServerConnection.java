import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerConnection implements Runnable, Observer{
	private Socket socket;
	BufferedReader input;
	PrintWriter output;

	public ServerConnection(Socket connectionToClient) {
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket = connectionToClient;
	}

	@Override
	public void update(String message) {
		send(message);
		
	}

	public void send(String message){
		output.write(message);
		output.flush();
	}
	
	public void receive(String message){
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
