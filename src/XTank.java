import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class XTank 
{
	private static int startingPositionX;
	private static int startingPositionY;
	private static int playerID;
	public XTank() {
		
		// starts each player's tank at a random position
		startingPositionX = (int)(Math.random() * ((30) + 400)) + 30;
		startingPositionY = (int)(Math.random() * ((400 - 30) + 1)) + 30;
		playerID = 1;
		
	}
	public static void main(String[] args) throws Exception 
    {
		// opens socket and start the UI for each client
        try (var socket = new Socket("127.0.0.1", 12345)) 
        {
        	DataInputStream in = new DataInputStream(socket.getInputStream());
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        	var ui = new XTankUI(in, out, startingPositionX, startingPositionY, playerID);
        	playerID++;
            ui.start();
        }
    }
}