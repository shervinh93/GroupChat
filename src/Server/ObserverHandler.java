package Server;

import java.util.ArrayList;


public class ObserverHandler {
	private ArrayList<ServerConnection> observerList;

	public ObserverHandler() {
		observerList = new ArrayList<ServerConnection>();
	}

	public synchronized void registerObserver(ServerConnection connection) {
		observerList.add(connection);
		System.out.println("client registered as a observer");
		System.out.println(observerList.size() + " clients connected");
	}

	public synchronized void unRegisterObserver(ServerConnection connection) {
		observerList.remove(connection);

		System.out.println(observerList.size() + " clients connected");
	}

	public synchronized void notifyObservers(String message) {
		if (message != null) {
			for (ServerConnection con : observerList) {
				con.update(message);
			}
			System.out.println("Clients uppdated");
		}
	}
}
