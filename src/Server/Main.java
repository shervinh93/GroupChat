package Server;

public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		server.init(52000);
		server.waitForConnections();

	}

}
