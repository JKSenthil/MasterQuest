package dev.thenamegenerator.MasterQuestAlpha.filereader;

import java.util.ArrayList;
import java.util.Scanner;

public class WorldTextReader{
	
	Scanner fileReader;
	
	public ArrayList<int[][]> maps = new ArrayList<>();
	String[] textNames = {"world[0][0].txt", "world[0][1].txt", "world[0][2].txt", "world[0][3].txt",
			"world[1][0].txt", "world[1][1].txt", "world[1][2].txt", "world[1][3].txt", 
			"world[2][0].txt", "world[2][1].txt", "world[2][2].txt", "world[2][3].txt",
			"world[3][0]temp.txt", "world[3][1].txt", "world[3][2].txt", "world[3][3].txt"};
	
	public WorldTextReader(){
		fileReader = new Scanner("/world_data/world[0][0].txt");
	}
	
	public void load(){
		for(int i = 0; i < textNames.length; i++){
			int[][] tempMap = new int[30][17];
			fileReader = new Scanner(WorldTextReader.class.getResourceAsStream("/world_data/" + textNames[i]));
			for(int y = 0; y < 17; y++){
				for(int x = 0; x < 30; x++){
					tempMap[x][y] = fileReader.nextInt();
				}
			}
			maps.add(tempMap);
		}
		fileReader.close();
	}
	
	public ArrayList<int[][]> getMaps(){
		return maps;
	}
}