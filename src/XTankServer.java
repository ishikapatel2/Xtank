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
import java.util.Random;
import java.util.Scanner;

/*
 * Author : Prasiddhi Gyawali, Ishika Patel
 * Date   : 11/12/22
 * Class  : CSC 335
 * File   : XTankServer.java
 * 
 * Purpose : This class creates the server for players to connect to. A new thread is created
 *           for each player that joins. It also contains a runnable for each thread that is created.
 */
public class XTankServer 
{
	static ArrayList<DataOutputStream> sq;
	static int playerID = 1;

	
    public static void main(String[] args) throws Exception 
    {
		System.out.println(InetAddress.getLocalHost());
		sq = new ArrayList<>();
		
		// runs the server
        try (var listener = new ServerSocket(12345)) 
        {
            System.out.println("The XTank server is running...");
            var pool = Executors.newFixedThreadPool(20);
           
            
            // connects all players to the server for the XTank game
            while (true) 
            {
                Player player = new Player(playerID, 0);
                pool.execute(new XTankManager(listener.accept(), player));
                playerID++;
            }
        }
    }

    /*
     * This is the runnable for the Server which sends and receives
     * information from all the players and ensure that all updates
     * are visible to each player 
     */
    private static class XTankManager implements Runnable 
    {
        private Socket socket;
        private Player currentPlayer;

        /*
         * This is the constructor class for the XTank Server
         */
        XTankManager(Socket socket, Player player) 
        {
            this.socket = socket;
            this.currentPlayer = player;
        }

        /*
         * This method runs if the socket connects to the server. It keeps track of 
         * the information to send to the server and the server responds by updating other sockets
         * and sending it to the current socket with udpates
         */
        @Override
        public void run() 
        {
            System.out.println("Connected: " + socket);
            try 
            {
            	
            	//initializing  input and output to and from the player 
            	DataInputStream in = new DataInputStream(socket.getInputStream());
            	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(in);
                PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true);
                
                sq.add(out);
                int currid = currentPlayer.getID();
                
                // start position of current tank
                Random random = new Random();
                int x = random.ints(50,450).findFirst().getAsInt();
                int y = random.ints(50,450).findFirst().getAsInt();
                int dir = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        
                currentPlayer.setX(x);
                currentPlayer.setY(y);

                outWriter.println("YOURID: " + currid + " X: " + x + " Y: " + y + " D: " + dir);
                while (true)
                {
					if (in.available() > 0) {
                        String info = scanner.nextLine();
                	for (DataOutputStream o: sq)
                	{
                		PrintWriter outWriter2 = new PrintWriter(o, true);
    						outWriter2.println(info);
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