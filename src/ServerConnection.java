import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ServerConnection implements Runnable, Observer{

	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;

	public ServerConnection(Socket connectionToClient) {
		socket = connectionToClient;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//updates observers
	public void update(String message) {
		send(message);
	}
	
	public Socket getSocket(){
		return socket;
	}

	//sends message o clients
	public void send(String message){
		output.write(message);
		output.flush();
	}
	
	//receives a string from a client
	public String receive(){
		String message = "";
		try {
			message = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	//the run method for each thread
	public void run() {
		while(true){
			server.notifyObservers(receive());
		}
	}

}
