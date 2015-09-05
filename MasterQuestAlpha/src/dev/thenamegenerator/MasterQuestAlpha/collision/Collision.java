package dev.thenamegenerator.MasterQuestAlpha.collision;

import java.util.ArrayList;

public class Collision {
	
	public static int[][] currentWorld = new int[30*4][17*4];
	
	public static int[] blocksNotToTouch = {1,4,6,7,8,16,17,18,20,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55};
	public static int[] magicNotToTouch = {4,6,7,8,16,17,18,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51};
	
	public static void loadCollision(ArrayList<int[][]> arrayList){
		int indexX = 0;
		int indexY = 0;
		for(int c = 0; c < 16; c++){
			if(c != 0){
				indexY += 17;
			}
			if(c == 4 || c == 8 || c == 12){
				indexY = 0;
				indexX += 30;
			}
			for(int y = 0; y < 17; y++){
				for(int x = 0; x < 30; x++){
					currentWorld[indexX + x][indexY + y] = arrayList.get(c)[x][y];
				}
			}
		}
	}

	public static boolean checkCollisionX(int x, int y){
		boolean collides = false;
		
		int indexX = x/32;
		int indexY = y/32;
		
		for(int z = 0; z < blocksNotToTouch.length; z++){
			if(indexX == 0 || indexX == 120 || indexY == 0 || indexY == 68 || indexX == -1){
				collides = true;
				break;
			}
			if(currentWorld[indexX][indexY] == blocksNotToTouch[z]){
				collides = true;
				break;
			}
		}
		return collides;
	}
	
	public static boolean checkMagicCollision(double x, double y){
		boolean collides = false;
		
		int indexX = (int) (x/32);
		int indexY = (int) (y/32);
		
		for(int z = 0; z < magicNotToTouch.length; z++){
			if(indexX == 0 || indexX == 120 || indexY == 0 || indexY == 68 || indexX == -1){
				collides = true;
				break;
			}
			if(currentWorld[indexX][indexY] == magicNotToTouch[z]){
				collides = true;
				break;
			}
		}
		return collides;
	}
}