import javax.swing.*;

public class ChatGUI extends JFrame {

	String username, serverAddress, port;

	public ChatGUI(String serverAddress, int port) {
		this.serverAddress = serverAddress;
		this.port = Integer.toString(port);
	}

	public void printMessage(String message) {

	}

	public String askForUsername() {
		String username = null;

		while (username == null)
			username = JOptionPane.showInputDialog("Input username:");

		this.username = username;

		return username;
	}
}
