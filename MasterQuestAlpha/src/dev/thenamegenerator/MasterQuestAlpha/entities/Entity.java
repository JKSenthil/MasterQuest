package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.awt.image.BufferedImage;
import java.util.Random;

import dev.thenamegenerator.MasterQuestAlpha.collision.Collision;

public class Entity{
	
	protected int startX, startY;
	protected int x, y;
	protected int worldX, worldY;
	protected int dispositionX, dispositionY;
	protected int dx, dy;
	protected int prevWorldX;
	protected int prevWorldY;
	
	protected double health;
	protected double magic;
	protected double speed;
	protected String name;
	
	protected BufferedImage left, right, up, down;
	protected BufferedImage currentSprite;
	
	protected boolean isMoving = false;
	protected boolean isOnScreen;
	protected boolean isAlive = true;
	
	protected long time;
	protected long endTime = 0;
	
	protected int direction = 1;
	
	public Entity(double health, double speed, String name){
		this.health = health;
		this.speed = speed;
		this.name = name;
	}
	
	public Entity(){
		time = System.nanoTime();
	}
	
	public void initSprites(BufferedImage left, BufferedImage right, BufferedImage down, BufferedImage up){
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}
	
	public void setLocation(int x, int y){
		this.worldX = x;
		this.worldY = y;
		
		startX = worldX;
		startY = worldY;
		
		this.x = x;
		this.y = y;
	}
	
	public int getStartX(){
		return startX;
	}
	
	public int getStartY(){
		return startY;
	}
	
	public void setSprite(BufferedImage currentSprite){
		this.currentSprite = currentSprite;
	}
	
	public void resetPreviousLocation(){
		dx = -1*dx;
		dy = -1*dy;
		updateAll();
	}
	
	public void updateAll(){
		worldX += dx;
		worldY += dy;
		x += dx;
		y += dy;
		dispositionX += dx;
		dispositionY += dy;
	}
	
	public void move(){
		if(!isMoving){
			Random rand = new Random();
			int temp = rand.nextInt(20);
			if(temp == 7){
				isMoving = true;
				temp = rand.nextInt(4);
				if(temp == 0){
					dx = 2;
					if(checkCollision(worldX + 32, worldY)){dx = 0; isMoving = false;}	
					setSprite(right);
					direction = 1;
				}
				if(temp == 1){
					dx = -2;
					if(checkCollision(worldX - 32, worldY)){dx = 0; isMoving = false;}	
					setSprite(left);
					direction = 0;
				}
				if(temp == 2){
					dy = 2;
					if(checkCollision(worldX, worldY + 32)){dy = 0; isMoving = false;}	
					setSprite(down);
					direction = 2;
				}
				if(temp == 3){
					dy = -2;
					if(checkCollision(worldX, worldY - 32)){dy = 0; isMoving = false;}	
					setSprite(up);
					direction = 3;
				}
				time = System.nanoTime();
				prevWorldX = worldX;
				prevWorldY = worldY;
			}
		}
		if(isMoving){
			endTime = System.nanoTime();
			if(endTime - time >= 45000000/speed){
				updateAll();
				if((worldX - prevWorldX) >= 32 || (worldX - prevWorldX) <= -32 || (worldY - prevWorldY) >= 32 || (worldY - prevWorldY) <= -32){
					dx = 0;
					dy = 0;
					isMoving = false;
				}
				time = endTime;
			}
		}
	}
	
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x){
    	this.x = x;
    }
    
    public void setY(int y){
    	this.y = y;
    }
    
    public int getWorldX(){
    	return worldX;
    }
    
    public int getWorldY(){
    	return worldY;
    }
    
    public void setWorldX(int x){
    	this.worldX = x;
    }
    
    public void setWorldY(int y){
    	this.worldY = y;
    }
    
    public int getDispositionX(){
    	return dispositionX;
    }
    
    public int getDispositionY(){
    	return dispositionY;
    }
    
    public int getDX() {
        return dx;
    }

    public int getDY() {
        return dy;
    }
    
    public void reset(){
    	dx = 0;
    	dy = 0;
    }
    
    public void setHealth(double health){
    	this.health = health;
    }
    
    public double getHealth(){
    	return health;
    }
    
    public BufferedImage getImage(){
    	return currentSprite;
    }
  
    public double getMagic(){
    	return magic;
    }
    
    public void setMagic(double magic){
    	this.magic = magic;
    }
    
    public boolean checkCollision(int x, int y){
    	return Collision.checkCollisionX(x, y);
    }
    
    public int getDirection() {
		return direction;
	}
    
    public void setDirection(int direction){
    	this.direction = direction;
    }
    
}