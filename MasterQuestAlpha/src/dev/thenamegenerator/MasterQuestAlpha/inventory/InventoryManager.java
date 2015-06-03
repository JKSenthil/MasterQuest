package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.util.ArrayList;

import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseWheelHandler;
import dev.thenamegenerator.MasterQuestAlpha.items.*;

public class InventoryManager {
	
	private Inventory inventory;
	private InventoryScrollScreen scrollScreen;
	
	//TEST
	private MouseWheelHandler mouseWheelHandler;
	
	ArrayList<Item> playerWeapons = new ArrayList<Item>();
	ArrayList<Item> playerArmor = new ArrayList<Item>();
	ArrayList<Item> playerPotion = new ArrayList<Item>();
	ArrayList<Item> playerMagic = new ArrayList<Item>();
	ArrayList<Item> playerMisc = new ArrayList<Item>();
	
	private boolean onInventoryScreen = false;
	private boolean cont = true;
	
	public InventoryManager(InputHandler input, MouseHandler mouseHandler, MouseWheelHandler mouseWheelHandler){
		//test
		this.mouseWheelHandler = mouseWheelHandler;
		
		inventory = new Inventory(input, mouseHandler);
		scrollScreen = new InventoryScrollScreen(mouseHandler);
		
		addItem(new Banana());
		addItem(new Carrot());
		addItem(new IronShortSword());
		
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
	
	public void tick(){
		inventory.check();
		if(inventory.renderInventory){
			onInventoryScreen = true;
		}else if(!inventory.renderInventory){
			onInventoryScreen = false;
		}
		if(onInventoryScreen){
			if(mouseWheelHandler.wheel.noches > 0){
				System.out.println("Mouse Wheel Scolls Down");
				System.out.println(mouseWheelHandler.wheel.noches);
				mouseWheelHandler.wheel.noches = 0;
			}else if(mouseWheelHandler.wheel.noches < 0){
				System.out.println("Mouse Wheel Scolls Up");
				System.out.println(mouseWheelHandler.wheel.noches);
				mouseWheelHandler.wheel.noches = 0;
			}	
			if(inventory.inWeapons){
				scrollScreen.reset();
				cont = true;
				if(cont){
					for(int i = 0; i<playerWeapons.size(); i++){
						scrollScreen.addItem(playerWeapons.get(i));
					}
					cont = false;
				}
			}else if(inventory.inArmor){
				scrollScreen.reset();
				cont = true;
				if(cont){
					for(int i = 0; i<playerArmor.size(); i++){
						scrollScreen.addItem(playerArmor.get(i));
					}
					cont = false;
				}
			}else if(inventory.inPotion){
				scrollScreen.reset();
				cont = true;
				if(cont){
					for(int i = 0; i<playerPotion.size(); i++){
						scrollScreen.addItem(playerPotion.get(i));
					}
					cont = false;
				}
			}else if(inventory.inMagic){
				scrollScreen.reset();
				cont = true;
				if(cont){
					for(int i = 0; i<playerMagic.size(); i++){
						scrollScreen.addItem(playerMagic.get(i));
					}
					cont = false;
				}
			}else if(inventory.inMisc){
				scrollScreen.reset();
				cont = true;
				if(cont){
					for(int i = 0; i<playerMisc.size(); i++){
						scrollScreen.addItem(playerMisc.get(i));
					}
					cont = false;
				}
			}
		}
	}	
}