package dev.thenamegenerator.MasterQuestAlpha.collision;

public class Collision {
	
	public static int[][] currentWorld;
	
	public static int[] blocksNotToTouch = {1,4,6,7};
	
	public static void init(int[][] world){
		currentWorld = world;
	}
	
	public static void loadCollision(int[][] world){
		currentWorld = world;
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