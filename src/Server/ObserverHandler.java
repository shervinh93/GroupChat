package Server;
import java.util.ArrayList;


public class ObserverHandler {
	private ArrayList<ServerConnection> observerList;
	
	public ObserverHandler(){
		observerList = new ArrayList<ServerConnection>();
		
	}
	
	public synchronized void registerObserver(ServerConnection connection){
		observerList.add(connection);
		System.out.println("client registered as a observer");
	}

	public synchronized void unRegisterObserver(ServerConnection connection){
		observerList.remove(connection);
	}

	public synchronized void notifyObservers(String message){
		for(ServerConnection con : observerList){
			con.update(message);
		}
	}
}
