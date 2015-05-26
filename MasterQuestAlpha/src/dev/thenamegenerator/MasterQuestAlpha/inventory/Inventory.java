package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;

public class Inventory {
	
	private InputHandler input;
	BufferedImage inven;
	
	public boolean renderInventory = false;
	
	public Inventory(InputHandler input){
		inven = Assets.inventory;
		this.input = input;
	}
	
	public BufferedImage getImage(){
		return inven;
	}
	
	public void check(){
		if(input.e.isPressed()){
			if(!renderInventory){
				renderInventory = true;
			}else{
				renderInventory = false;
			}
			if(input.e.isPressed()){
				input.reset();
			}
		}
	}
	
}