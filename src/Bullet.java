import java.io.Serializable;

public class Bullet implements Serializable{
	private int x;
	private int y;
	private int direction;
	private int playerID;
	
		 
	/*
	 * Constructor for the bullets fired by a tank associated by playerID
	 */
	public Bullet(int x, int y,  int direction, int playerID) {
		this.x = x;
		this.y =y;
		this.playerID = playerID; 
		this.direction = direction;
	}
		 
	/*
	 * Returns x coordinate of location
	 */
	public int getX() {
		return x; 
	}
		
	/*
	 * Returns y coordinate of location
	 */
	public int getY() {
		return y; 
	}
		 
	/*
	 * Returns the player the bullets are shot by 
	 */
	public int getPlayerID() {
		return playerID; 
	}
		 
	/*
	 * moves the bullet across the display from wherever it was fired
	 */
	public void move() {
		if (direction == 0) 
			x += 8; 
		else if (direction == 1)
			x -= 8; 
		else if (direction == 2)  
			y -= 8; 
		else if (direction == 3)
			y += 8; 
	}
		 
}