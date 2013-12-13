package Server;

import javax.swing.JOptionPane;


public class Main {

	public static void main(String[] args) {
		int port;
		
		while(true){
			try{
				String t = JOptionPane.showInputDialog("Enter port: ");
				if(t != null){
					port = Integer.parseInt(t);
					break;
				}
				else
					System.exit(0);
			}
			catch(NumberFormatException e){
				continue;
			}
		}

		Server server = new Server();
		server.init(port);
		server.waitForConnections();
	}
}
