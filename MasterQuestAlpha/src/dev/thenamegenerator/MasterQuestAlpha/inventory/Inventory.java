package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;

public class Inventory {
	
	private InputHandler input;
	private MouseHandler mouseHandler;
	
	BufferedImage inven;
	
	public boolean renderInventory = false;
	
	public boolean inStats, inMap, inWeapons, inArmor, inPotion, inMagic, inMisc;
	
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
				if(mouseHandler.mouse.x >=(24+32) && mouseHandler.mouse.x <= (59+32)){
					if(mouseHandler.mouse.y >= (19+32) && mouseHandler.mouse.y <= (46+32)){
						reset();
						inStats = true;
					}else if(mouseHandler.mouse.y >= (53+32) && mouseHandler.mouse.y <= (80+32)){
						reset();
						inMap = true;
					}else if(mouseHandler.mouse.y >= (88+32) && mouseHandler.mouse.y <= (115+32)){
						reset();
						inWeapons = true;
					}else if(mouseHandler.mouse.y >= (122+32) && mouseHandler.mouse.y <= (149+32)){
						reset();
						inArmor = true;
					}else if(mouseHandler.mouse.y >= (156+32) && mouseHandler.mouse.y <= (183+32)){
						reset();
						inPotion = true;
					}else if(mouseHandler.mouse.y >= (190+32) && mouseHandler.mouse.y <= (217+32)){
						reset();
						inMagic = true;
					}else if(mouseHandler.mouse.y >= (224+32) && mouseHandler.mouse.y <= (251+32)){
						reset();
						inMisc = true;
					}
					mouseHandler.mouse.toggle(false);
				}
			}
		}
	}
}