package Client;

public class ListenForServer implements Runnable{
	private Client client;
	private ChatGUI gui;
	
	public ListenForServer(Client c, ChatGUI g){
		this.client = c;
		this.gui = g;
	}
	
	public void run() {
		
		while(true){
			String inputmessage = client.receive();
			gui.appendRecievedText(inputmessage);
		}
	}
}
