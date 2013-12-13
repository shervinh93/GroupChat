package Server;

import javax.swing.JOptionPane;


public class Main {

	public static void main(String[] args) {
		int port = Integer.parseInt(JOptionPane.showInputDialog("Enter port: "));

		Server server = new Server();
		server.init(port);
		server.waitForConnections();
	}
}
