import java.util.Arrays;

/*
 * Author : Prasiddhi Gyawali, Ishika Patel
 * Date   : 11/12/22
 * Class  : CSC 335
 * File   : Coordinate.java
 * 
 * Purpose : This class creates creates the x and y locations as coordinates  
 */
public class Coordinate {
	private int coordinates [] = new int [2];
	
	/* This is the constructor that accepts an x,y location
	 * 
	 */
	public Coordinate(int x, int y) {
		 this.coordinates[0] = x;
		 this.coordinates[1] = y;
	 }
	
	 
	/*
	 * This methods gets the coordinates
	 */
	 public int[] getCoord() {
		 return coordinates; 
	 }
	 
	 /*
	  * This method sets the coordinate 
	  */
	 public void setCoord(int x, int y) {
		 this.coordinates[0] = x;
		 this.coordinates[1] = y;
	 }
	 
	 @Override
	 public int hashCode() {
		 
		    int result = Arrays.hashCode(coordinates);
		    return result;
	 }

	 @Override 
	 public boolean equals(Object o) {
		 
		 
		 if(!(o instanceof Coordinate)) {
			 return false;
		 }
		 
		 Coordinate x = (Coordinate) o;
	
		 if(this.coordinates[0] == x.getCoord()[0] && 
				 this.coordinates[1] == x.getCoord()[1]) {
			 return true;
		 }
		
		 return false;
	 }

}
