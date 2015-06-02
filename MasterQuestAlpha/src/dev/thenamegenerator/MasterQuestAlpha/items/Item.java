package dev.thenamegenerator.MasterQuestAlpha.items;

import java.awt.image.BufferedImage;

public class Item {
	
	protected int id;
	protected String name;
	protected String type;
	protected double weight;
	protected int value;
	
	protected BufferedImage icon;
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public int getValue(){
		return value;
	}
	
	public BufferedImage getIcon(){
		return icon;
	}
	
	public String getType(){
		return type;
	}
}
