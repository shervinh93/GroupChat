import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Chat{

	private static Properties properties;

	public static void loadProperties() {
		try {
			properties.load(new FileReader("prop"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		properties = new Properties();
		loadProperties();
		ClientConnection client = new ClientConnection();
			try {
				client.connect("127.0.0.1", 52000);
			} catch (IOException e) {
				System.err.println("Failed to connect to the server. " + e.getMessage());
				System.exit(1);
			}
			/*
			String receivedMessage = client.receive();
			System.out.println(receivedMessage);
			*/
			client.send("********** Hello there! **********");
			String receivedMessage = client.receive();
			System.out.println(receivedMessage);
			client.send("********** Hello again! **********");
			receivedMessage = client.receive();
			System.out.println(receivedMessage);
			client.send("bye");
			receivedMessage = client.receive();
			System.out.println(receivedMessage);
			client.close();
		}
}