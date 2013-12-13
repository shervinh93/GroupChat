package Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class ChatGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton button1 = new JButton("Send");
	private JTextArea textArea = new JTextArea(30, 40);
	private JScrollPane scroll = new JScrollPane(textArea);
	private JTextField textField = new JTextField(33);
	private JPanel panel = new JPanel();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem connect = new JMenuItem("Connect");
	private JMenuItem disconnect = new JMenuItem("Disconnect");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenu helpMenu = new JMenu("Help");
	private JMenuItem about = new JMenuItem("About");
	private Properties prop = new Properties();
	private Client client = null;
	private String address = "localhost";

	public ChatGUI(Client client) {
		this.client = client;
		try {
			prop.load(new FileInputStream("prop"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		createWindow();
	}

	public void createWindow() {
//		 f���nster
		setResizable(false);
		setSize(600, 600);
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
//		 panel.setLayout(new BorderLayout());
		getContentPane().add(panel);
		textArea.setEditable(false);
		panel.add(scroll);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
//		 panel.add(textArea);
		panel.add(textField);
		panel.add(button1);
		setVisible(true);

//		 actioncommand & actionlistener
		button1.setActionCommand("Send");
		button1.addActionListener(this);
		connect.setActionCommand("connect");
		connect.addActionListener(this);

		button1.requestFocusInWindow();
	}

	public void writeProperties(String name, int port, String address) {

		try {
			//set the properties value
			prop.setProperty("name", name);
			prop.setProperty("address", address);
			prop.setProperty("port", Integer.toString(port));

			//save properties to project root folder
			prop.store(new FileOutputStream("prop"), null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void appendRecievedText(String message) {
		textArea.append(message + "\n");
	}

//	public void sendInputText(){
//		client.send(": "+textField.getText());
//	}

	public void setConnected(boolean connected) {
		if (connected) {
			connect.setEnabled(false);
			disconnect.setEnabled(true);
		} else {
			connect.setEnabled(true);
			disconnect.setEnabled(false);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if ("Send".equals(arg0.getActionCommand())) {
			if (client.isConnected()) {
				String message = textField.getText();
				textField.setText("");
				client.send(message);
			} else {
				textArea.append("You have to connect before sending any messages.");
			}
		} else if ("connect".equals(arg0.getActionCommand())) {    	
			String name = JOptionPane.showInputDialog("Nickname:");
			int port = Integer.parseInt(JOptionPane.showInputDialog("Port:"));
			String address = JOptionPane.showInputDialog("Address:");
			writeProperties(name, port, address);
			try {
				client.connect(prop.getProperty("address"), Integer.parseInt(prop.getProperty("port")));
				client.sendUsername(prop.getProperty("name"));
				Thread t = new Thread(new ListenForServer(client, this));
				t.start();
			} catch (NumberFormatException | IOException e) {
				JOptionPane.showMessageDialog(panel, "No server found");
				e.printStackTrace();
			}
		} else if ("Exit".equals(arg0.getActionCommand())) {
			client.close();
		}
	}
}
