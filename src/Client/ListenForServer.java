package Client;

public class ListenForServer implements Runnable{
	private Client client;
	private ChatGUI gui;
	
	public ListenForServer(Client client, ChatGUI gui){
		System.out.println("Listen for server constructor");
		this.client = client;
		this.gui = gui;
	}
	
	public void run() {
		System.out.println("in run method");
		while(true){
			String inputmessage = client.receive();
			gui.appendRecievedText(inputmessage);
		}
	}
}
