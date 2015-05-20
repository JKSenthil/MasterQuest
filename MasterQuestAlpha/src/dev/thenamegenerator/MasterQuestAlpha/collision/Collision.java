package dev.thenamegenerator.MasterQuestAlpha.collision;

import dev.thenamegenerator.MasterQuestAlpha.world.World;

public class Collision {
	
	public static int[] BlockX;
	public static int[] BlockY;
	
	int[] world = new int[World.xBlock * World.yBlock];
	
	int[] blocksNotToTouch = {1,4,6,7};
	
	public Collision(int[] world){
		for(int i = 0; i<(World.xBlock * World.yBlock); i++){
			this.world[i]=world[i];
		}
		
		for(int i = 0; i<(World.xBlock * World.yBlock); i+=World.xBlock){
			for(int x = 0; x<World.xBlock; x++){
				for(int z = 0; z<blocksNotToTouch.length; z++){
					if(world[x+i]==blocksNotToTouch[z]){
						World.numOfCollideBlocks++;
						break;
					}
				}
			}
		}
		BlockX = new int[World.numOfCollideBlocks];
		BlockY = new int[World.numOfCollideBlocks];
	}
	
	public void initCollision(){
    	int count = 0;
    	int Index = 0;
    	
    	for(int i = 0; i<(World.xBlock * World.yBlock); i+=World.xBlock){
    		for(int x = 0; x<World.xBlock; x++){
    			for(int z = 0; z<blocksNotToTouch.length; z++){
					if(world[x+i]==blocksNotToTouch[z]){
						BlockX[Index] = 32*x;
	    				BlockY[Index] = 32*count;
	    				Index++;
						break;
					}
				}
    		}
    		count++;
    	}
    	count = 0;
    }
	
	public boolean checkCollisionX(int x, int y){
		boolean collides = false;
		for(int i = 0; i<World.numOfCollideBlocks; i++){
			if(x == BlockX[i] && y == BlockY[i]){
				collides = true;
				break;
			}
		}
		return collides;
	}

	public void updateBlocks(int x, int y){
		for(int i = 0; i<World.numOfCollideBlocks; i++){
			BlockX[i] += x;
			BlockY[i] += y;
		}
	}
}