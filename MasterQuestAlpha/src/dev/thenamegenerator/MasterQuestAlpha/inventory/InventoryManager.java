package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.util.ArrayList;
import java.util.Collections;

import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseWheelHandler;
import dev.thenamegenerator.MasterQuestAlpha.items.*;

public class InventoryManager{
	
	private Inventory inventory;
	private InventoryScrollScreen scrollScreen;
	
	private boolean done = false;
	
	private boolean endLoop = false;
	
	
	ArrayList<Item> playerWeapons = new ArrayList<Item>();
	ArrayList<Item> playerArmor = new ArrayList<Item>();
	ArrayList<Item> playerPotion = new ArrayList<Item>();
	ArrayList<Item> playerMagic = new ArrayList<Item>();
	ArrayList<Item> playerMisc = new ArrayList<Item>();
	
	private boolean onInventoryScreen = false;
	
	public InventoryManager(InputHandler input, MouseHandler mouseHandler, MouseWheelHandler mouseWheelHandler){	
		inventory = new Inventory(input, mouseHandler);
		scrollScreen = new InventoryScrollScreen(mouseHandler, mouseWheelHandler);
		
		addItem(new Banana());
		addItem(new Carrot());
		addItem(new Banana());
		addItem(new Carrot());
		addItem(new Banana());
		addItem(new Carrot());
		addItem(new Carrot());
		
		addItem(new IronShortSword());
		
		addItem(new Watermelon());
		
		addItem(new Chicken());
		addItem(new Chicken());
		
		addItem(new Apple());
		
		addItem(new Fish());
		addItem(new Fish());
		
		addItem(new Bread());
		addItem(new Bread());
		addItem(new Bread());
		addItem(new Bread());
		addItem(new Bread());
		addItem(new Bread());
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
	
	public void addItem(Item item){
		if(item.getType().equalsIgnoreCase("food")){
			playerMisc.add(item);
		}else if(item.getType().equalsIgnoreCase("weapon")){
			playerWeapons.add(item);
		}else if(item.getType().equalsIgnoreCase("armor")){
			playerArmor.add(item);
		}else if(item.getType().equalsIgnoreCase("potion")){
			playerPotion.add(item);
		}
	}
	
	public void organizeItems(){
		boolean end = false;
		
		int index = 0;
		
		while(!end){
			Item item = playerMisc.get(index);
			for(int i = index + 1; i < playerMisc.size(); i++){
				if(item.getId() == playerMisc.get(i).getId()){
					Collections.swap(playerMisc, i, index + 1);
				}
			}
			index++;
			if(index >= playerMisc.size()){
				end = true;
			}
		}
		
	}
	
	public void tick(){
		inventory.check();
		if(inventory.renderInventory){
			onInventoryScreen = true;
		}else if(!inventory.renderInventory){
			onInventoryScreen = false;
		}
		if(onInventoryScreen){
			if(!done){
				organizeItems();
				done = true;
			}
			if(inventory.changeScreen){
				scrollScreen.resetScrollOffset();
				scrollScreen.tick();
				scrollScreen.reset();
				
				inventory.changeScreen = false;
				scrollScreen.isSelected = false;
			}else{
				scrollScreen.tick();
				scrollScreen.reset();
				scrollScreen.selectedItem();
			}
			if(inventory.inWeapons){
				for(int i = 0; i<playerWeapons.size(); i++){
					scrollScreen.addItem(playerWeapons.get(i));
				}				
			}else if(inventory.inArmor){
				for(int i = 0; i<playerArmor.size(); i++){
					scrollScreen.addItem(playerArmor.get(i));
				}
			}else if(inventory.inPotion){
				for(int i = 0; i<playerPotion.size(); i++){
					scrollScreen.addItem(playerPotion.get(i));
				}
			}else if(inventory.inMagic){
				for(int i = 0; i<playerMagic.size(); i++){
					scrollScreen.addItem(playerMagic.get(i));
				}
			}else if(inventory.inMisc){
				endLoop = false;
				int index = 0;
				int count = 1;
				Item prevItem;
				while(!endLoop){
					prevItem = playerMisc.get(index);
					if(index + 1 == playerMisc.size()){
						scrollScreen.addItem(playerMisc.get(index));
						break;
					}
					if(prevItem.getId() == playerMisc.get(index + 1).getId()){
						for(int i = index + 1; i < playerMisc.size(); i++){
							if(prevItem.getId() == playerMisc.get(i).getId()){
								count++;
								if(i == playerMisc.size() - 1){
									scrollScreen.stackItem(playerMisc.get(index), count);
									index = i;
									break;
								}
							}else{
								scrollScreen.stackItem(playerMisc.get(index), count);
								index = i - 1;
								break;
							}
						}
					}else{
						scrollScreen.addItem(playerMisc.get(index));
					}
					index++;
					count = 1;
					if(index >= playerMisc.size()){
						endLoop = true;
					}
				}
			}
		}
	}
}