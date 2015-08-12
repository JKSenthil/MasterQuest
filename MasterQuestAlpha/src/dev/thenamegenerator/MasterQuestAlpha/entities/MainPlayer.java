package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;

public class MainPlayer extends Entity{
	
    private InputHandler input;
    
    private boolean hasHair = false;
    private BufferedImage[] hair = {Assets.pinkHairLeft, Assets.pinkHairRight, Assets.pinkHairDown, Assets.pinkHairUp};
    
    private int direction = 1;
    
    public MainPlayer(InputHandler input) {
		this.input = input;
		initSprites(Assets.playerLeft, Assets.playerRight, Assets.playerDown, Assets.playerUp);
		setLocation(480, 480);
		setSprite(right);
		time = System.nanoTime();
		speed = 5;
		
		hasHair = true;
	}
    
    public int getDirection(){
    	return direction;
    }
    
    public BufferedImage[] getHair(){
    	return hair;
    }
    
    public boolean hasHair(){
    	return hasHair;
    }
 
    public boolean isBothDirectionsUsed(){
		boolean info = false;
		if((dx != 0) && (dy != 0)){
			info = true;
		}
		return info;
	}
    
    @Override
    public void move() {
    	if(!isMoving){
    		if(input != null){
    			if(input.up.isPressed()){
            		dy = -8;
            		isMoving = true;
            	if(isBothDirectionsUsed()) {dx = 0;}
            	if(checkCollision(worldX, worldY - 32)){dy = 0; isMoving = false;}	
            	setSprite(up);
            	direction = 3;
            }
    			if(input.down.isPressed()){
            		dy = 8;
            		isMoving = true;
            	if(isBothDirectionsUsed()) {dx = 0;}
            	if(checkCollision(worldX, worldY + 32)){dy = 0; isMoving = false;}	
            	setSprite(down);
            	direction = 2;
            }
    			if(input.left.isPressed()){
            		dx = -8;
            		isMoving = true;
            	if(isBothDirectionsUsed()) {dy = 0;}
            	if(checkCollision(worldX - 32, worldY)){dx = 0; isMoving = false;}	
            	setSprite(left);
            	direction = 0;
            }
    			if(input.right.isPressed()){
            		dx = 8;
            		isMoving = true;
            	if(isBothDirectionsUsed()) {dy = 0;}
            	if(checkCollision(worldX + 32, worldY)){dx = 0; isMoving = false;}	
            	setSprite(right);
            	direction = 1;
            }
    	}
    		time = System.nanoTime();
            prevWorldX = worldX;
            prevWorldY = worldY;
    }
    	if(isMoving){
    		endTime = System.nanoTime();
    		if(endTime - time >= 45000000/speed){
    			worldX += dx;
        		worldY += dy;
        		if((worldX - prevWorldX) >= 32 || (worldX - prevWorldX) <= -32 || (worldY - prevWorldY) >= 32 || (worldY - prevWorldY) <= -32){
    				dx = 0;
    				dy = 0;
    				isMoving = false;
    			}
        		time = endTime;
    		}
    	}
    }
    
    @Override
    public void resetPreviousLocation(){
		dx = -1*dx;
		dy = -1*dy;
		worldX+=dx;
		worldY+=dy;
		dx = 0;
		dy = 0;
	}

}
