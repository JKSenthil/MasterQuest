package dev.thenamegenerator.MasterQuestAlpha.inventory;

import dev.thenamegenerator.MasterQuestAlpha.items.Banana;
import dev.thenamegenerator.MasterQuestAlpha.items.Carrot;
import dev.thenamegenerator.MasterQuestAlpha.items.Item;

public class InventoryManager {
	
	Item[] playerInventory;	
	
	public InventoryManager(){
		playerInventory = new Item[99];
		
		playerInventory[0] = new Carrot();
		playerInventory[1] = new Banana();
	}
	
}
