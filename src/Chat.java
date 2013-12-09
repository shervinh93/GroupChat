import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Chat {

	private Properties properties;
	private ClientConnection clientConnection;
	private ChatGUI gui;
	private String username;

	public Chat() {
		properties = new Properties();
		loadProperties();
		connectToServer(properties.getProperty("address"), Integer.parseInt(properties.getProperty("port")));
		gui = new ChatGUI(properties.getProperty("address"), Integer.parseInt(properties.getProperty("port")));
	}

	public void connectToServer(String serverAddress, int port) {
		try {
			clientConnection = new ClientConnection(serverAddress, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadProperties() {
		try {
			properties.load(new FileReader("prop"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
