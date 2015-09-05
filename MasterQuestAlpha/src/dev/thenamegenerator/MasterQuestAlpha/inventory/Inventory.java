package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;

public class Inventory {
	
	private InputHandler input;
	private MouseHandler mouseHandler;
	
	public boolean renderInventory = false;
	public boolean inStats, inMap, inWeapons, inArmor, inPotion, inMagic, inMisc;
	public boolean changeScreen = false;
	
	BufferedImage inven;
	
	public int a = 32;
	
	public Inventory(InputHandler input, MouseHandler mouseHandler){
		inven = Assets.inventory;
		
		this.input = input;
		this.mouseHandler = mouseHandler;
		
		inWeapons = false;
		inMap = false;
		inWeapons = false;
		inArmor = false;
		inPotion = false;
		inMagic = false;
		inMisc = true;
	}
	
	public void reset(){
		inWeapons = false;
		inMap = false;
		inWeapons = false;
		inArmor = false;
		inPotion = false;
		inMagic = false;
		inMisc = false;
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
			input.reset();
		}
		if(renderInventory){
			if(mouseHandler.mouse.isPressed()){
				if(mouseHandler.mouse.x >=(24+32+a) && mouseHandler.mouse.x <= (59+32+a)){
					if(mouseHandler.mouse.y >= (19+32+a) && mouseHandler.mouse.y <= (46+32+a)){
						reset();
						inStats = true;
						changeScreen = true;
					}else if(mouseHandler.mouse.y >= (53+32+a) && mouseHandler.mouse.y <= (80+32+a)){
						reset();
						inMap = true;
						changeScreen = true;
					}else if(mouseHandler.mouse.y >= (88+32+a) && mouseHandler.mouse.y <= (115+32+a)){
						reset();
						inWeapons = true;
						changeScreen = true;
					}else if(mouseHandler.mouse.y >= (122+32+a) && mouseHandler.mouse.y <= (149+32+a)){
						reset();
						inArmor = true;
						changeScreen = true;
					}else if(mouseHandler.mouse.y >= (156+32+a) && mouseHandler.mouse.y <= (183+32+a)){
						reset();
						inPotion = true;
						changeScreen = true;
					}else if(mouseHandler.mouse.y >= (190+32+a) && mouseHandler.mouse.y <= (217+32+a)){
						reset();
						inMagic = true;
						changeScreen = true;
					}else if(mouseHandler.mouse.y >= (224+32+a) && mouseHandler.mouse.y <= (251+32+a)){
						reset();
						inMisc = true;
						changeScreen = true;
					}
					mouseHandler.mouse.toggle(false);
				}
			}else{
				changeScreen = false;
			}
		}
	}
}