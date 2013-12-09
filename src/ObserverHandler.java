import java.util.ArrayList;


public class ObserverHandler {
	private ArrayList<ServerConnection> observerList;
	
	public ObserverHandler(){
		observerList = new ArrayList<ServerConnection>();
	}
	
	public void registerObserver(ServerConnection connection){
		observerList.add(connection);
	}

	public void unRegisterObserver(ServerConnection connection){
		observerList.remove(connection);
	}

	public void notifyObservers(String message){
		for(ServerConnection con : observerList){
			con.update(message);
		}
	}
}
