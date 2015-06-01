package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.items.Item;

public class InventoryScrollScreen {
	
	public BufferedImage listOfItems;
	
	Graphics g2d;
	
	public int width = 200;
	public int height = 6*32;
	
	public int reference = 0;
	
	public MouseHandler mouseHandler;
	
	public InventoryScrollScreen(MouseHandler mouseHandler){
		listOfItems = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = listOfItems.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		this.mouseHandler = mouseHandler;
	}
	
	public void addItem(Item item){
			g2d.drawImage(item.getIcon(), 0, reference, null);
			reference+=32;
			g2d.setColor(Color.BLACK);
			g2d.drawString(item.getName(), 30, reference-11);
			g2d.drawLine(0, reference, width, reference);
	}
	
	public BufferedImage getScrollScreen(){
		return listOfItems;
	}
	
	public void reset(){
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, width, height);
		reference = 0;
	}
	
}