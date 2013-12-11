import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Chat{

	private Properties properties;
	private ClientConnection client;

	/*Constructor for chat*/
	public Chat(ClientConnection client){
		properties = new Properties();
		loadProperties();

		this.client = client;
		try {
			client.connect(properties.getProperty("address"), Integer.parseInt(properties.getProperty("port")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*returns the clientConnection*/
	public ClientConnection getClient(){
		return client;
	}

	/*Loads properties file*/
	public void loadProperties() {
		try {
			properties.load(new FileReader("prop"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}