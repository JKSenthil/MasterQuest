package dev.thenamegenerator.MasterQuestAlpha.items;

import java.awt.image.BufferedImage;

public class Armor extends Item{
	
	protected int defense;
	
	protected BufferedImage[] sprites;
	
	public Armor(){
		type = "armor";
	}
	
	public int getDefense(){
		return defense;
	}
	
	public BufferedImage[] getSprites(){
		return sprites;
	}
	
}
