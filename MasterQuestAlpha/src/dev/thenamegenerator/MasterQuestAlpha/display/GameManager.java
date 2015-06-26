package dev.thenamegenerator.MasterQuestAlpha.display;

import java.awt.Canvas;

import dev.thenamegenerator.MasterQuestAlpha.collision.Collision;
import dev.thenamegenerator.MasterQuestAlpha.entities.Chicken;
import dev.thenamegenerator.MasterQuestAlpha.entities.Elephant;
import dev.thenamegenerator.MasterQuestAlpha.entities.MainPlayer;
import dev.thenamegenerator.MasterQuestAlpha.entities.Spider;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseWheelHandler;
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
	private Spider spider;
	
	private InputHandler input;
	private MouseHandler mouseHandler;
	private MouseWheelHandler mouseWheelHandler;
	
	private InventoryManager inventoryManager;
	
	public GameManager(InputHandler input, MouseHandler mouseHandler, MouseWheelHandler mouseWheelHandler){
		this.input = input;
		this.mouseHandler = mouseHandler;
		this.mouseWheelHandler = mouseWheelHandler;
	}
	
	public void init(){
		player = new MainPlayer(input);
		
		inventoryManager = new InventoryManager(input, mouseHandler, mouseWheelHandler);
		
		chicken = new Chicken();
		elephant = new Elephant();
		spider = new Spider();
		
		camera = new MovingMap(0,0);
		renderer = new WorldRenderer();
		
		collision = new Collision(World.StartMap);
		collision.initCollision();
		
		renderer.renderWorld(World.StartMap);
		camera.setImage(renderer.getImage());
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
	
	public Spider getSpider(){
		return spider;
	}
	
	public void tick(){
		inventoryManager.tick();
		if(!inventoryManager.onInventoryScreen()){
			camera.setX(-1*player.getWorldX()+224);
			camera.setY(-1*player.getWorldY()+128);
			
			player.move();
			chicken.move();
			elephant.move();
			spider.move();
			
			chicken.setX(camera.getX() + 224 + chicken.getDispositionX());
			chicken.setY(camera.getY() + 128 + chicken.getDispositionY());
			
			spider.setX(camera.getX() + 128 + spider.getDispositionX());
			spider.setY(camera.getY() + 32 + spider.getDispositionY());
			
			elephant.setX(camera.getX() + 224 + elephant.getDispositionX());
			elephant.setY(camera.getY() + 256 + elephant.getDispositionY());
		}
	}
}