package Client;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Chat{

	private Properties properties;
	private Client client;

	/*Constructor for chat*/
	public Chat(Client client, Properties prop){
		System.out.println("konstruktor!?! Chat");
		this.client = client;
		this.properties = prop;
		
		loadProperties();
		
		try {
			client.connect(properties.getProperty("address"), Integer.parseInt(properties.getProperty("port")));
		} catch (NumberFormatException e) {
			System.out.println("Error on connection");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error on connection");
			e.printStackTrace();
		}
	}
	
	/*returns the clientConnection*/
	public Client getClient(){
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