import java.net.Socket;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
/*
 * Author : Prasiddhi Gyawali, Ishika Patel
 * Date   : 11/12/22
 * Class  : CSC 335
 * File   : XTank.java
 * 
 * Purpose : This class creates a new socket and  UI for each player that connects to the server. A new thread is created
 *           for each player that joins. 
 */
public class XTank {
	
	private static int tankModel;
	private static String weapon;
	private static String map;
	;
	public static void main(String[] args) throws Exception  {
		
		// Asks the user their tank model, weapon choice, and map
		
		System.out.println("Please choose your tank model (1 or 2): ");
		Scanner scan = new Scanner(System.in);
		
		if (scan.nextInt() == 1) {
			tankModel = 1;
		}
		else {
			tankModel = 2;
		}

		System.out.println("Please choose your weapon: bullets or spikes");
		
		scan = new Scanner(System.in);
		if (scan.nextLine().equals("bullets")) {
			weapon = "bullets";
		}
		else {
			weapon = "spikes";
		}
		
		System.out.println("Please choose your map: M1 or M2");
		
		scan = new Scanner(System.in);
		if (scan.nextLine().equals("M1")) {
			map = "M1";
		}
		else {
			map = "M2";
		}

		
        try (var socket = new Socket("127.0.0.1", 12345)) 
        {
        	DataInputStream in = new DataInputStream(socket.getInputStream());
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        	var ui = new XTankUI(in, out, tankModel, weapon, map);
            ui.start();
        }
}
}