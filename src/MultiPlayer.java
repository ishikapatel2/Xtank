import java.util.ArrayList;

public class MultiPlayer {

	ArrayList<Player> players = new ArrayList<Player>();
	
	
	public MultiPlayer(Player player) {
		players.add(player);
	}
	
	
	
	public ArrayList<Player> getPlayersList(){
		return players;
	}
	
	public void clear() {
		players = new ArrayList<Player>();
	}

}
