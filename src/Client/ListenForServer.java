package Client;

public class ListenForServer implements Runnable{
	private Client client;
	private ChatGUI gui;
	
	public ListenForServer(Client c, ChatGUI g){
		System.out.println("Listen for server constructor");
		this.client = c;
		this.gui = g;
	}
	
	public void run() {
		System.out.println("in run method");
		while(true){
			String inputmessage = client.receive();
			System.out.println("Client received: " + inputmessage);
			gui.appendRecievedText(inputmessage);
		}
	}
}
