import java.io.Serializable;

import org.eclipse.swt.SWT;

/*
 * Author : Prasiddhi Gyawali, Ishika Patel
 * Date   : 11/12/22
 * Class  : CSC 335
 * File   : Tank.java
 * 
 * Purpose : This class the tank object for XTank UI to keep track of. 
 *            
 */
public class Tank implements Serializable{
	
	
	private int x;
	private int y; 
	private int direction; 
	private int playerID;
	private int healthStatus;
	
	// size of tank
	private int width;
	private int height;
	
	/*
	 * Constructor for each Tank object created
	 */
	public Tank(int initX, int initY, int initDirection, int playerID, int tankModel) {
		this.x = initX;
		this.y = initY;
		this.direction = initDirection;
		this.healthStatus = 5;
		this.playerID = playerID;
		
		// size of each tank created
		this.width = 50;
		this.height = 60;
	}

	/*
	 * This method sets the direction of the tank
	 * it moves in
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
	/*
	 * This method moves the tank
	 */
	public void moveTank(int moveX, int moveY) {
		if (moveX > 0) 
			direction = 0;
		
		else if (moveX < 0) 
			direction = 1;
		
		else if (moveY < 0) 
			direction = 2;
		
		else if (moveY > 0) 
			direction = 3;
		
		this.setXY(moveX, moveY);
	}
	
	/*
	 * Gets x coordinate of tank
	 */
	public int getX() {
		return x;
	}
	
	/*
	 * Gets y coordinate of tank
	 */
	public int getY() {
		return y;
	}
	
	/*
	 * This set the x coordinate
	 */
	public void setX (int x) {
		this.x = x;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
	/*
	 * sets position of tank to a new location
	 */
	private void setXY (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Returns the direction the tank is moving in
	 */
	public int getDirection() {
		return direction;
	}
	
	/*
	 * Keeps track of each players health status
	 */
	public int getHealth() {
		return healthStatus;
	}

	
	
	/*
	 * Returns player ID (unique to every player in the server)
	 */
	public int getID() {
		return playerID;
	}
	
	/*
	 * size of tank
	 */
	public int getWidth() {
		return width;
	}
	
	/*
	 * size of tank
	 */
	public int getHeight() {
		return height;
	}

}