package dev.thenamegenerator.MasterQuestAlpha.entities;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;

public class MainPlayer extends Entity{
	
    private InputHandler input;
    
    public MainPlayer(InputHandler input) {
		this.input = input;
		initSprites(Assets.playerLeft, Assets.playerRight, Assets.playerRight, Assets.playerRight);
		initLocation(224, 128, 224, 128);
		setSprite(Assets.playerRight);
		time = System.nanoTime();
		speed = 5;
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
            		dy = -2;
            		isMoving = true;
            	if(isBothDirectionsUsed()) {dx = 0;}
            	if(checkCollision(0, -32)){dy = 0; isMoving = false;}	
            }
    			if(input.down.isPressed()){
            		dy = 2;
            		isMoving = true;
            	if(isBothDirectionsUsed()) {dx = 0;}
            	if(checkCollision(0, 32)){dy = 0; isMoving = false;}	
            }
    			if(input.left.isPressed()){
            		dx = -2;
            		isMoving = true;
            	if(isBothDirectionsUsed()) {dy = 0;}
            	if(checkCollision(-32, 0)){dx = 0; isMoving = false;}	
            	setSprite(Assets.playerLeft);
            }
    			if(input.right.isPressed()){
            		dx = 2;
            		isMoving = true;
            	if(isBothDirectionsUsed()) {dy = 0;}
            	if(checkCollision(32, 0)){dx = 0; isMoving = false;}	
            	setSprite(Assets.playerRight);
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
