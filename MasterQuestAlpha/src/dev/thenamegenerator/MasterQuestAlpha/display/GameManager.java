package dev.thenamegenerator.MasterQuestAlpha.display;

import java.awt.Canvas;

import dev.thenamegenerator.MasterQuestAlpha.entities.Chicken;
import dev.thenamegenerator.MasterQuestAlpha.entities.Elephant;
import dev.thenamegenerator.MasterQuestAlpha.entities.MainPlayer;
import dev.thenamegenerator.MasterQuestAlpha.entities.Spider;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseWheelHandler;
import dev.thenamegenerator.MasterQuestAlpha.inventory.InventoryManager;
import dev.thenamegenerator.MasterQuestAlpha.world.WorldManager;

public class GameManager extends Canvas{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MainPlayer player;
	private Chicken chicken;
	private Elephant elephant;
	private Spider spider;
	
	private InputHandler input;
	private MouseHandler mouseHandler;
	private MouseWheelHandler mouseWheelHandler;
	
	private InventoryManager inventoryManager;
	private WorldManager worldManager;
	
	public GameManager(InputHandler input, MouseHandler mouseHandler, MouseWheelHandler mouseWheelHandler){
		this.input = input;
		this.mouseHandler = mouseHandler;
		this.mouseWheelHandler = mouseWheelHandler;
	}
	
	public void init(){
		player = new MainPlayer(input);
		
		inventoryManager = new InventoryManager(input, mouseHandler, mouseWheelHandler);
		worldManager = new WorldManager();
		
		chicken = new Chicken();
		elephant = new Elephant();
		spider = new Spider();
	}
	
	public WorldManager getWorldManager(){
		return worldManager;
	}
	
	public MainPlayer getPlayer(){
		return player;
	}
	
	public Chicken getChicken(){
		return chicken;
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
			worldManager.getCamera().setX(-1*player.getWorldX()+224);
			worldManager.getCamera().setY(-1*player.getWorldY()+128);
			
			player.move();
			chicken.move();
			elephant.move();
			spider.move();
			
			chicken.setX(worldManager.getCamera().getX() + 224 + chicken.getDispositionX());
			chicken.setY(worldManager.getCamera().getY() + 128 + chicken.getDispositionY());
			
			spider.setX(worldManager.getCamera().getX() + 128 + spider.getDispositionX());
			spider.setY(worldManager.getCamera().getY() + 32 + spider.getDispositionY());
			
			elephant.setX(worldManager.getCamera().getX() + 224 + elephant.getDispositionX());
			elephant.setY(worldManager.getCamera().getY() + 256 + elephant.getDispositionY());
		}
	}
}