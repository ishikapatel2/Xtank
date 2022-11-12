import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * When a client connects, a new thread is started to handle it.
 */
public class XTankServer 
{
	static ArrayList<DataOutputStream> sq;
	
    public static void main(String[] args) throws Exception 
    {
		System.out.println(InetAddress.getLocalHost());
		sq = new ArrayList<>();
		
		// Connects to the socket
        try (ServerSocket listener = new ServerSocket(12345)) 
        {
            System.out.println("The XTank server is running...");
            var pool = Executors.newFixedThreadPool(10);
            Game game = new Game();

            while (true) {
            	
            	
                pool.execute(new XTankManager(listener.accept()));
                
            }
        }
    }
    
private static class XTankManager implements Runnable {
    	
    	
        private Socket socket;
        private static int startingPositionX;
    	private static int startingPositionY;
    	private static int playerID;
        

        XTankManager(Socket socket) {
        	this.socket = socket;
        	startingPositionX = (int)(Math.random() * ((30) + 400)) + 30;
    		startingPositionY = (int)(Math.random() * ((400 - 30) + 1)) + 30;
    		playerID = 1;
        	
        }

        @Override
        public void run() 
        {
            System.out.println("Socket is Connected!");
            try 
            {

            	DataInputStream in = new DataInputStream(socket.getInputStream());
            	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            	var ui = new XTankUI(in, out, startingPositionX, startingPositionY, playerID, "MAP2");
            	playerID++;
                ui.start(out);
            	
                sq.add(out);
                while (true)
                {
                	Scanner scanner = new Scanner(in);
					String line = scanner.nextLine();
					String fix = "";
					for (int i = 0; i < line.length(); i++) { 
						char c = line.charAt(i);
						if (c == ',' || c == '(' || c == ')' || Character.isLetterOrDigit(c) ) {
							fix += line.charAt(i) + "";
						}
					}
					scanner.close();
					System.out.println(fix);
					out.writeChars(fix);
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Error:" + socket);
            } 
            finally 
            {
                try { socket.close(); } 
                catch (IOException e) {}
                System.out.println("Closed: " + socket);
            }
          
        }
        }
    
}