import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ChatGUI extends JFrame {

	private Properties properties;
	private ClientConnection clientConnection;

	public ChatGUI(){
		properties = new Properties();
		loadProperties();
		connectToServer(properties.getProperty("address"), Integer.parseInt(properties.getProperty("port")));
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
