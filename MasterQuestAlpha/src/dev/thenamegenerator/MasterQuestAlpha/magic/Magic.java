package dev.thenamegenerator.MasterQuestAlpha.magic;

import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.collision.Collision;
import dev.thenamegenerator.MasterQuestAlpha.entities.MainPlayer;

public class Magic {
	
	protected BufferedImage icon;
	
	protected int x, y;
	protected int worldX, worldY;
	protected int dx, dy;
	
	protected int magicRequired;
	
	protected long time;
	protected long endTime;
	
	protected int distance;
	
	protected MainPlayer player;
	
	public boolean checkCollision(int x, int y){
		return Collision.checkMagicCollision(x, y);
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}

	public BufferedImage getIcon(){
		return icon;
	}
	
	public int getMagicRequired(){
		return magicRequired;
	}
	
	public MainPlayer getPlayer(){
		return player;
	}
}
