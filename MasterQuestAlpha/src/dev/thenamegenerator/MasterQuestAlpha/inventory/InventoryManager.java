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
	
	ArrayList<Item> playerWeapons = new ArrayList<Item>();
	ArrayList<Item> playerArmor = new ArrayList<Item>();
	ArrayList<Item> playerPotion = new ArrayList<Item>();
	ArrayList<Item> playerMagic = new ArrayList<Item>();
	ArrayList<Item> playerMisc = new ArrayList<Item>();
	
	private boolean onInventoryScreen = false;
	private boolean cont = true;
	
	public InventoryManager(InputHandler input, MouseHandler mouseHandler){
		inventory = new Inventory(input, mouseHandler);
		scrollScreen = new InventoryScrollScreen(mouseHandler);
		
		playerMisc.add(new Banana());
		playerMisc.add(new Carrot());
	}
	
	public ArrayList<Item> getPlayerItems(){
		return playerMisc;
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
		if(onInventoryScreen){
			if(inventory.inWeapons){
				scrollScreen.reset();
				cont = true;
				if(cont){
					for(int i = 0; i<playerWeapons.size(); i++){
						scrollScreen.addItem(playerWeapons.get(i));
					}
					cont = false;
				}
			}
		}
	}	
}