package dev.thenamegenerator.MasterQuestAlpha.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;

public class WorldRenderer{
	BufferedImage image;
	Graphics2D cg;
	
	 public BufferedImage renderWorld(int[][] world){
		 image = new BufferedImage(world[0].length * 32, world.length * 32, BufferedImage.TYPE_INT_RGB);
		 Graphics2D g = image.createGraphics();
		 
		 for(int y = 0; y < world.length; y++){
			 for(int x = 0; x < world[0].length; x++){
				 if(world[y][x] == 0){
					 g.drawImage(Assets.grass, x*32, y*32, null);
				 }else if(world[y][x] == 1){
					 g.drawImage(Assets.water, x*32, y*32, null);
				 }else if(world[y][x] == 2){
					 g.drawImage(Assets.dirt, x*32, y*32, null);
				 }else if(world[y][x] == 3){
					 g.drawImage(Assets.path, x*32, y*32, null);
				 }else if(world[y][x] == 4){
					 g.drawImage(Assets.wall, x*32, y*32, null);
				 }else if(world[y][x] == 5){
					 g.drawImage(Assets.door, x*32, y*32, null);
				 }else if(world[y][x] == 6){
					 g.drawImage(Assets.window, x*32, y*32, null);
				 }else if(world[y][x] == 7){
					 g.drawImage(Assets.roof, x*32, y*32, null);
				 }else if(world[y][x] == 8){
					 g.drawImage(Assets.tree, x*32, y*32, null);
				 }else if(world[y][x] == 9){
					 g.drawImage(Assets.flower, x*32, y*32, null);
				 }else if(world[y][x] == 10){
					 g.drawImage(Assets.bush, x*32, y*32, null);
				 }else if(world[y][x] == 11){
					 g.drawImage(Assets.bridge, x*32, y*32, null);
				 }
			 }
		 }
		 
		 return image;
	 }
	 
	 public BufferedImage getImage(){
		 return image;
	 }
}