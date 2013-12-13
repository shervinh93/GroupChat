package Client;

public class ListenForServer implements Runnable {
	private Client client;
	private ChatGUI gui;

	public ListenForServer(Client client, ChatGUI gui) {
		System.out.println("Listen for server constructor");
		this.client = client;
		this.gui = gui;
	}

	public void run() {
		System.out.println("in run method");
		gui.setConnected(true);
		while (true) {
			String inputMessage = client.receive();
			if (inputMessage != null)
				gui.appendRecievedText(inputMessage);
			else {
				gui.appendRecievedText("Disconnected from server. Please reconnect.");
				gui.setConnected(false);
				client.close();
				break;
			}
		}
	}
}
