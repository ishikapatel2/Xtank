import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

public class GameBoard extends JPanel implements ActionListener{

	private DataInputStream in;
	private DataOutputStream out;
	
	private ArrayList<OriginalTank> tanks = new ArrayList<OriginalTank>();
	

	
	
	public GameBoard(DataInputStream in, DataOutputStream out) {
		
		
		this.in = in;
		this.out = out;
		
		setSize(500, 500);
		setBackground(Color.black);
		setFocusable(true);
		setDoubleBuffered(true);
		setVisible(true);

	}
	
	public void addTank(OriginalTank tank) {
		tanks.add(tank);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	


}
