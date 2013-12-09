import java.util.ArrayList;


public class ObserverHandler {
	private ArrayList<Observer> observerList;
	
	public ObserverHandler(){
		observerList = new ArrayList<Observer>();
	}
	
	public void registerObserver(Observer ob){
		observerList.add(ob);
	}

	public void unRegisterObserver(Observer ob){
		observerList.remove(ob);
	}

	public void notifyObservers(String message){
		for(Observer o : observerList){
			o.update(message);
		}
	}
}
