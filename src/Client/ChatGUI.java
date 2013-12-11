package Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.*;

public class ChatGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton button1 = new JButton("Send");
	private JTextArea textArea = new JTextArea(30 ,28);
	private JTextField textField = new JTextField("hejsan");
	private JPanel panel = new JPanel();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem connect = new JMenuItem("Connect");
	private JMenuItem disconnect = new JMenuItem("Disconnect");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenu helpMenu = new JMenu("Help");
	private JMenuItem about = new JMenuItem("About");
	private Properties prop = new Properties();	
	private Client client;
	private String address = "localhost";
	
	public ChatGUI(Client client){
		this.client = client;
		createWindow();
	}
	
	public void createWindow(){
//		 fönster
		 setResizable(false);
		 setSize(500, 500);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setTitle("GroupChat");
		 
//		 arkivmeny
		 setJMenuBar(menuBar);
		 menuBar.add(fileMenu);
		 fileMenu.add(connect);
		 fileMenu.add(disconnect);
		 fileMenu.add(exit);
		 menuBar.add(helpMenu);
		 helpMenu.add(about);
     	 		        	
//		 panel
		 getContentPane().add(panel);
		 textArea.setEditable(false);
		 panel.add(textArea);
		 panel.add(textField);
		 panel.add(button1);
		 setVisible(true);
		 
//		 actioncommand & actionlistener
		 button1.setActionCommand("Send");
		 button1.addActionListener(this); 
		 connect.setActionCommand("connect");
		 connect.addActionListener(this); 
	}
	
	public void writeProperties(String name, String port){
		
		try {
			//set the properties value
			prop.setProperty("name", name);
			prop.setProperty("address", address);
			prop.setProperty("port", port);

			//save properties to project root folder
			prop.store(new FileOutputStream("prop"), null);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void appendRecievedText(){
		textArea.append(textField.getText()+"\n");
	}
	
	public void sendInputText(){
		//textField.getText();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if ("Send".equals(arg0.getActionCommand())){
			String message = textField.getText();
			client.send(message);
//			textArea.append(textField.getText()+"\n");
       	}	       
    	else if ("connect".equals(arg0.getActionCommand())) {
    		String name = JOptionPane.showInputDialog("Nickname:");
    		String port = JOptionPane.showInputDialog("Port:");
    		writeProperties(name, port);
    		try {
				client.connect(address, Integer.parseInt(port));
			} catch (NumberFormatException | IOException e) {
				JOptionPane.showMessageDialog(panel, "No server found");
				e.printStackTrace();
			}
        }
	}

}
