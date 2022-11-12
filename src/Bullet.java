import java.io.Serializable;

public class Bullet implements Serializable{
	 private int x;
	 private int y;
	 private int id;
	 private int direction;
	 
	
	 public Bullet(int x, int y, int id, int direction) {
		 this.x = x;
		 this.y =y;
		 this.id = id; 
		 this.direction = direction;
	 }
	 
	 public int getX() {
		 return x; 
	 }
	 
	 public int getY() {
		 return y; 
	 }
	 
	 public int getId() {
		 return id; 
	 }

	 public int getDirection() {
		 return direction; 
	 }

	 public void setDirection(int direction) {
		 this.direction = direction;
		 if (direction == 0) {
			 this.y = this.y - 10;
		 }
		 else if (direction == 1) {
			this.y = this.y + 150;
		 }
		 else if (direction == 2) {
			 this.y = this.y + 75;
			 this.x = this.x - 50;
		 }
		 else if (direction == 3) {
			this.y = this.y + 75;
			this.x = this.x + 50;
		 } 
	 }
	 
	 public void incrementY() {
		 // based on direction change x and y	
		 if (direction == 0) {
			 y -= 10;
		 }
		 else if (direction == 1) {
			 y += 10;
		 }
		 else if (direction == 2) {
			 x -= 10;
		 }
		 else if (direction == 3) {
			 x += 10;
		 }

	 }
		 
}