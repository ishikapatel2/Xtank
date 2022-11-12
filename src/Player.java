import java.io.DataInputStream;
import java.io.DataOutputStream;
/*
 * Author : Prasiddhi Gyawali, Ishika Patel
 * Date   : 11/12/22
 * Class  : CSC 335
 * File   : XTankServer.java
 * 
 * Purpose : This class creates a player object for the Sever to keep track of a players
 *           location on the canvas, their score, and their ID which is unique to each player
 *           to send and receive information from the server.
 */

public class Player
{
    private int score;
    private int playerID;
    private int x;
    private int y;

    /*
     * Constructor for the Player class which initializes the ID and starting score.\
     */
    public Player(int id, int score) 
    {

        this.score = score;
        this.playerID = id;
    }
    
    /*
     * Returns the score for this player
     */
    public int getScore() {
    	return score; 
    }
    
    /*
     * Returns the unique ID
     */
    public int getID() { 
    	return playerID; 
    }
    
    /*
     * Returns the x coordinate of player's location
     */
    public int getX() { 
    	return x; 
    }
    
    /*
     * Returns the y coordinate of the player's location
     */
    public int getY() {
    	return y; 
    }
    
    /*
     * Sets the x coordinate of the player's location
     */
    public void setX(int x) { 
    	this.x = x; 
    }
    
    /*
     * Sets the x coordinate of the player's location
     */
    public void setY(int y) { 
    	this.y = y; 
    }

}
