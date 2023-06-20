package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

	GamePanel gp;
	Font arial_10;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_10 = new Font("Arial", Font.PLAIN, 25);
	}
	
	public void showMessage(String t) {
		
		message = t;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setFont(arial_10);
		if(gp.gewonnen == true) {
			g2.setColor(Color.white);
			g2.drawString("Dank dem gewonnenen Treibstoff konntest du entkommen!", 48, gp.tileSize*6);
		}
		else {
			g2.setColor(Color.black);
			g2.drawString(gp.player.pflanzen + " von 20 gebrauchten Pflanzen", 24, 36);
			
		}
		
		if(messageOn == true) {
			
			g2.drawString(message,gp.tileSize ,gp.tileSize*11 );
			
			messageCounter++;
			
			if(messageCounter > 60) {
				messageCounter = 0;
				messageOn = false; 
			}
		}
	}
}
