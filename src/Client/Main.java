package Client;

public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){

			public void run() {
				ChatGUI g = new ChatGUI(new Client());
			}
			
		});
		
	}

}
