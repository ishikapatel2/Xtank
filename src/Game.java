import java.util.ArrayList;
import java.util.List;

public class Game {
	
    private List<Player> players = new ArrayList<Player>();
    private List<Tank> tanks = new ArrayList<Tank>();

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
