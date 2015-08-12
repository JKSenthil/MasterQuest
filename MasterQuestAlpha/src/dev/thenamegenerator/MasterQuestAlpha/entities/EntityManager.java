package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.util.ArrayList;
import java.util.Random;

public class EntityManager {

	ArrayList<Entity> animals;
	ArrayList<Entity> npc;
	ArrayList<Entity> mobs;
	
	public EntityManager(){
		animals = new ArrayList<Entity>();
		npc = new ArrayList<Entity>();
		mobs = new ArrayList<Entity>();
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
	
}