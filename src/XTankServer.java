import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.net.InetAddress;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * When a client connects, a new thread is started to handle it.
 */
public class XTankServer 
{
	static ArrayList<DataOutputStream> sq;
	static int playerID = 1;

	
    public static void main(String[] args) throws Exception 
    {
		System.out.println(InetAddress.getLocalHost());
		sq = new ArrayList<>();
		
        try (var listener = new ServerSocket(12345)) 
        {
            System.out.println("The XTank server is running...");
            var pool = Executors.newFixedThreadPool(20);
           
            while (true) 
            {
                Player player = new Player(playerID, 0);
                pool.execute(new XTankManager(listener.accept(), player));
                playerID++;
            }
        }
    }

    private static class XTankManager implements Runnable 
    {
        private Socket socket;
        private Player currentPlayer;

        XTankManager(Socket socket, Player player) 
        {
            this.socket = socket;
            this.currentPlayer = player;
        }

        @Override
        public void run() 
        {
            System.out.println("Connected: " + socket);
            try 
            {
            	DataInputStream in = new DataInputStream(socket.getInputStream());
            	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(in);
                PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true);
                sq.add(out);
                int currid = currentPlayer.getID();
                int x = (int)(Math.random()*500);
                int y = (int)(Math.random()*500);
                int dir = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                
                currentPlayer.setX(x);
                currentPlayer.setY(y);
                outWriter.println("YOURID: " + currid + " X: " + x + " Y: " + y + " D: " + dir);
                while (true)
                {
					if (in.available() > 0) {
                        String line = scanner.nextLine();
                	for (DataOutputStream o: sq)
                	{
                		PrintWriter outWriter2 = new PrintWriter(o, true);
    						outWriter2.println(line);
                	}
                    }
					
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