import java.io.Serializable;

public class Tank implements Serializable{
	
	private int x;
	private int y; 
	private int direction; 
	private int playerID;
	private int healthStatus;
	
	private int width;
	private int height;
	
	/*
	 * Constructor for each Tank object created
	 */
	public Tank(int initX, int initY, int initDirection, int playerID) {
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
	 * Moves the tank
	 */
	public void moveTank(int moveX, int moveY) {
		int movement = (int) Math.sqrt((moveX * moveX) + (moveY * moveY));
		x += moveX/movement * 5;
		y += moveX/movement * 5;
		
		if (moveX > 0) 
			direction = 0;
		
		else if (moveX < 0) 
			direction = 1;
		
		else if (moveY < 0) 
			direction = 2;
		
		else if (moveY > 0) 
			direction = 3;
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
	 * sets position of tank to a new location
	 */
	public void setXY (int x, int y) {
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
		if (healthStatus == 0) 
			System.out.println("Player " + playerID + " has died");
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