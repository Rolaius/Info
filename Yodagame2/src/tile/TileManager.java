package tile;

import java.awt.Graphics2D;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	public int currentMap;
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		//evtl fix für stucks sein in den wall tiles
		mapTileNum = new int[gp.maxScreenCol +1][gp.maxScreenRow +1];
		
		getTileImage();
		// wieder auf 0
		changeMap(1);
	}
	
	public void changeMap(int num) {
		if(num == 0) {
			loadMap("/maps/map_01.txt");
			currentMap = 0;
		}
		else if(num == 1) {
			loadMap("/maps/map_02.txt");
			currentMap = 1;
		}
		else if(num == 2) {
			loadMap("/maps/map_03.txt");
			currentMap = 2;
		}
		
	}
	
	public void getTileImage() {
		
		try {
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sandstein.png"));
			tile[2].plantable = true;
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stein.png"));
			tile[1].collision = true;
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sandstein_2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	public void loadMap(String DateinPfad) {
		
		try {
			InputStream is = getClass().getResourceAsStream(DateinPfad);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxScreenCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
					
				}
			}
			br.close();
			
			
		}catch(Exception e) {
			
			
		}
		
	}
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
				
			}
			
			
			
		}
		

	}
}
