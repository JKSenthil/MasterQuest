package dev.thenamegenerator.MasterQuestAlpha.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;

public class Chunk {
	
	private BufferedImage mapImage;
	
	private int[][] map = new int[30][17];

	public Chunk(int[][] map){
		for(int y = 0; y < 17; y++){
			for(int x = 0; x < 30; x++){
				this.map[x][y] = map[x][y];
			}
		}
	}
	
	public void renderMap(){
		mapImage = new BufferedImage(30 * 32, 17 * 32, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = mapImage.createGraphics();
			 
		for(int y = 0; y < 17; y++){
			 for(int x = 0; x < 30; x++){
				 g.drawImage(Assets.groupTiles[map[x][y]], x*32, y*32, null);
			 }
		 }
		g.dispose();
	}
	
	public BufferedImage getMapImage(){
		return mapImage;
	}	
}