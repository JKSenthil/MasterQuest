package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;

public class InventoryAction {
	
	private BufferedImage equip;
	private BufferedImage trash;
	
	private boolean equipPressed;
	private boolean trashPressed;
	
	private MouseHandler e;
	
	public InventoryAction(MouseHandler e){
		this.e = e;
		
		equip = Assets.equipButton;
		trash = Assets.trashButton;
		
		equipPressed = false;
		trashPressed = false;
	}
	
	public void tick(){
		
	}

	public void setEquipPressed(boolean equipPressed) {
		this.equipPressed = equipPressed;
	}

	public void setTrashPressed(boolean trashPressed) {
		this.trashPressed = trashPressed;
	}

	public BufferedImage getEquip() {
		return equip;
	}

	public BufferedImage getTrash() {
		return trash;
	}

	public boolean isEquipPressed() {
		return equipPressed;
	}

	public boolean isTrashPressed() {
		return trashPressed;
	}
	
}
