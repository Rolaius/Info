package object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class OBJ_Bett_top extends SuperObject {

	public BufferedImage  top ;
	
	public OBJ_Bett_top() {
		
		name = "Bett";
		
		try {
			
			top = ImageIO.read(getClass().getResourceAsStream("/objects/betttop.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		image = top;
		
	}
	
}

