package Server;
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
	private String username = "";

	/*Constructor for ServerConnection*/
	public ServerConnection(Socket connectionToClient, ObserverHandler obh) {
		obsHandler = obh;
		socket = connectionToClient;

		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		obsHandler.registerObserver(this);
	}

	/*updates observers*/
	public void update(String message) {
		send(username + ": " + message);
		System.out.println("updating clients");
	}
	
	public Socket getSocket(){
		return socket;
	}

	/*sends message o clients*/
	public void send(String message){
		output.println(message);
		output.flush();
		System.out.println("message sent:" + message);
	}
	
	/*receives a string from a client*/
	public String receive(){
		String message = "";
		try {
			message = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	/* unregisters the client from the handler,
	 * closes the streams and 
	 * closes the socket */
	public void close(){
		try {
			input.close();
			output.close();
			obsHandler.unRegisterObserver(this);
			socket.close();
			this.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//the run method for each thread
	public void run() {
		System.out.println("new thread started");
		while(true){
			if(socket.isConnected())
				obsHandler.notifyObservers(receive());
			else{
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
