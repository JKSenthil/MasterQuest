package dev.thenamegenerator.MasterQuestAlpha.world;

public class World {

	public static int xBlock = 30;
	public static int yBlock = 32;
	
	public static int numOfCollideBlocks = 0;
	
	//old map, game now uses 2D arrays
	public static int[] StartMap = {
			
			1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 
			1,1,1,0,0,0,0,0,0,7,7,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,
			1,1,1,0,0,0,0,0,0,6,4,6,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,1,1,1, 
			1,1,1,0,0,0,0,0,0,4,5,4,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,1,1,1, 
			1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,1,1,1, 
			1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1, 
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,1,1,1, 
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1, 
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1, 
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1, 
			1,1,1,0,0,0,0,0,0,7,7,7,0,0,0,0,0,7,7,7,7,7,0,0,0,0,0,1,1,1, 
			1,1,1,0,0,0,0,0,0,6,4,6,0,0,0,0,0,6,6,4,6,6,0,0,0,0,0,1,1,1, 
			1,1,1,0,0,0,0,0,0,4,5,4,0,0,0,0,0,4,4,5,4,4,0,0,0,0,0,1,1,1, 
			1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,
			1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,
			1,1,1,0,0,7,7,7,0,7,7,7,0,0,0,0,0,0,0,7,7,7,7,7,0,0,0,1,1,1,
			1,1,1,0,0,6,4,6,0,6,4,6,0,0,0,0,0,0,0,6,6,4,6,6,0,0,0,1,1,1,
			1,1,1,0,0,4,5,4,0,4,5,4,0,0,0,0,0,0,0,4,4,5,4,4,0,0,0,1,1,1,
			1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,
			1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,
			1,1,1,0,0,0,0,0,0,7,7,7,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,1,1,1,
			1,1,1,0,0,0,0,0,0,6,4,6,0,0,0,0,2,0,0,0,0,0,3,3,0,0,0,1,1,1,
			1,1,1,0,0,0,0,0,0,4,5,4,0,0,0,0,2,2,0,0,0,0,3,3,0,0,0,1,1,1,
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,1,1,1,
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,1,1,1,
			1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,
			1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1,
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,
			1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,
			1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
			
	};
	
	public static int[][] StartMapTwo = new int[][]{
		
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,0,0,0,0,0,0,7,7,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,6,4,6,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,4,5,4,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,0,0,0},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,7,7,7,0,0,0,0,0,7,7,7,7,7,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,6,4,6,0,0,0,0,0,6,6,4,6,6,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,4,5,4,0,0,0,0,0,4,4,5,4,4,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};
	
	public static int[][] ToTheRight = new int[][]{
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{0,0,0,7,7,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
		{0,0,0,6,4,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,1,1,1},
		{0,0,0,4,5,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,1,1,1},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,2,2,2,2,0,1,1,1},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,1,1,1},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
		{0,0,0,7,7,7,0,0,0,7,7,7,0,0,0,0,0,0,7,7,7,0,0,0,0,0,0,1,1,1},
		{0,0,0,6,4,6,0,0,0,6,4,6,0,0,0,0,0,0,6,4,6,0,0,0,0,0,0,1,1,1},
		{0,0,0,4,5,4,0,0,0,4,5,4,0,0,0,0,0,0,4,5,4,0,0,0,0,0,0,1,1,1},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,1,1},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};

}