import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerConnection implements Runnable, Observer{

	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private ObserverHandler obsHandler;

	public ServerConnection(Socket connectionToClient, ObserverHandler obh) {
		socket = connectionToClient;
		obsHandler = obh;
		
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		obsHandler.registerObserver(this);
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
			obsHandler.notifyObservers(receive());
		}
	}

}
