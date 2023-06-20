package object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.SplittableRandom;

import javax.imageio.ImageIO;

public class OBJ_Pflanze extends SuperObject{
	
	int erntestatus = 0;
	
	public BufferedImage pf1, pf2, pf3;
	
	public OBJ_Pflanze() {
		
		name = "Pflanze";
		try {
			
			pf1 = ImageIO.read(getClass().getResourceAsStream("/objects/Pflanze_1.png"));
			pf2 = ImageIO.read(getClass().getResourceAsStream("/objects/Pflanze_2.png"));
			pf3 = ImageIO.read(getClass().getResourceAsStream("/objects/Pflanze_3.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		image = pf1;
	}

	public void neuerTag() {
		SplittableRandom random = new SplittableRandom();
		
		// !! Unbedingt auf > 0 setzen nur zum testen auf > -1
		boolean wachsen = random.nextInt(1,11) > 3;
		
		if(erntestatus<3 && wachsen) {
			
			if(erntestatus == 0) {
				image = pf2;
			}
			else {
				image = pf3;
				erntbar = true;
			}
			erntestatus++;
		}
	}
	
	public void remove(int index) {
		
		
	}
	
}
