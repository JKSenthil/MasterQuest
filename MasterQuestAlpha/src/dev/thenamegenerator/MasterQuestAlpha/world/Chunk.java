package dev.thenamegenerator.MasterQuestAlpha.world;

import java.awt.image.BufferedImage;

public class Chunk {
	
	private BufferedImage mapImage;
	
	private int[][] map;
	
	private WorldRenderer renderer;

	public Chunk(int[][] map){
		this.map = map;
	}
	
	public void renderMap(){
		mapImage = renderer.renderWorld(map);
	}
	
	public BufferedImage getMapImage(){
		return mapImage;
	}
	
}
