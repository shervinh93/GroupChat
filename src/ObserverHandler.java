import java.util.ArrayList;


public class ObserverHandler {
	private ArrayList<ServerConnection> observerList;
	
	public ObserverHandler(){
		observerList = new ArrayList<ServerConnection>();
		System.out.println("klient registered as a observer");
	}
	
	public synchronized void registerObserver(ServerConnection connection){
		observerList.add(connection);
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
