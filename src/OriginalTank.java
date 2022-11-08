import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Timer;

import javax.swing.JPanel;

public class OriginalTank extends JPanel implements ActionListener, KeyListener  {
	
	private String player;
	
	private double x;
	private double y;
	private Timer t = new Timer();
	
	private boolean status;

	public OriginalTank(String name) {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		status = true;
		this.player = name;
		
		x = 0;
		y = 0;
		
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rectangle = new Rectangle2D.Double(x, y, 40, 90);
		Ellipse2D oval = new Ellipse2D.Double(x, y+25, 50, 50);
		g2.setColor(new Color(125, 167, 116));
		g2.fillRect(10, 10, 90, 60);
		
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public String getPlayer() {
		return player;
	}
	
	/*
	 * indicates if the tank/player is still in the game
	 */
	public void setStatus() {
		this.status = false;
	}
	
	/*
	 * returns the state of the tank/player (alive or dead)
	 */
	public boolean getStatus() {
		return status;
	}
	
	/*
	 * sets the location of tank
	 */
	public void setCoords(double x, double y) {
		xCoord = x;
		yCoord = y;
	}
	
	/*
	 * returns x coordinate
	 */
	public double getX() {
		return xCoord;
	}
	
	/*
	 * returns y coordinate
	 */
	public double getY() {
		return yCoord;
	}



	
	
	



}
