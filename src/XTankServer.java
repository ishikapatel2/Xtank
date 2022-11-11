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
		
        try (ServerSocket listener = new ServerSocket(59890)) 
        {
            System.out.println("The XTank server is running...");
            var pool = Executors.newFixedThreadPool(10);
            Game game = new Game();

            while (true) {
            	
            	
                pool.execute(new XTankManager(listener.accept()));
                
            }
        }
    }

    private static class XTankManager implements Runnable 
    {
    	private Player currentPlayer;
    	
        private Socket socket;
        

        XTankManager(Socket socket) {
        	this.socket = socket;
        	
        }

        @Override
        public void run() 
        {
            System.out.println("Socket is Connected!");
            try 
            {

            	DataInputStream in = new DataInputStream(socket.getInputStream());
            	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            	
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