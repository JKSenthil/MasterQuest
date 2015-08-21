package dev.thenamegenerator.MasterQuestAlpha.display;

import dev.thenamegenerator.MasterQuestAlpha.combat.StatusBar;
import dev.thenamegenerator.MasterQuestAlpha.entities.EntityManager;
import dev.thenamegenerator.MasterQuestAlpha.entities.MainPlayer;
import dev.thenamegenerator.MasterQuestAlpha.entities.PlayerMP;
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
	
	private StatusBar statusBar;
	
	private String userName;
	
	public GameManager(InputHandler input, MouseHandler mouseHandler, MouseWheelHandler mouseWheelHandler, String userName){
		this.input = input;
		this.mouseHandler = mouseHandler;
		this.mouseWheelHandler = mouseWheelHandler;
		this.userName = userName;
	}
	
	public void init(){
		player = new PlayerMP(input, userName, null, -1);
		
		worldManager = new WorldManager(-1*player.getWorldX()+224, -1*player.getWorldY()+128);
		inventoryManager = new InventoryManager(input, mouseHandler, mouseWheelHandler);
		
		entityManager = new EntityManager();
		
		statusBar = new StatusBar(player.getHealth(), player.getMagic());
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
	
	public void setPlayer(MainPlayer player){
		this.player = player;
	}
	
	public InventoryManager getInventoryManager(){
		return inventoryManager;
	}
	
	public StatusBar getStatusBar(){
		return statusBar;
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
			for(int  i = 0; i < entityManager.players.size(); i++){
				//entityManager.players.get(i).setX(worldManager.getCamera().getX() + entityManager.players.get(i).getStartX() + entityManager.players.get(i).getDispositionX());
				//entityManager.players.get(i).setY(worldManager.getCamera().getY() + entityManager.players.get(i).getStartY() + entityManager.players.get(i).getDispositionY());
				entityManager.players.get(i).setX(worldManager.getCamera().getX() + entityManager.players.get(i).getWorldX());
				entityManager.players.get(i).setY(worldManager.getCamera().getY() + entityManager.players.get(i).getWorldY());
			}
			statusBar.updateStatusBars(player.getHealth(), player.getMagic());
		}
	}
}