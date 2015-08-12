package dev.thenamegenerator.MasterQuestAlpha.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;

public class WorldRenderer{
	
	BufferedImage image;
	
	BufferedImage[] reference;
	
	public void init(){
		reference = new BufferedImage[Assets.groupTiles.length];
		for(int i = 0; i < Assets.groupTiles.length; i++){
			reference[i] = Assets.groupTiles[i];
		}
	}
	
	public BufferedImage renderWorld(int[][] world){
		 image = new BufferedImage(world[0].length * 32, world.length * 32, BufferedImage.TYPE_INT_RGB);
		 Graphics2D g = image.createGraphics();
		 
		 for(int y = 0; y < world.length; y++){
			 for(int x = 0; x < world[0].length; x++){
				 g.drawImage(reference[world[y][x]], x*32, y*32, null);
			 }
		 }
		 return image;
	 }
}