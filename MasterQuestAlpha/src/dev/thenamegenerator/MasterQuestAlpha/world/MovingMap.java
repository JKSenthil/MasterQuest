package dev.thenamegenerator.MasterQuestAlpha.world;

import java.awt.image.BufferedImage;

public class MovingMap {

	int x, y;
	int prevX, prevY;
	int width, height;
	int differenceX, differenceY;
	BufferedImage image;
	
	public MovingMap(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		prevX = x;
		this.x = x;
		differenceX = (x - prevX);
	}
	
	public void setY(int y){
		prevY = y;
		this.y = y;
		differenceY = (y - prevY);
	}
	
	public int getPrevX(){
		return prevX;
	}
	
	public int getPrevY(){
		return prevY;
	}
	
	public int getXDifference(){
		return differenceX;
	}
	
	public int getYDifference(){
		return differenceY;
	}

	public BufferedImage getMapImage() {
		return image;
	}
	
}
