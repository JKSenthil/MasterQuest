package dev.thenamegenerator.MasterQuestAlpha.display;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.collision.Collision;
import dev.thenamegenerator.MasterQuestAlpha.entities.Chicken;
import dev.thenamegenerator.MasterQuestAlpha.entities.Elephant;
import dev.thenamegenerator.MasterQuestAlpha.entities.MainPlayer;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.inventory.InventoryManager;
import dev.thenamegenerator.MasterQuestAlpha.world.MovingMap;
import dev.thenamegenerator.MasterQuestAlpha.world.World;
import dev.thenamegenerator.MasterQuestAlpha.world.WorldRenderer;

public class GameManager extends Canvas{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MainPlayer player;
	private WorldRenderer renderer;
	private Collision collision;
	private MovingMap camera;
	private Chicken chicken;
	private Elephant elephant;
	
	private InputHandler input;
	private MouseHandler mouseHandler;
	
	private InventoryManager inventoryManager;
	
	private BufferedImage world;
	
	public GameManager(InputHandler input, MouseHandler mouseHandler){
		this.input = input;
		this.mouseHandler = mouseHandler;
	}
	
	public void init(){
		player = new MainPlayer(input);
		inventoryManager = new InventoryManager(input, mouseHandler);
		chicken = new Chicken();
		elephant = new Elephant();
		camera = new MovingMap(0,0);
		renderer = new WorldRenderer();
		collision = new Collision(World.StartMap);
		
		
		collision.initCollision();
		renderer.renderWorld(World.StartMap);
		world = renderer.getImage();
		camera.setImage(world);
	}
	
	public MainPlayer getPlayer(){
		return player;
	}
	
	public Chicken getChicken(){
		return chicken;
	}
	
	public MovingMap getCamera(){
		return camera;
	}
	
	public Elephant getElephant(){
		return elephant;
	}
	
	public InventoryManager getInventoryManager(){
		return inventoryManager;
	}
	
	public void tick(){
		inventoryManager.tick();
		if(!inventoryManager.onInventoryScreen()){
			camera.setX(-1*player.getWorldX()+224);
			camera.setY(-1*player.getWorldY()+128);
			
			player.move();
			chicken.move();
			elephant.move();
			
			chicken.setX(camera.getX() + 224 + chicken.getDispositionX());
			chicken.setY(camera.getY() + 128 + chicken.getDispositionY());
			
			elephant.setX(camera.getX() + 224 + elephant.getDispositionX());
			elephant.setY(camera.getY() + 256 + elephant.getDispositionY());
		}
	}
}