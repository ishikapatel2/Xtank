import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;  
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class XTankUI
{
	private Canvas canvas;
	private Display display;
	
	// canvas dimensions
	private int width = 600;
	private int height = 600;
	// The location and direction of the "tank"
	private int x = 300;
	private int y = 500;
	
	private int health = 5;
	private int id;
	private Map<Integer, Integer[]> enemyTanks;
	private int directionX = 0;
	private int directionY = 0;
	
	private List<Bullet> bulletsList;
	private List<Bullet> enemyBulletsList;
	private Set<Coordinate> filledCoordsMyTank;
	private Set<Coordinate> filledCoordsEnemyTank;
	private Set<Coordinate> filledCoordsBullet;
	private Set<Coordinate> filledCoordsObstacles;
	
	// tank direction
	private int tankDirection = 0; // 0 = up, 1 = right, 2 = down, 3 = left
	
	DataInputStream in; 
	PrintWriter out;
	
	public XTankUI(DataInputStream in, DataOutputStream out)
	{
		this.in = in;
		this.out = new PrintWriter(out, true);
		this.id = 0;
		this.enemyTanks = new HashMap<>();
		this.bulletsList = new ArrayList<>();
		this.enemyBulletsList = new ArrayList<>();
		this.filledCoordsMyTank = new HashSet<>();
		this.filledCoordsEnemyTank = new HashSet<>();
		this.filledCoordsBullet = new HashSet<>();
		this.filledCoordsObstacles = new HashSet<>();
		this.tankDirection = 0;

	}

	private int getBulletDirection(Bullet bullet) {
		
		// look at the bullet's id and return the direction of the tank that fired it
		int id = bullet.getId();
		// check the current tank first
		if(id == this.id) {
			return tankDirection;
		}
		// check the other tanks
		for(Integer key : enemyTanks.keySet()) {
			if(key == id) {
				return enemyTanks.get(key)[2];
			}
		}
		return -1;
	}
	
	private void fillCoords(int x, int y, String type) {
		
		if(type.equals("Tank")) {
			for(int i =x; i <=x+50; i++) {
				for(int j = y ; j <= y+100; j++) {
				Coordinate toAdd = new Coordinate(i,j);
				filledCoordsEnemyTank.add(toAdd);
			}}
		} else if(type.equals("My Tank")) {
			for(int i =x; i <=x+50; i++) {
				for(int j = y ; j <= y+100; j++) {
				Coordinate toAdd = new Coordinate(i,j);
				filledCoordsMyTank.add(toAdd);
			}}
			
		} else if(type.equals("Obstacle1")) {
			for(int i =x; i <=x+50; i++) {
				for(int j = y ; j <= y+200; j++) {
				Coordinate toAdd = new Coordinate(i,j);
				filledCoordsObstacles.add(toAdd);
			}}
		}
		else if(type.equals("Obstacle2")) {
			for(int i =x; i <=x+300; i++) {
				for(int j = y ; j <= y+50; j++) {
				Coordinate toAdd = new Coordinate(i,j);
				filledCoordsObstacles.add(toAdd);
			}}
		}
		else {
			for(int i =x; i <=x+10; i++) {
				for(int j = y ; j <= y+10; j++) {
				Coordinate toAdd = new Coordinate(i,j);
				filledCoordsBullet.add(toAdd);
			}}	
		}
		
	}
	
	/*
	 * This method keeps track of the bullet collisions into 
	 * curent player and enemies. 
	 * If bullet collides with current player, return 0
	 * If bullet collides with enemy player, return 1
	 * If bullet collides with both enemy player and current player, return -1
	 */
	public String isBulletCollision() {
			
		 	boolean enemyCollision = false;
		 	boolean myCollision = false;
		 	
		 	for(Coordinate bulletCoord: filledCoordsBullet) {
		 		if(filledCoordsEnemyTank.contains(bulletCoord) ) {
		 			{enemyCollision = true;
		 			}
		 		}
		 		if(filledCoordsMyTank.contains(bulletCoord) ) {
		 			{myCollision = true;}
		 		}
		 		
		 	}
		 	
		 	
		 	if(enemyCollision && myCollision) {
		 		return "both";
		 	} else if(enemyCollision) {
		 		return "enemy";
		 	}else if (myCollision){
		 		return "mine";
		 	}else {
		 		return "none";
		 	}
			
			
		}
	
	public String isObstacleCollision() {
			
			boolean tankCollision = false;
			boolean bulletCollision = false;
			
	 		for(Coordinate tankCoord: filledCoordsMyTank) {	
	 			if(filledCoordsObstacles.contains(tankCoord))
	 			{
	 			   System.out.println(tankCoord.getCoord()[0] + " " + tankCoord.getCoord()[1]);
	 		       tankCollision = true;
	 		       break;
	 			}
	 		}
	 		
	 		for(Coordinate bulletCoord: filledCoordsBullet) {	
	 			if(filledCoordsObstacles.contains(bulletCoord))
	 			{
	 				bulletCollision = true;
		 		       break;
	 			}
	 		}
	 		
	 	if(tankCollision && bulletCollision) {
	 		return "both";
	 	}else if(tankCollision) {
	 		return "tank";
	 	}else if(bulletCollision) {
	 		return "bullet";
	 	}
	 	return "none";
		
		
	}
	
	/*
	 * Draws your tank on the canvas depending on the direction it is facing
	 */
	public void drawYourTank(PaintEvent event, Shell shell) {
		if(tankDirection == 0) {
			// up
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
			event.gc.fillRectangle(x-(50/2), y-(100/2), 50, 100);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(x-(50/2), y-(50/2), 50, 50);
			event.gc.setLineWidth(4);
			event.gc.drawLine(x, y, x, y-70); 
		} else if(tankDirection == 1) {
			// down
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
			event.gc.fillRectangle(x-(50/2), y-(100/2), 50, 100);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(x-(50/2), y-(50/2), 50, 50);
			event.gc.setLineWidth(4);
			event.gc.drawLine(x, y, x, y+70); 
		} else if(tankDirection == 2) {
			// left
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
			event.gc.fillRectangle(x-(100/2), y-(50/2), 100, 50);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(x-(50/2), y-(50/2), 50, 50);
			event.gc.setLineWidth(4);
			event.gc.drawLine(x, y, x-70, y);
		} else if(tankDirection == 3) {
			// right
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
			event.gc.fillRectangle(x-(100/2), y-(50/2), 100, 50);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(x-(50/2), y-(50/2), 50, 50);
			event.gc.setLineWidth(4);
			event.gc.drawLine(x, y, x+70, y);
		}
		
		fillCoords(x,y, "My Tank");
		
	}
	
	/*
	 * Draws the enemy tanks on the canvas depending on the direction it is facing
	 */
	public void drawEnemyTank( PaintEvent event, Shell shell, Integer[] enemyTank) {
		if(enemyTank[2] == 0) {
			// up
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
			event.gc.fillRectangle(enemyTank[0]-(50/2), enemyTank[1]-(100/2), 50, 100);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(enemyTank[0]-(50/2), enemyTank[1]-(50/2), 50, 50);
			event.gc.setLineWidth(4);
			event.gc.drawLine(enemyTank[0], enemyTank[1], enemyTank[0], enemyTank[1]-70); 
			
		} else if(enemyTank[2] == 1) {
			// down
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
			event.gc.fillRectangle(enemyTank[0]-(50/2), enemyTank[1]-(100/2), 50, 100);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(enemyTank[0]-(50/2), enemyTank[1]-(50/2), 50, 50);
			event.gc.setLineWidth(4);
			event.gc.drawLine(enemyTank[0], enemyTank[1], enemyTank[0], enemyTank[1]+70); 

		} else if(enemyTank[2] == 2) {
			// left
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
			event.gc.fillRectangle(enemyTank[0]-(100/2), enemyTank[1]-(50/2), 100, 50);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(enemyTank[0]-(50/2), enemyTank[1]-(50/2), 50, 50);
			event.gc.setLineWidth(4);
			event.gc.drawLine(enemyTank[0], enemyTank[1], enemyTank[0]-70, enemyTank[1]);
			
			
		} else if(enemyTank[2] == 3) {
			// right 
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
			event.gc.fillRectangle(enemyTank[0]-(100/2), enemyTank[1]-(50/2), 100, 50);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(enemyTank[0]-(50/2), enemyTank[1]-(50/2), 50, 50);
			event.gc.setLineWidth(4);
			event.gc.drawLine(enemyTank[0], enemyTank[1], enemyTank[0]+70, enemyTank[1]);
		}
		
		fillCoords(enemyTank[0], enemyTank[1], "Tank");
	}
	
	public void start()
	{
		
		display = new Display();
		Shell shell = new Shell(display);
		shell.setText("XTank");

		
		GridLayout gridLayout = new GridLayout();
        shell.setLayout( gridLayout);
        
        shell.setSize(width,height);
        
        Text healthText = new Text(shell, SWT.READ_ONLY | SWT.BORDER);
        healthText.setText("Health: " + health);
        healthText.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
        
        Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
        Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);


        canvas = new Canvas(upperComp, SWT.NONE);
        canvas.setSize(width,height); 

	

		canvas.addPaintListener(event -> {	
			
			event.gc.fillRectangle(canvas.getBounds());
			this.filledCoordsMyTank.clear();
			
			
			// draws your tank to the canvas
			if(health>0) {
				drawYourTank(event, shell);
				
				for (int i = 0; i < bulletsList.size(); i++) {
					
					Bullet bullet = bulletsList.get(i);
					this.filledCoordsBullet.clear();
					fillCoords(bullet.getX(), bullet.getY(), "Bullet");

					// if the bullet is out of bounds, remove it
					if(bullet.getX() < 0 || bullet.getX() > 800 || bullet.getY() < 0 || bullet.getY() > 650) {	
						bulletsList.remove(i);
					}
					
					else if((!(isBulletCollision().equals("none"))) || isObstacleCollision().equals("bullet")  
							|| isObstacleCollision().equals("both")) {
						bulletsList.remove(i);
					} 
					
					else {
						event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
						event.gc.fillOval( bullet.getX(), bullet.getY(), 8, 8);
		
					}
					
					
					
				}
				
				
				
				
			}
			
	
			
			// draws all enemy tanks to the canvas
			this.filledCoordsEnemyTank.clear();
			
			for (Integer[] enemyTank : enemyTanks.values())
			{
				drawEnemyTank( event, shell, enemyTank);
				for (int i = 0; i < enemyBulletsList.size(); i++) {
					
					Bullet bullet = enemyBulletsList.get(i);
					this.filledCoordsBullet.clear();
					fillCoords(bullet.getX(), bullet.getY(), "Bullet");
					// if the bullet is out of bounds, remove it
					if(bullet.getX() < 0 || bullet.getX() > 800 || bullet.getY() < 0 || bullet.getY() > 650) {
						enemyBulletsList.remove(i);
					}
					
					else if((isBulletCollision().equals("mine") || isBulletCollision().equals("both") ))  {
						health--;
						healthText.setText("Health: "+health);
						enemyBulletsList.remove(i);
					} 
					
					else if(!(isBulletCollision().equals("none")) || isObstacleCollision().equals("bullet") || 
							isObstacleCollision().equals("both")) {
						enemyBulletsList.remove(i);
					}
					
					else {
						event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLUE));
						event.gc.fillOval( bullet.getX(), bullet.getY(), 8, 8);

						
						if(health==0) {
							healthText.setText("GAME OVER");
							out.println("REMOVE: "+this.id + " X: -100 Y: -100 D: -1");
							
							
						}
					}

				}
			}

	
		}	
		);	
		

		canvas.addMouseListener(new MouseListener() {
			public void mouseDown(MouseEvent e) {} 
			public void mouseUp(MouseEvent e) {} 
			public void mouseDoubleClick(MouseEvent e) {} 
		});

		
		// if the space bar is pressed, bullets are fired from player's tank
		// shots can be fired in any direction (up/down/right/left)
		canvas.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				System.out.println("key " + e.keyCode + " pressed");
				
				if(health > 0) {
					
					if(e.keyCode == 32) {
						System.out.println("testing");
						 
						int offset = 50;
						int[] coords = {0, 0};
						if (tankDirection == 0) { 
							coords[0] = -4; coords[1] = -10; 
						}
						else if (tankDirection == 1) { 
							coords[0] = +0; coords[1] = offset -100; 
						}
						else if (tankDirection == 2) { 
							coords[0] = +20; coords[1] = -offset-30;
						}
						else if (tankDirection == 3) { 
							coords[0] = -3; coords[1] = -offset-30;
						}
						
						
						// new bullet is created and added to bullet list that tracks bullets fired on the canvas
						Bullet bullet = new Bullet(x+coords[0], y + coords[1], id, tankDirection);
						int bulletDir = getBulletDirection(bullet);
						bullet.setDirection(bulletDir);
						bulletsList.add(bullet);
						
						try {
							out.println("BULLET: "+bullet.getId() + " X: " + bullet.getX() + " Y: " + bullet.getY() + " D: " + bulletDir);
						}
						catch(Exception ex) {
							System.out.println("The server did not respond (write KL).");
						}
						
						Timer timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
		                    @Override
		                    public void run() {
		                        Display.getDefault().asyncExec(new Runnable() {
		                            public void run() {
		                            	
		                            	bullet.move();
										canvas.redraw();
		
										if(bullet.getY() <= -10) {
											bulletsList.remove(bullet);
											timer.cancel();
											canvas.redraw();
										}
		                            }
		                        });
		                    }
		                },0,60);

						
					} 

					if (e.keyCode == SWT.ARROW_UP) {
						
							directionX = 0;
							directionY = -10;
							x += directionX;
							y += directionY;
							tankDirection = 0;
							
							filledCoordsMyTank.clear();
							fillCoords(x,y, "My Tank");
							

						
					} else if (e.keyCode == SWT.ARROW_DOWN) {
						
						directionX = 0;
						directionY = 10;
						x += directionX;
						y += directionY;
						tankDirection = 1;
						
						filledCoordsMyTank.clear();
						fillCoords(x,y, "My Tank");

						
						
					} else if (e.keyCode == SWT.ARROW_LEFT) {
						
						directionX = -10;
						directionY = 0;
						x += directionX;
						y += directionY;
						tankDirection = 2;
						
						filledCoordsMyTank.clear();
						fillCoords(x,y, "My Tank");

						
						
					} else if (e.keyCode == SWT.ARROW_RIGHT) {
				
						directionX = 10;
						directionY = 0;
						x += directionX;
						y += directionY;
						tankDirection = 3;
						
						filledCoordsMyTank.clear();
						fillCoords(x,y, "My Tank");

						
					} 


					try {
						
						out.println("ID: " + id + " X: " + x + " Y: " + y + " D: " + tankDirection);
					}
					catch(Exception ex) {
						System.out.println("");
					}
					
					canvas.redraw();}

				
			}
			public void keyReleased(KeyEvent e) {}
		});
			
		Runnable runnable = new Runner();
		display.asyncExec(runnable);
		shell.open();
		while (!shell.isDisposed()) 
			if (!display.readAndDispatch())
				display.sleep();

		display.dispose();		
	}
	
	class Runner implements Runnable
	{
		public void run() 
		{
							
			try {
				if (in.available() > 0) {
					Scanner scanner = new Scanner(in);
					String info = scanner.nextLine();
					if (info != "") {
						System.out.println(info);
						
						// updates tank locations of the the enemy and current player
						String[] updatedInfo = info.split(" ");
						String iD = updatedInfo[0];
						int indentifier = Integer.parseInt(updatedInfo[1]);
						int newX = Integer.parseInt(updatedInfo[3]);
						int newY = Integer.parseInt(updatedInfo[5]);
						int newDir = Integer.parseInt(updatedInfo[7]);
						
						// updates your location if you moved
						if (iD.equals("YOURID:"))
						{
							id = indentifier;
							x = newX;
							y = newY;
							tankDirection = newDir;
							
							filledCoordsMyTank.clear();
							fillCoords(newX, newY, "My Tank");
							
							while(isObstacleCollision().equals("tank") || 
									isObstacleCollision().equals("both")) {
								
								x = (int)(Math.random()*500);
								y = (int)(Math.random()*500);
								
								filledCoordsMyTank.clear();
								fillCoords(x, y, "My Tank");
							}
							
							
							canvas.redraw();
							
						// updates enemy location if enemy moved
						}
						else if (iD.equals("ID:") && id != indentifier)
						{
							enemyTanks.put(indentifier, new Integer[] {newX, newY, newDir});
							System.out.println("Enemy count: " + enemyTanks.size());
							canvas.redraw();
						}
						
						// updates bullet location if bullet moved 
						else if (iD.equals("BULLET:") && id != indentifier)
						{
							Bullet bullet = new Bullet(newX, newY, indentifier, newDir);
							enemyBulletsList.add(bullet);
							
							Timer t = new Timer();
							t.scheduleAtFixedRate(new TimerTask() {
			                    @Override
			                    public void run() {
			                        Display.getDefault().asyncExec(new Runnable() {
			                            public void run() {
			                            	
			                            	bullet.move();
											canvas.redraw();
									
											if(bullet.getY() <= -10) {
												enemyBulletsList.remove(bullet);
												t.cancel();
												canvas.redraw();
											}
			                            }
			                        });
			                    }
			                },0,50);
							
							canvas.redraw();
						}
						
						// Removes tank if they have lost all health
						else if(iD.equals("REMOVE:") && id != indentifier ) {
							
							enemyTanks.remove(indentifier);
							canvas.redraw();
						}
					}
				}
			}
			catch(IOException ex) {
				System.out.println(ex);
			}				
            display.timerExec(1, this);
			display.asyncExec(this);
		}
	};	
	
	
	 
		
	
	 
	
}