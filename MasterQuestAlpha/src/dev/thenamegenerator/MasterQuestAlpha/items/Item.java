package dev.thenamegenerator.MasterQuestAlpha.items;

import java.awt.image.BufferedImage;

public class Item {
	
	protected int id;
	protected String name;
	protected double weight;
	protected int value;
	
	protected BufferedImage icon;
	
	public void setId(int id){
		this.id = id;
	}
	
}
