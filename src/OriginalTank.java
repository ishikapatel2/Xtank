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
	private double velx;
	private double vely;
	private Timer t = new Timer();
	
	private boolean status;

	public OriginalTank(String name) {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		status = true;
		this.player = name;
		
		x = 0.0;
		y = 0.0;
		
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D oval = new Ellipse2D.Double(x, y+25, 30, 30);
		g2.setColor(new Color(125, 167, 116));
		g2.fill(new Rectangle2D.Double(x, y, 40, 60));
		
		g2.setColor(new Color(10, 15, 90, 60));
		g2.fill(new Ellipse2D.Double(x, y+25, 30, 30));
		
	
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		x += velx;
		y+= vely;
		
	}
	
	public void up() {
		vely = -1.5;
		velx = 0;
		
	
	}
	
	public void down() {
		vely = 1.5;
		velx = 0;
	}
	
	public void right() {
		vely = 0;
		velx = 1.5;
	}
	
	public void left() {
		velx = -1.5;
		vely = 0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_DOWN) {
			down();
		}

		if (code == KeyEvent.VK_UP) {
			up();
		}
		
		if (code == KeyEvent.VK_RIGHT) {
			right();
		}
		
		if (code == KeyEvent.VK_LEFT) {
			left();
		}
		
	}
	
	/*
	 * sets the location of tank
	 */
	public void setCoords(double x, double y) {
		this.x = x;
		this.x = y;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
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
	
	
	



	
	
	



}
