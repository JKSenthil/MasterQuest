package dev.thenamegenerator.MasterQuestAlpha.display;

import dev.thenamegenerator.MasterQuestAlpha.entities.EntityManager;
import dev.thenamegenerator.MasterQuestAlpha.entities.MainPlayer;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseWheelHandler;
import dev.thenamegenerator.MasterQuestAlpha.inventory.InventoryManager;
import dev.thenamegenerator.MasterQuestAlpha.world.WorldManager;

public class GameManager{
	
	private MainPlayer player;
	
	private InputHandler input;
	private MouseHandler mouseHandler;
	private MouseWheelHandler mouseWheelHandler;
	
	private InventoryManager inventoryManager;
	private WorldManager worldManager;
	private EntityManager entityManager;
	
	public GameManager(InputHandler input, MouseHandler mouseHandler, MouseWheelHandler mouseWheelHandler){
		this.input = input;
		this.mouseHandler = mouseHandler;
		this.mouseWheelHandler = mouseWheelHandler;
	}
	
	public void init(){
		player = new MainPlayer(input);
		
		inventoryManager = new InventoryManager(input, mouseHandler, mouseWheelHandler);
		worldManager = new WorldManager(-1*player.getWorldX()+224, -1*player.getWorldY()+128);
		entityManager = new EntityManager();
		entityManager.spawnAnimals();
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public WorldManager getWorldManager(){
		return worldManager;
	}
	
	public MainPlayer getPlayer(){
		return player;
	}
	
	public InventoryManager getInventoryManager(){
		return inventoryManager;
	}
	
	public void tick(){
		inventoryManager.tick();
		if(!inventoryManager.onInventoryScreen()){
			worldManager.getCamera().setX(-1*player.getWorldX()+224);
			worldManager.getCamera().setY(-1*player.getWorldY()+128);
			
			player.move();
			
			for(int i = 0; i < entityManager.getAnimals().size(); i++){
				entityManager.getAnimals().get(i).move();
				entityManager.getAnimals().get(i).setX(worldManager.getCamera().getX() + entityManager.getAnimals().get(i).getStartX() + entityManager.getAnimals().get(i).getDispositionX());
				entityManager.getAnimals().get(i).setY(worldManager.getCamera().getY() + entityManager.getAnimals().get(i).getStartY() + entityManager.getAnimals().get(i).getDispositionY());
			}
			//chicken.move();
			//elephant.move();
			//spider.move();
			
			//chicken.setX(worldManager.getCamera().getX() + chicken.getStartX() + chicken.getDispositionX());
			//chicken.setY(worldManager.getCamera().getY() + chicken.getStartY() + chicken.getDispositionY());
			
			//spider.setX(worldManager.getCamera().getX() + 128 + spider.getDispositionX());
			//spider.setY(worldManager.getCamera().getY() + 32 + spider.getDispositionY());
			
			//elephant.setX(worldManager.getCamera().getX() + 224 + elephant.getDispositionX());
			//elephant.setY(worldManager.getCamera().getY() + 256 + elephant.getDispositionY());
		}
	}
}