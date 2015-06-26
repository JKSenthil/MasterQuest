package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseWheelHandler;
import dev.thenamegenerator.MasterQuestAlpha.items.Item;

public class InventoryScrollScreen {
	
	public BufferedImage listOfItems;
	
	Graphics g2d;
	
	public int width = 200;
	public int height = 6*32;
	
	public int reference = 0;
	public int reference2 = 0;
	public int scrollOffset = 0;
	public int count = 0;
	public int itemNumber = 0;
	
	public int count2 = 0;
	
	public MouseHandler mouseHandler;
	public MouseWheelHandler mouseWheelHandler;
	
	private boolean change = true;
	public boolean isSelected = false;
	
	private Font fontNormal;
	private Font fontSmall;
	
	public InventoryScrollScreen(MouseHandler mouseHandler, MouseWheelHandler mouseWheelHandler){
		listOfItems = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = listOfItems.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		
		this.mouseHandler = mouseHandler;
		this.mouseWheelHandler = mouseWheelHandler;
		
		fontNormal = new Font("Verdana", Font.PLAIN, 14);
		fontSmall = new Font("Verdana", Font.PLAIN, 10);
		g2d.setFont(fontNormal);
	}
	
	public void tick(){
		if(mouseWheelHandler.wheel.notchMoved){
			if(reference > (6*32)){
				if(mouseWheelHandler.wheel.noches < 0){
					if(scrollOffset < 0){
						scrollOffset += 8;
						mouseWheelHandler.wheel.noches = 0;
						change = true;
					}
				}else if(mouseWheelHandler.wheel.noches > 0){
					if((-1*scrollOffset) < reference - (6*32)){
						scrollOffset += -8;
						mouseWheelHandler.wheel.noches = 0;
						change = true;
					}
				}
			}
			mouseWheelHandler.wheel.notchMoved = false;
		}
		if(mouseHandler.mouse.isPressed()){
			if(mouseHandler.mouse.x >= 128 && mouseHandler.mouse.x <= 328){
				int stand = 0;
				for(int i = 0; i<(reference/32); i++){
					if(mouseHandler.mouse.y > (stand+91+scrollOffset) && mouseHandler.mouse.y < (stand+91+32+scrollOffset)){
						itemNumber = (stand/32)+1;
						isSelected = true;
						break;
					}
					stand+=32;
				}
			}else{
				isSelected = false;
			}
		}
	}
	
	public boolean isChange(){
		return change;
	}
	
	public void setChange(boolean change){
		this.change = change;
	}
	
	public void addItem(Item item){
		g2d.drawImage(item.getIcon(), 0, reference + scrollOffset, null);
		reference+=32;
		g2d.setFont(fontNormal);
		g2d.setColor(Color.BLACK);
		g2d.drawString(item.getName(), 50, reference - 11 + scrollOffset);
		g2d.setFont(fontSmall);
		g2d.drawString("x1", 27, reference - 2 + scrollOffset);
		g2d.drawLine(0, reference + scrollOffset, width, reference + scrollOffset);
		
		g2d.setFont(fontNormal);
		count++;
	}
	
	public void stackItem(Item item, int count){
		g2d.drawImage(item.getIcon(), 0, reference + scrollOffset, null);
		reference += 32;
		g2d.setColor(Color.BLACK);
		g2d.drawString(item.getName(), 50, reference - 11 + scrollOffset);
		g2d.setFont(fontSmall);
		g2d.drawString("x"+count, 27, reference - 2 + scrollOffset);
		g2d.drawLine(0, reference + scrollOffset, width, reference + scrollOffset);
		g2d.setFont(fontNormal);
	}
	
	public void selectedItem(){
		if(isSelected){
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(0, ((itemNumber-1)*32) + scrollOffset, width, 32);
		}
	}
	
	public BufferedImage getScrollScreen(){
		return listOfItems;
	}
	
	public void reset(){
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, width, height);
		reference = 0;
		count = 0;
		
		reference2 = 0;
	}
	
	public void resetScrollOffset(){
		scrollOffset = 0;
	}
	
}