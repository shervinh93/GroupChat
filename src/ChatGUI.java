import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChatGUI extends JFrame{

	String username, serverAddress, port;
	
//	JTextArea textArea = new JTextArea(30 ,28);
//	JTextField textField = new JTextField("hejsan");
//	String text;
//	
//	public void createWindow(){
////		fönster
//		
//		 setResizable(false);
//		 setSize(500, 500);
//		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////		 arkivmeny
//		 JMenuBar menuBar = new JMenuBar();
//		 setJMenuBar(menuBar);
//		 JMenu fileMenu = new JMenu("File");
//		 menuBar.add(fileMenu);
//		 JMenuItem connect = new JMenuItem("Connect");
//		 JMenuItem disconnect = new JMenuItem("Disconnect");
//		 JMenuItem exit = new JMenuItem("Exit");
//		 fileMenu.add(connect);
//		 fileMenu.add(disconnect);
//		 fileMenu.add(exit);
//		 JMenu helpMenu = new JMenu("Help");
//		 menuBar.add(helpMenu);
//	     JMenuItem about = new JMenuItem("About");
//     	 helpMenu.add(about);
//     	 		        	
////		 panel
//		 JPanel panel = new JPanel();
//		 getContentPane().add(panel);
//		 
//		 textArea.setEditable(false);
//		 panel.add(textArea);
//		 
//		 panel.add(textField);
//		 
//		 JButton button1 = new JButton("Send");
//		 panel.add(button1);
//		 button1.setActionCommand("Send");
//		 button1.addActionListener(this);
//		 
//		 connect.addActionListener(this);
//		 connect.setActionCommand("connect");
//		 
//		 setTitle("GroupChat");
//		 setVisible(true);
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		if ("Send".equals(arg0.getActionCommand())){
//			textArea.append(textField.getText()+"\n");			
//       	}	       
//    	else if ("connect".equals(arg0.getActionCommand())) {
//         	String name = JOptionPane.showInputDialog("Name");
//    		String port = JOptionPane.showInputDialog("Port");
//        }
//	}

	
	public void printMessage(String message) {

	}

	public String askForUsername() {
		String username = null;

		while (username == null)
			username = JOptionPane.showInputDialog("Input username:");

		this.username = username;

		return username;
	}
	
	public ChatGUI(String serverAddress, int port) {
		this.serverAddress = serverAddress;
		this.port = Integer.toString(port);
	}
}
