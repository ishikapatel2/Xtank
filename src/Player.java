import java.io.DataInputStream;
import java.io.DataOutputStream;


public class Player
{
    private int score;
    private int playerID;
    private int x;
    private int y;

    public Player(int id, int score) 
    {

        this.score = score;
        this.playerID = id;
    }
    
    public int getScore() {
    	return score; 
    }
    
    public int getID() { 
    	return playerID; 
    }
    
    public int getX() { 
    	return x; 
    }
    
    public int getY() {
    	return y; 
    }
    
    public void setX(int x) { 
    	this.x = x; 
    }
    
    public void setY(int y) { 
    	this.y = y; 
    }

}
