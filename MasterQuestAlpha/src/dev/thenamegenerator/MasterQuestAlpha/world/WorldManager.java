package dev.thenamegenerator.MasterQuestAlpha.world;

import dev.thenamegenerator.MasterQuestAlpha.collision.Collision;


public class WorldManager {
	
	private MovingMap camera;
	private WorldRenderer renderer;
	
	private int[][] currentWorld;
	
	private int length;
	
	private int playerX;
	private int playerY;
	
	public WorldManager(){
		camera = new MovingMap(0,0);
		renderer = new WorldRenderer();
		
		Collision.init(World.StartMapTwo);
		Collision.loadCollision(World.StartMapTwo);
		
		renderer.renderWorld(World.StartMapTwo);
		camera.setImage(renderer.getImage());
		
		currentWorld = World.StartMapTwo;
	}
	
	public MovingMap getCamera(){
		return camera;
	}
	
	public WorldRenderer getRenderer(){
		return renderer;
	}
	
	public void getPlayerMapCoords(int x, int y){
		this.playerX = x;
		this.playerY = y;
	}
	
	public void tick(){
		length = currentWorld[0].length;
		
		if(playerY >= 7 || playerX >= 7 || length == 0){
			
		}
	}
	
}