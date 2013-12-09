import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
	private ServerSocket serverSocket = null;
	private Socket connectionToClient = null;
	private ArrayList<Observer> observerList;

	public void init(int port){
		observerList = new ArrayList<Observer>();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitForConnection(){
		while(true){
			try {
				connectionToClient = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Thread newThread = new Thread(new ServerHandler(connectionToClient));
			Observer observer = (Observer) newThread;
			registerObserver(observer);
			newThread.start();
		}
	}

	public void close(){

	}

	public void registerObserver(Observer ob){
		observerList.add(ob);
	}

	public void unRegisterObserver(Observer ob){
		observerList.remove(ob);
	}

	public void notifyObservers(){
		for(Observer o : observerList){
			o.update();
		}
	}
}
