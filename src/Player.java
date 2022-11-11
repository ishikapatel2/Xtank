import java.io.DataInputStream;
import java.io.DataOutputStream;


public class Player
{
	private String name;
	private int playerID;
	private int x;
    private int y;
    private int score;
	DataInputStream input;
	DataOutputStream output;
	
	/*
	 * Constructor for each player which initializes its components
	 */
	public Player(String name, int playerID) {
		this.name = name;
		this.playerID = playerID;
		this.score = 0;
	}
	
	/*
	 * Returns the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Returns the player ID (unique to each player)
	 */
	public int getPlayerID() {
		return playerID;
	}
	
	/*
	 * Returns the current score for the player
	 */
	public int getScore() {
		return this.score;
	}
	
	/*
	 * sets the inputData
	 */
	public void setInput(DataInputStream input) { 
		this.input = input;
	}
	
	/*
	 * sets the OutputData
	 */
	public void setOutput(DataOutputStream output) {
		this.output = output;
	}
	
	/*
	 * gets the inputData
	 */
	public DataInputStream getInput() {
		return input;
	}
	
	/*
	 * gets the outputData
	 */
	public DataOutputStream getOutput() {
		return output;
	}

	/*
	 * Returns x coordinate of player
	 */
	public int getX() {
		return x;
	}

	/*
	 * Sets current x coordinate of player
	 */
	public void setX(int x) {
		this.x = x;
	}

	/*
	 * Returns y coordinate of player
	 */
	public int getY() {
		return y;
	}
	

	/*
	 * Sets the y coordinate of player
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/*
	 * Sets x and y coordinate of player
	 */
	public void setXY (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
