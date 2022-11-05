
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;


public class XTank {
	
	private JFrame frame;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	private GameBoard gameBoard;
	
	
	
	public XTank(String serverAddress) throws Exception {
		frame = new JFrame("XTank");
		socket = new Socket(serverAddress, 12345);
		
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        
        setGameBoard(new GameBoard());
	}
	
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	 
	
	private void play() throws Exception {
		// TODO Auto-generated method stub
		
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
        
        
        try (var socket = new Socket("127.0.0.1", 59896)) 
        {
        	DataInputStream in = new DataInputStream(socket.getInputStream());
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            var ui = new XTankUI(in, out);
            ui.start();
        }

    }






	
}


