package dev.thenamegenerator.MasterQuestAlpha.collision;

import dev.thenamegenerator.MasterQuestAlpha.world.World;

public class Collision {
	
	public static int[] BlockX;
	public static int[] BlockY;
	
	public static int[][] currentWorld = World.StartMapTwo;
	
	public static int[] blocksNotToTouch = {1,4,6,7};
	
	public static void init(int[][] world){
		for(int y = 0; y < world.length; y++){
			for(int x = 0; x < world[0].length; x++){
				for(int z = 0; z<blocksNotToTouch.length; z++){
					if(world[y][x] == blocksNotToTouch[z]){
						World.numOfCollideBlocks++;
						break;
					}
				}
			}
		}
		BlockX = new int[World.numOfCollideBlocks];
		BlockY = new int[World.numOfCollideBlocks];
	}
	
	public static void loadCollision(int[][] world){
		
		int index = 0;
		
		for(int y = 0; y < world.length; y++){
			for(int x = 0; x < world[0].length; x++){
				for(int z = 0; z < blocksNotToTouch.length; z++){
					if(world[y][x] == blocksNotToTouch[z]){
						BlockX[index] = x * 32;
						BlockY[index] = y * 32;
						index++;
						break;
					}
				}
			}
		}
	}

	public static boolean checkCollisionX(int x, int y){
		boolean collides = false;
		
		int indexX = x/32;
		int indexY = y/32;
		
		for(int z = 0; z < blocksNotToTouch.length; z++){
			if(currentWorld[indexY][indexX] == blocksNotToTouch[z]){
				collides = true;
				break;
			}
		}
		
		return collides;
	}
	
}