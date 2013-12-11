import java.util.Scanner;

import Server.Server;


public class Main {

	public static void main(String[] args) {
		
		boolean run = true;
		Scanner input = new Scanner(System.in);
		String option;
		
//		while (run){
			
			System.out.println("1: Server");
			System.out.println("2: Client");
			System.out.println("3: Quit");
			option = input.nextLine();
			
			switch (option){
			
			case "1":
				System.out.println("---------------");
				System.out.print("Port (52000+): ");
				option = input.nextLine();
				Server server = new Server();
				server.init(Integer.parseInt(option));
				server.waitForConnections(); 	
			break;
			
			case "2":
				ChatGUI clientGUI = new ChatGUI();
				
			break;
			case "3":	System.exit(0);
			
			break;
			default: System.out.println("ERROR");
			break;}
//		}
			
			
		}

	}

