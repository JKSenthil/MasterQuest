package dev.thenamegenerator.MasterQuestAlpha.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.collision.Collision;
import dev.thenamegenerator.MasterQuestAlpha.filereader.WorldTextReader;


public class WorldManager{
	
	private MovingMap camera;
	private WorldRenderer renderer;
	
	private WorldTextReader worldReader;
	
	private Chunk[][] world = new Chunk[4][4];
	
	private BufferedImage worldImage;
	
	public WorldManager(int xx, int yy){
		camera = new MovingMap(xx,yy);
		
		renderer = new WorldRenderer();
		renderer.init();
		
		worldReader = new WorldTextReader();
		worldReader.load();
		
		Collision.loadCollision(worldReader.getMaps());
		
		loadChunks();
		
		Graphics2D g2d;
		worldImage = new BufferedImage(30*4*32, 17*4*32, BufferedImage.TYPE_INT_RGB);
		g2d = worldImage.createGraphics();
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				g2d.drawImage(world[x][y].getMapImage(), x*32*30, y*32*17, null);
			}
		}
		camera.setImage(worldImage);
	}
	
	public void loadChunks(){
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				world[x][y] = new Chunk(worldReader.getMaps().get(4*x+y));
				world[x][y].renderMap();
			}
		}
	}
	
	public MovingMap getCamera(){
		return camera;
	}
	
	public WorldRenderer getRenderer(){
		return renderer;
	}
	
}