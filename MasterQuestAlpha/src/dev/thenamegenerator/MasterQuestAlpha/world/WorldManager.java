package dev.thenamegenerator.MasterQuestAlpha.world;

import dev.thenamegenerator.MasterQuestAlpha.collision.Collision;


public class WorldManager {
	
	private MovingMap camera;
	private WorldRenderer renderer;
	
	public WorldManager(){
		camera = new MovingMap(0,0);
		renderer = new WorldRenderer();
		
		Collision.init(World.StartMapTwo);
		Collision.loadCollision(World.StartMapTwo);
		
		renderer.renderWorld(World.StartMapTwo);
		camera.setImage(renderer.getImage());
	}
	
	public MovingMap getCamera(){
		return camera;
	}
	
	public WorldRenderer getRenderer(){
		return renderer;
	}
	
	public void tick(){
		
	}
	
}