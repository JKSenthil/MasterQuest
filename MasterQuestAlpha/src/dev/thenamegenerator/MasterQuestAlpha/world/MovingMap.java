package dev.thenamegenerator.MasterQuestAlpha.world;

import java.awt.image.BufferedImage;

public class MovingMap {

	int x, y;
	int prevX, prevY;
	int width, height;;
	BufferedImage image;
	
	boolean secondImageInUse = false;
	
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
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public BufferedImage getMapImage() {
		return image;
	}
}