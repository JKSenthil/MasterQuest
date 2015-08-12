package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.util.Random;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;

public class Spider extends Entity{
	
	public Spider(){
		initSprites(Assets.spider, Assets.spider, Assets.spider, Assets.spider);
		setSprite(Assets.spider);
		speed = 3;
	}
	
	@Override
	public void move(){
		if(!isMoving){
			Random rand = new Random();
			int temp = rand.nextInt(20);
			if(temp == 7){
				isMoving = true;
				temp = rand.nextInt(4);
				if(temp == 0){
					dx = 2;
					if(checkCollision(worldX + 64, worldY)){dx = 0; isMoving = false;}
					setSprite(right);
				}
				if(temp == 1){
					dx = -2;
					if(checkCollision(worldX - 32, worldY)){dx = 0; isMoving = false;}	
					setSprite(left);
				}
				if(temp == 2){
					dy = 2;
					if(checkCollision(worldX, worldY + 32)){dy = 0; isMoving = false;}	
					if(checkCollision(worldX + 32, worldY + 32)){dy = 0; isMoving = false;}	
				}
				if(temp == 3){
					dy = -2;
					if(checkCollision(worldX, worldY - 32)){dy = 0; isMoving = false;}	
					if(checkCollision(worldX + 32, worldY - 32)){dy = 0; isMoving = false;}	
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
	
}
