package object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class OBJ_Bett_bot extends SuperObject {

	
	public BufferedImage bot ;
	
	public boolean schlafend = false;
	
	public OBJ_Bett_bot() {
		
		name = "Bett";
		
		try {
			
			bot = ImageIO.read(getClass().getResourceAsStream("/objects/bettbot.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		image = bot;
		
	}

	
	public boolean getSchlafend()   {
		if(schlafend == true) {	
			System.out.println("neuertagBett");
		}
		return schlafend;	
			
			
			
		}
		
	}
	
	

