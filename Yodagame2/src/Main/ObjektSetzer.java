package Main;

import object.OBJ_Bett_bot;
import object.OBJ_Bett_top;
import object.OBJ_Pflanze;


public class ObjektSetzer {
	
	GamePanel gp;
	
	
	public ObjektSetzer(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		
		gp.obj[0] = new OBJ_Bett_top();
		gp.obj[0].x = 14 * gp.tileSize; 
		gp.obj[0].y = 9 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Bett_bot();
		gp.obj[1].x = 14 * gp.tileSize; 
		gp.obj[1].y = 10 * gp.tileSize;
		
	}
	public void setnewPlant(int x, int y) {
		int j = 2;
		while(gp.obj[j] != null) {
		         j++;
		}
			gp.obj[j] = new OBJ_Pflanze();
			gp.obj[j].x = x * gp.tileSize; 
			gp.obj[j].y = y * gp.tileSize;

	}
	
	public void remove(int index) {
		
			gp.obj[index] = null;

	}
}
