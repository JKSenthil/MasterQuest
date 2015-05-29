package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class InventoryScrollScreen {
	
	public BufferedImage listOfItems;
	Graphics2D scrollScreen;
	
	public int width = 200;
	public int height = 6*32;
	
	public InventoryScrollScreen(){
		listOfItems = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
}
