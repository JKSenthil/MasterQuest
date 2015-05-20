package dev.thenamegenerator.MasterQuestAlpha.world;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;

public class WorldRenderer extends Canvas{
	private static final long serialVersionUID = 1L;
	BufferedImage image;
	Graphics2D cg;
	
	public void renderWorld(int[] world){
			int width = 32*World.xBlock;
			int length = 32*World.yBlock;
			
	    	int count = 0;
	    	image = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);
	    	cg =  image.createGraphics();
	    	
	    	//cg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    	for(int i = 0; i<(World.xBlock * World.yBlock); i+=World.xBlock){
	    		for(int x = 0; x<World.xBlock; x++){
	    			if(world[x+i]==0){
	    				cg.drawImage(Assets.grass, 32*(x), 32*count, this);
	    			}
	    			if(world[x+i]==1){
	    				cg.drawImage(Assets.water, 32*(x), 32*count, this);
	    			}
	    			if(world[x+i]==2){
	    				cg.drawImage(Assets.dirt, 32*(x), 32*count, this);
	    			}
	    			if(world[x+i]==3){
	    				cg.drawImage(Assets.path, 32*(x), 32*count, this);
	    			}
	    			if(world[x+i]==4){
	    				cg.drawImage(Assets.wall, 32*(x), 32*count, this);
	    			}
	    			if(world[x+i]==5){
	    				cg.drawImage(Assets.door, 32*(x), 32*count, this);
	    			}
	    			if(world[x+i]==6){
	    				cg.drawImage(Assets.window, 32*(x), 32*count, this);
	    			}
	    			if(world[x+i]==7){
	    				cg.drawImage(Assets.roof, 32*(x), 32*count, this);
	    			}
	    		}
	    		count++;
	    	}
	 }
	 
	 public BufferedImage getImage(){
		 return image;
	 }
}