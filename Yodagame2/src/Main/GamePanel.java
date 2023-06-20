package Main;

import tile.TileManager;
import object.SuperObject;

import java.awt.Color;

import Entity.Player;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int originalTileSize = 16;
	final int scale = 3;
	
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//Maptravel
	public int mapNummer = 0;
	public int sleepable = 7200/4;
	
	public boolean schlafen = false;
	public boolean gewonnen = false;
	//FPS
	int FPS = 60;
	
	BufferedImage lastpic;
	public TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public GegenschlagsBeobachter cChecker = new GegenschlagsBeobachter(this);
	public ObjektSetzer aSetzer = new ObjektSetzer(this);
	public UI ui = new UI(this);
	Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[30];
	
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
try {
			
			lastpic = ImageIO.read(getClass().getResourceAsStream("/objects/gewonnen.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}

	public void setupGame() {
		
		aSetzer.setObject();
	}
		
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}


	public void run() {

		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		if(gewonnen == true) {
			repaint();
			gameThread = null;
		}
		
		while(gameThread != null){
		
			if(sleepable != 0) {
				sleepable--;
				
			}
			
			
			
			//Update für jeden gametick
			update();
			
			//draw neue infos displayen
			repaint();
			
			if(schlafen == true) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				schlafen = false;
			}
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime );
				
				nextDrawTime += drawInterval;
				
			} catch(InterruptedException e) {
				
				e.printStackTrace();
			}
			 
		}
	}
		public void update() {
			
			player.update();
		}
		
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			
			
			tileM.draw(g2);
			//for Schleife noch auf 2 Setzen
		if(tileM.currentMap == 1) {
				for(int i = 2; i < obj.length; i++) {
					if(obj[i] != null) {
						obj[i].draw(g2, this);
					}
				}
			}
			
		if(tileM.currentMap == 2) {
			for(int i = 0; i < 2; i++) {
					obj[i].draw(g2, this);
				
			}
		}
		
			player.draw(g2);
			
			
			if(gewonnen == true) {
				g.fillRect(0, 0,screenWidth ,screenHeight );
				
			}	
			
			ui.draw(g2);
			
			
			if(schlafen == true) {
				g.fillRect(0, 0,screenWidth ,screenHeight );	
			}
				
			
			g2.dispose();
		}
	

	
			
		
	
	

}
