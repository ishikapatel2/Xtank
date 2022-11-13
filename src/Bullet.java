import java.io.Serializable;
/*
 * Author : Prasiddhi Gyawali, Ishika Patel
 * Date   : 11/12/22
 * Class  : CSC 335
 * File   : Bullet.java
 * 
 * Purpose : This class creates the weapon that each player uses
 *           during the game 
 *            
 */
public class Bullet implements Serializable{
	 private int x;
	 private int y;
	 private int id;
	 private int direction;
	 
	/*
	 * This is the constructor that saves the location of the bullet and
	 * tracks which player shot it and in which direction
	 */
	 public Bullet(int x, int y, int id, int direction) {
		 this.x = x;
		 this.y =y;
		 this.id = id; 
		 this.direction = direction;
	 }
	 
	 /*
	  * This method increments the bullet location. It mimics
	  * the movement of a bullet
	  */
	 public void move() {	
		 if (direction == 0) {
			 y -= 8;
		 }
		 else if (direction == 1) {
			 y += 8;
		 }
		 else if (direction == 2) {
			 x -= 8;
		 }
		 else if (direction == 3) {
			 x += 8;
		 }

	 }
	 
	 /*
	  * Returns the X coordinate of bullet's location
	  */
	 public int getX() {
		 return x; 
	 }
	 
	 /*
	  * Returns the Y coordinate of bullet's location
	  */
	 public int getY() {
		 return y; 
	 }
	 
	 /*
	  * Returns the ID of the tank that shot these bullets.
	  */
	 public int getId() {
		 return id; 
	 }

	 /*
	  * Returns the direction the bullet is moving in.
	  */
	 public int getDirection() {
		 return direction; 
	 }

	 /*
	  * Returns sets the direction the bullet moves in
	  */
	 public void setDirection(int direction) {
		 this.direction = direction;
		 if (direction == 0) {
			 y -= 10;
		 }
		 else if (direction == 1) {
			y += 150;
		 }
		 else if (direction == 2) {
			x -= 50;
			y += 75;
			
		 }
		 else if (direction == 3) {
			x += 50;
			y += 75;
			
		 } 
	 }
	 

		 
}