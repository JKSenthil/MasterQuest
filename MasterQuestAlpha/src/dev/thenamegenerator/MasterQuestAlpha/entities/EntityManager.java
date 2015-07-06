package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.util.ArrayList;

public class EntityManager {

	ArrayList<Entity> animals;
	ArrayList<Entity> npc;
	ArrayList<Entity> mobs;
	
	public EntityManager(){
		animals = new ArrayList<Entity>();
		npc = new ArrayList<Entity>();
		mobs = new ArrayList<Entity>();
	}
	
	public void spawnAnimal(){
		
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
