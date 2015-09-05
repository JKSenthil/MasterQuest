package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.combat.Combat;
import dev.thenamegenerator.MasterQuestAlpha.display.Board;
import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet02Move;

public class MainPlayer extends Entity{
	
    private InputHandler input;
    
    protected boolean hasHair = false;
    protected BufferedImage[] hair = {Assets.redHairLeft, Assets.redHairRight, Assets.redHairDown, Assets.redHairUp};
    
    protected boolean hasArmor = false;
    protected BufferedImage[] armor = {Assets.orangeRobeLeft, Assets.orangeRobeRight, Assets.orangeRobeDown, Assets.orangeRobeUp};
    
    protected boolean hasWeapon = false;
    protected BufferedImage[] weapon = {Assets.lSword, Assets.rSword, Assets.rSword, Assets.rSword};
    
    protected boolean isAttacking = false;
    protected BufferedImage[] slash = {Assets.lAttack, Assets.rAttack, Assets.dAttack, Assets.uAttack};
    
    protected String userName;
    
    protected int classNumber;
    protected double strengthStat;
    protected double guardStat;
    protected int level = 25;
    
    protected BufferedImage[] body = new BufferedImage[4];
    
    protected Rectangle rect;
    
    public MainPlayer(InputHandler input, String userName) {
		this.input = input;
		initSprites(Assets.playerLeft, Assets.playerRight, Assets.playerDown, Assets.playerUp);
		
		body[0] = left;
		body[1] = right;
		body[2] = down;
		body[3] = up;
		
		setLocation(480, 480);
		setSprite(right);
		time = System.nanoTime();
		speed = 5;
		this.userName = userName;
		
		direction = 1;
		
		hasHair = true;
		hasArmor = true;
		hasWeapon = true;
		
		//calculate player stats
		health = 100;
		magic = 25;
		classNumber = 1;
		strengthStat = Combat.calcStrength(level, classNumber);
		guardStat = Combat.calcGuard(level, classNumber);
	}
    
    public boolean isAttacking(){
    	return isAttacking;
    }
    
    public void setAttackingTrue(){
    	isAttacking = true;
    }
    
    public void setAttackingFalse(){
    	isAttacking = false;
    }
    
    public int getDirection(){
    	return direction;
    }
    
    public BufferedImage[] getHair(){
    	return hair;
    }
    
    public BufferedImage[] getArmor(){
    	return armor;
    }
    
    public BufferedImage[] getWeapon(){
    	return weapon;
    }
    
    public boolean hasHair(){
    	return hasHair;
    }
    
    public boolean hasArmor(){
    	return hasArmor;
    }
    
    public boolean hasWeapon(){
    	return hasWeapon;
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
            		dy = -4;
            		isMoving = true;
            		if(isBothDirectionsUsed()) {dx = 0;}
            		if(checkCollision(worldX, worldY - 32)){dy = 0; isMoving = false;}	
            		setSprite(up);
            		direction = 3;
    			}
    			if(input.down.isPressed()){
            		dy = 4;
            		isMoving = true;
            		if(isBothDirectionsUsed()) {dx = 0;}
            		if(checkCollision(worldX, worldY + 32)){dy = 0; isMoving = false;}	
            		setSprite(down);
            		direction = 2;
    			}
    			if(input.left.isPressed()){
            		dx = -4;
            		isMoving = true;
            		if(isBothDirectionsUsed()) {dy = 0;}
            		if(checkCollision(worldX - 32, worldY)){dx = 0; isMoving = false;}	
            		setSprite(left);
            		direction = 0;
    			}
    			if(input.right.isPressed()){
            		dx = 4;
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
    		Packet02Move packet = new Packet02Move(this.userName, this.worldX, this.worldY, this.direction);
    		packet.writeData(Board.board.socketClient);
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
    
    public String getUsername(){
    	return userName;
    }
    
    public void setRect(){
    	rect = new Rectangle(worldX, worldY, 32, 32);
    }
    
    public void setBigRect(){
		rect = new Rectangle(worldX, worldY, 40, 40);
	}
    
    public Rectangle getRect(){
    	return rect;
    }
    
    public int getClassNumber(){
    	return classNumber;
    }
}