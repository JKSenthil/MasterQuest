package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.util.ArrayList;

import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.items.Banana;
import dev.thenamegenerator.MasterQuestAlpha.items.Carrot;
import dev.thenamegenerator.MasterQuestAlpha.items.Item;

public class InventoryManager {
	
	private Inventory inventory;
	private InventoryScrollScreen scrollScreen;
	
	ArrayList<Item> playerItems = new ArrayList<Item>();
	
	private boolean onInventoryScreen = false;
	
	public InventoryManager(InputHandler input, MouseHandler mouseHandler){
		inventory = new Inventory(input);
		scrollScreen = new InventoryScrollScreen(mouseHandler);
		
		playerItems.add(new Banana());
		playerItems.add(new Carrot());
	}
	
	public ArrayList<Item> getPlayerItems(){
		return playerItems;
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	public boolean onInventoryScreen(){
		return onInventoryScreen;
	}
	
	public InventoryScrollScreen getInventoryScrollScreen(){
		return scrollScreen;
	}
	
	public void tick(){
		inventory.check();
		if(inventory.renderInventory){
			onInventoryScreen = true;
		}
		if(!inventory.renderInventory){
			onInventoryScreen = false;
		}
	}	
}