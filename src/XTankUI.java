import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class XTankUI
{
	private int width = 500;
	private int height = 500;
	
	private int max = 100;
	private int min = 400;
	private int startingPositionX;
	private int startingPositionY;
	
	
	// The location and direction of the "tank"
	private Tank player;
	
	private Canvas canvas;
	private Display display;
	
	private Set<Coordinate> filledCoordsMyTank;
	private List<Bullet> bulletsList;
	private Set<Coordinate> filledCoordsBullet;
	private Set<Coordinate> filledCoordsObstacles;
	private Map<Integer, Integer[]> enemyTanks;
	private Set<Coordinate> filledCoordsEnemyTank;
	private List<Bullet> enemyBulletsList;
	
	DataInputStream in; 
	PrintWriter out;
	
	
	/*
	 * Constructor for XTank User Interface
	 */
	public XTankUI(DataInputStream in, DataOutputStream out, int startingPositionX, int startingPositionY, int playerID)
	{
		this.startingPositionX = startingPositionX;
		this.startingPositionY = startingPositionY;
		
		
		
		
		this.in = in;
		this.out = new PrintWriter(out, true);
		
		this.player = new Tank(40, 40, 1, playerID);

		this.enemyTanks = new HashMap<>();
		
		this.bulletsList = new ArrayList<>();
		this.enemyBulletsList = new ArrayList<>();
		this.filledCoordsMyTank = new HashSet<>();
		this.filledCoordsEnemyTank = new HashSet<>();
		this.filledCoordsBullet = new HashSet<>();
		this.filledCoordsObstacles = new HashSet<>();
		
		
		
		
	}
	
	public void start(DataOutputStream out)
	{
		
		display = new Display();
		Shell shell = new Shell(display);
		shell.setText("XTank");
		
		GridLayout gridLayout = new GridLayout();
        shell.setLayout(gridLayout);
        
        shell.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        
        shell.setSize(width, height);
        
        // draws the amount of health left for each client's tank
        Text healthText = new Text(shell, SWT.READ_ONLY | SWT.BORDER);
        healthText.setText("Health: " + player.getHealth());
        healthText.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
        
        Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
        Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
        
        canvas = new Canvas(upperComp, SWT.NONE);
        canvas.setSize(width, height);

		canvas.addPaintListener(event -> {	
			
			// draws your tank in green
			if(player.getHealth() > 0) {
				//event.gc.fillRectangle(canvas.getBounds());
				drawTank(event, shell, player, SWT.COLOR_DARK_GREEN);
				
				this.filledCoordsMyTank.clear();
				
			}

			this.filledCoordsEnemyTank.clear();
			
			
			// draws enemy tanks in a different color 
			for (Integer[] enemyTank : enemyTanks.values()){
			
				drawTank(event, shell, player, SWT.COLOR_DARK_BLUE);
				
				fillCoords(enemyTank[0], enemyTank[1], "Tank");
			}
			
			// displays the bullets shot by your tank in the canvas
			if(player.getHealth()>0) {
				for (int i = 0; i < bulletsList.size(); i++) {
					
					Bullet bullet = bulletsList.get(i);

					event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
					event.gc.fillOval( bullet.getX(), bullet.getY(), 8, 8);
					
					
					
				}
				
			}
			
			
		}	
		);	
		

		canvas.addMouseListener(new MouseListener() {
			public void mouseDown(MouseEvent e) {
				System.out.println("mouseDown in canvas");
			} 
			public void mouseUp(MouseEvent e) {} 
			public void mouseDoubleClick(MouseEvent e) {} 
		});

		canvas.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				
				// update tank location
				int x = player.getX(); 
				int y = player.getY();
				int id = player.getID(); 
				int dir = player.getDirection();
				int offset = player.getWidth();
				if(e.keyCode == 32) {
					
					int[] coords = {0, 0};
					
					if (dir == 0) {
						coords[0] = offset; 
						coords[1] = 0;
					}
					else if (dir == 1) {
						coords[0] = -offset; 
						coords[1] = 0; 
					}
					else if (dir == 2) {
						coords[0] = 0; 
						coords[1] = -offset; 
					}
					else if (dir == 3) {
						coords[0] = 0;
						coords[1] = offset; 
					}
					
					Bullet bullet = new Bullet(x + coords[0]-2, y + coords[1]-2, dir, id); 
					bulletsList.add(bullet);
					
					
					System.out.println("Bullet Fired");
				
					
					
					Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
	                    @Override
	                    public void run() {
	                        Display.getDefault().asyncExec(new Runnable() {
	                        public void run() {
	                        	bullet.move();
	                        	canvas.redraw();
	
	                        	if (bullet.getX() <= -10 || bullet.getX() >= width
	                        			|| bullet.getY() <= -10 || bullet.getY() >= height) {
	                        		bulletsList.remove(bullet);
	                        		timer.cancel();
	                        		canvas.redraw();
	                        	}
	                        } 
	                    });
	                } }, 0, 50);
				} 
				
				else {
					int dX = 0; int dY = 0;
					if (e.keyCode == SWT.ARROW_UP || e.keyCode == 119) {
						dY = -10;
					} if (e.keyCode == SWT.ARROW_DOWN || e.keyCode == 115) {
						dY = 10;
						
					} if (e.keyCode == SWT.ARROW_LEFT || e.keyCode == 97) {
						dX = -10;
						
					} if (e.keyCode == SWT.ARROW_RIGHT || e.keyCode == 100) {
						dX = 10;
					} 
					
					player.moveTank(dX, dY);
					if (player.getX() > width-110) { 
						player.setXY(width-110, player.getY());
					} else if (player.getX() < 75) { 
						player.setXY(75, player.getY());
					}
					
					if (player.getY() > height-130) { 
						player.setXY(player.getX(), height-130);
					} else if (player.getY() < 100) { 
						player.setXY(player.getX(), 100);
					}
					
					canvas.redraw();
					

					
				}
				
				
				

				
			}
			public void keyReleased(KeyEvent e) {}
		});

		System.out.println("testing");				
		Runnable runnable = new Runner(); 
		
		display.asyncExec(runnable);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();		
	}
	
	
	private void drawTank(PaintEvent event, Shell shell, Tank tank, int color) {
		
		int x = tank.getX();
		int y = tank.getY();
		
		if (tank.getDirection() == 0 || tank.getDirection() == 1) {
			
			event.gc.setBackground(shell.getDisplay().getSystemColor(color));
			event.gc.fillRectangle(x-(tank.getHeight()/2), y-(tank.getWidth()/2), tank.getHeight(), tank.getWidth());
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(x-(tank.getWidth()/2), y-(tank.getWidth()/2), tank.getWidth(), tank.getWidth());
			event.gc.setLineWidth(4);
			
			if (tank.getDirection() == 0) 
				event.gc.drawLine(x, y, x+tank.getWidth(), y); 
				
			else 
				event.gc.drawLine(x, y, x-tank.getWidth(), y); 
			
		}
		else if (tank.getDirection() == 2 || tank.getDirection() == 3) {
			
			event.gc.setBackground(shell.getDisplay().getSystemColor(color));
			event.gc.fillRectangle(x-(tank.getWidth()/2), y-(tank.getHeight()/2), tank.getWidth(), tank.getHeight());
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(x-(tank.getWidth()/2), y-(tank.getWidth()/2), tank.getWidth(), tank.getWidth());
			event.gc.setLineWidth(4);
			
			if (tank.getDirection() == 2) 
				event.gc.drawLine(x, y, x, y-tank.getWidth()); 
			
			else
				event.gc.drawLine(x, y, x, y+tank.getWidth()); 
		}
	}
	
	private void fillCoords(int x, int y, String type) {
		
		if(type.equals("Tank")) {
			
			Coordinate toAdd = new Coordinate(x,y);
			filledCoordsEnemyTank.add(toAdd);
		} 
		else if(type.equals("My Tank")) {
			
			Coordinate toAdd = new Coordinate(x,y);
			filledCoordsMyTank.add(toAdd);
		}
		else {
			
			for(int i =x; i <=x+10; i++) {
				for(int j = y ; j <= y+10; j++) {
					Coordinate toAdd = new Coordinate(i,j);
					filledCoordsBullet.add(toAdd);
				}
			}	
		}
		
	}
	
	public String isCollision() {
			
		 	boolean enemyCollision = false;
		 	boolean myCollision = false;
		 	
		 	for(Coordinate bulletCoord: filledCoordsBullet) {
		 		for(Coordinate tankCoord: filledCoordsEnemyTank) {
		 			
		 			if(tankCoord.getCoord()[0] == bulletCoord.getCoord()[0] && tankCoord.getCoord()[1] == bulletCoord.getCoord()[1])
		 				enemyCollision = true;
		 		}
		 	}
		 	
		 	for(Coordinate bulletCoord: filledCoordsBullet) {
		 		for(Coordinate tankCoord: filledCoordsMyTank) {
		 			
		 			if(tankCoord.getCoord()[0] == bulletCoord.getCoord()[0] && tankCoord.getCoord()[1] == bulletCoord.getCoord()[1])
		 				myCollision = true;
		 		}
		 	}
		 	
		 	
		 	if(enemyCollision && myCollision) 
		 		return "both";
		 		
		 	 else if(enemyCollision) 
		 		return "enemy";
		 	
		 	else if (myCollision)
		 		return "mine";
		 	
		 	else 
		 		return "none";
		 	
			
			
		}
	

	
	class Runner implements Runnable
	{
		public void run() 
		{
			
		}
	};	
	
	
	 
	 
	class Coordinate{
		private int coordinates [] = new int [2];
		
		public Coordinate(int x, int y) {
			 this.coordinates[0] = x;
			 this.coordinates[1] = y;
		 }
		
		 
		 public int[] getCoord() {
			 return coordinates; 
		 }
		 
		 public void setCoord(int x, int y) {
			 this.coordinates[0] = x;
			 this.coordinates[1] = y;
		 }
		 
		
	}
}