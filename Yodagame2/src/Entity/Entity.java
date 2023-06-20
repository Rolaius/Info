package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, right1, right2, right3, right4, right5, left1, left2, left3, left4, left5, stand;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY; 
	public boolean collisionOn = false;
	public boolean plantableOn = false;
	public void paintComponent(Graphics g) {}
}
