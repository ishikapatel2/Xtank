import java.util.ArrayList;
import java.util.List;

public class Game {
	
    private List<Player> players = new ArrayList<Player>();
    private List<Tank> tanks = new ArrayList<Tank>();
    private Player player1 = null;
    private Player player2 = null;
    private Player player3 = null;
    private Player player4 = null;
    private Player currentPlayer = null;
    
    public Player getCurrentPlayer() {
    	return currentPlayer;
    	}
    
    public void setCurrentPlayer(Player player) {
    	currentPlayer = player;
    	}
    
    public Player getPlayer1(){
    	return player1;}
    
    public void setPlayer1(Player player) {
    	player1 = player;
    	}
    
    public Player getPlayer2(){
    	return player2;
    	}
    
    public void setPlayer2(Player player) {
    	player2= player;
    	}
    
    public Player getPlayer3(){
    	return player3;}
    
    public void setPlayer3(Player player) {
    	player3 = player;
    	}
    
    public Player getPlayer4(){
    	return player4;
    	}
    
    public void setPlayer4(Player player) {
    	player4= player;
    	}

    /*
     * Adds a player to the game
     */
    public void addPlayer(Player player) 
    {
        players.add(player);
    }

    
    /*
     * Removes a player from the game
     */
    public void removePlayer(Player player) 
    {
        players.remove(player);
    }

    /*
     * Returns the list of all players 
     */
    public List<Player> getPlayers() 
    {
        return players;
    }
    
    /*
     * Adds tank to the game
     */
    public void addTank(Tank tank) {
    	tanks.add(tank);
    }
    
    /*
     * Removes tank from the game
     */
    public void removeTank(Tank tank) {
    	tanks.remove(tank);
    }

    /*
     * Returns list of tanks still in the game
     */
	public List<Tank> getTanks() {
		return tanks;
	}
	
	
    
    
	
}
