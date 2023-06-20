package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;


public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	public int pflanzen = 0;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;		
		
		solidArea = new Rectangle();
		solidArea.x = 16;
		solidArea.y = 12;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 12;
		solidArea.height = 24;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	public void setDefaultValues(){
		
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/play_up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/play_up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/play_right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/play_right2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/play_right3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/play_right4.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/play_right5.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/play_left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/play_left2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/play_left3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/play_left4.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/play_left5.png"));
			stand = ImageIO.read(getClass().getResourceAsStream("/player/play_stand.png"));
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void update() {
		
		if( keyH.lClicked == true) {
			
			plantableOn = false;
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			int objIndex = gp.cChecker.checkObject(this,true);
			//ObjectIndex ist der Index mit welchem wir beijeder Pflanze arbeiten einzeln
			uselClicked(objIndex);
			
		}
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true ) {
			
			if(keyH.upPressed == true) {
				direction = "up";
				
			}
			if(keyH.downPressed == true) {
				direction = "down";
				
			}
			if(keyH.rightPressed == true) {
				direction = "right";
				
			}
			if(keyH.leftPressed == true) {
				direction = "left";
				
			}
			plantableOn = false;
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			
			transition();
			
			if(collisionOn == false) {
				
				switch(direction) {
				
				case "up": y -= speed; break;
				case "down": y += speed; break;
				case "left": x -= speed; break;
				case "right": x += speed; break;
				
				}
				
			}
			
			spriteCounter++;
			if(spriteCounter > 5) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 3;
				}
				else if(spriteNum == 3) {
					spriteNum = 4;
				}
				else if(spriteNum == 4) {
					spriteNum = 5;
				}
				else if(spriteNum == 5) {
					spriteNum = 1;
				}
				spriteCounter = 0;
				
			}
			
		}
		
		if(pflanzen >= 25) {
			gp.gewonnen = true;
			
		}
		
	}
	
	public void uselClicked(int index) {
		if(keyH.lClicked == true && index <= 1 && gp.sleepable == 0) {
			gp.schlafen = true;

			gp.sleepable = 7200/4;
			for(int i = 1; i < gp.obj.length ;i++) {
				if(gp.obj[i] != null) {
					gp.obj[i].neuerTag();
					
					
				}
				
			}
			keyH.lClicked = false;
		}
		else if(keyH.lClicked == true && index <= 1  && gp.sleepable != 0) {
			gp.ui.showMessage("Du mussst noch " + gp.sleepable/60 + " sec warten, bis du schlafen kannst");
			keyH.lClicked = false;
		}
		if(keyH.lClicked == true && index == 999 && plantableOn == true) {

			int entityMiddleX = x + solidArea.x + solidArea.width/2;
			int entityMiddleY = y + solidArea.y + solidArea.height;
					
			int entityMiddleCol = entityMiddleX/gp.tileSize; 
			int entityMiddleRow = entityMiddleY/gp.tileSize;

			gp.aSetzer.setnewPlant(entityMiddleCol, entityMiddleRow);
		}
		
		if(keyH.lClicked == true && index >= 1 && index < 100 && gp.obj[index].erntbar == true) {
			
			pflanzen++;
			gp.aSetzer.remove(index);
		}
		keyH.lClicked = false;
	}
	
	public void transition() {
		switch(direction) {
		
		case "up":
			int test1 = y - speed;
			if(test1 < 24) {
				System.out.println("Fehler");
			}
		
		break;
		case "down":  
			int test2 = y + speed;
			if(test2 > 552) {
				System.out.println("Fehler");
			}
		break;
		case "left": 
			int test3 = x - speed;
			if(test3 < 24 && gp.tileM.currentMap == 0) {
				gp.tileM.changeMap(1);
				x = 696;
				
			}
			if(test3 < 24 && gp.tileM.currentMap == 2) {
				gp.tileM.changeMap(0);
				x = 696;
				
			}
		break;
		case "right": 
			int test4 = x + speed;
			if(test4 > 744 && gp.tileM.currentMap == 0) {
				gp.tileM.changeMap(2);
				x = 48;
				
			}
			if(test4 > 744 && gp.tileM.currentMap == 1) {
				gp.tileM.changeMap(0);
				x = 48;
				
			}
		break;
		
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		// g2.setColor(Color.white);
		
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
				}
			if(spriteNum == 2) {
				image = up1;
			}
			if(spriteNum == 3) {
				image = up2;
				}
			if(spriteNum == 4) {
				image = up2;
			}
			if(spriteNum == 5) {
				image = up1;
			}
			
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
				}
			if(spriteNum == 2) {
				image = down1;
			}
			if(spriteNum == 3) {
				image = down2;
				}
			if(spriteNum == 4) {
				image = down2;
			}
			if(spriteNum == 5) {
				image = down1;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
				}
			if(spriteNum == 2) {
				image = right2;
			}
			if(spriteNum == 3) {
				image = right3;
				}
			if(spriteNum == 4) {
				image = right4;
			}
			if(spriteNum == 5) {
				image = right5;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
				}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 3) {
				image = left3;
				}
			if(spriteNum == 4) {
				image = left4;
			}
			if(spriteNum == 5) {
				image = left5;
			}
			break;
		
			
		
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
