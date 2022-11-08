/*
 * 
 *Abstract class that is implemented by other tank types
 *
 *Not being implmeneted until everything works 
 * 
 */

public abstract class TankModels {
	
	private OriginalTank tank;
	
	public TankModels(String tankModel, String player) {
		if (tankModel == "OriginalTank") {
			tank = new OriginalTank(player);
		}
	}
	 

}
