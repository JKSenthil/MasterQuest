package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.util.ArrayList;
import java.util.Random;

public class EntityManager {

	private ArrayList<Entity> animals;
	private ArrayList<Entity> npc;
	private ArrayList<Entity> mobs;
	public ArrayList<PlayerMP> players;
	
	public EntityManager(){
		animals = new ArrayList<Entity>();
		npc = new ArrayList<Entity>();
		mobs = new ArrayList<Entity>();
		players = new ArrayList<PlayerMP>();
	}
	
	public void spawnAnimals(){
		Random rand = new Random();
		for(int c = 0; c < 15; c++){
			int x = rand.nextInt(120);
			int y = rand.nextInt(68);
			animals.add(new Chicken());
			animals.get(c).setLocation(x*32, y*32);
			while(animals.get(c).checkCollision(x*32, y*32)){
				x = rand.nextInt(120);
				y = rand.nextInt(68);
				animals.get(c).setLocation(x*32, y*32);
			}
		}
	}
	
	public ArrayList<Entity> getAnimals(){
		return animals;
	}
	
	public void addAnimal(Entity e){
		animals.add(e);
	}
	
	public void addNpc(Entity e){
		npc.add(e);
	}
	
	public void addMob(Entity e){
		mobs.add(e);
	}
	
	public void removeAnimal(){
		
	}
	
	public void tick(){
		for(int i = 0; i < animals.size(); i++){
			animals.get(i).move();
		}
	}

	public void removePlayers(String username) {
		int index = 0;
		for(PlayerMP p : players){
			if(p.getUsername().equalsIgnoreCase(username)){
				break;
			}
			index++;
		}
		players.remove(index);
	}
	
	private int getPlayerMPIndex(String username) {
		int index = 0;
		if(this.players.size() != 0){
			for(PlayerMP p : players){
				if(p.getUsername().equalsIgnoreCase(username)){
					break;
				}
				index++;
			}
		}
		return index;
	}
	
	public void movePlayer(String userName, int x, int y, int direction){
		int index = getPlayerMPIndex(userName);
		this.players.get(index).setWorldX(x);
		this.players.get(index).setWorldY(y);
		this.players.get(index).setDirection(direction);
		this.players.get(index).setSprite(this.players.get(index).body[direction]);
	}
	
	public void addPlayerMP(PlayerMP p){
		this.players.add(p);
	}
	
}