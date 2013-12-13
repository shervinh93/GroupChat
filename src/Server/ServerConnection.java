package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
			output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		obsHandler.registerObserver(this);
		username = receive();
	}

	/*updates observers*/
	public void update(String message) {
		send(username + ": " + message);
		
	}

	public Socket getSocket(){
		return socket;
	}

	/*sends message o clients*/
	public void send(String message){
		output.println(message);
		output.flush();
	}

	/*receives a string from a client*/
	public String receive(){
		String message = "";
		try {
			message = input.readLine();
		} catch (IOException e) {
			this.close();
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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//the run method for each thread
	public void run() {
		System.out.println("new thread started");
		while(true){
			obsHandler.notifyObservers(receive());
			if(socket.isClosed()){
				break;
			}

		}

	}

	public String getUserName() {
		return username;
		
	}
}
