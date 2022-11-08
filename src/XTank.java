
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
/*
 * Client class
 */


public class XTank {
	
	private JFrame frame;
	private JLabel messageLabel;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	private GameBoard gameBoard;
	
	
	public XTank(String serverAddress) throws Exception {
		// creates new socket 
		socket = new Socket(serverAddress, 12345);
		frame = new JFrame("XTank");
		
		// takes input from the socket
		in = new DataInputStream(socket.getInputStream());
		
		// sends output to the socket 
        out = new DataOutputStream(socket.getOutputStream());
		
		// creates the UI for all players 
        gameBoard = new GameBoard(in, out);
		frame.add(gameBoard);
		frame.getContentPane().add(gameBoard, BorderLayout.CENTER);
		
		// message label that tells all players whose turn it is  
		messageLabel = new JLabel("...");
		messageLabel.setBackground(Color.lightGray);
        frame.getContentPane().add(messageLabel, BorderLayout.SOUTH);
        
        OriginalTank tank = new OriginalTank("P1");
        gameBoard.addTank(tank);

	}
	
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}

	 
	
	private void play() throws Exception {
		System.out.println("Playing");
		
	}
	
	public static void main(String[] args) throws Exception 
    {
		
		XTank client = new XTank("127.0.0.1");
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setBackground(Color.black);
        client.frame.setSize(500, 500);
        client.frame.setVisible(true);
        client.frame.setResizable(false);
        client.frame.setLocationRelativeTo(null);
        client.play();

    }






	
}


