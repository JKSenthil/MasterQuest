package dev.thenamegenerator.MasterQuestAlpha.display;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.collision.Collision;
import dev.thenamegenerator.MasterQuestAlpha.entities.Chicken;
import dev.thenamegenerator.MasterQuestAlpha.entities.MainPlayer;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.world.MovingMap;
import dev.thenamegenerator.MasterQuestAlpha.world.World;
import dev.thenamegenerator.MasterQuestAlpha.world.WorldRenderer;

public class GameManager extends Canvas{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainPlayer player;
	private WorldRenderer renderer;
	private Collision collision;
	private MovingMap camera;
	private Chicken chicken;
	private InputHandler input;
	
	private BufferedImage world;
	
	public GameManager(InputHandler input){
		this.input = input;
	}
	
	public void init(){
		player = new MainPlayer(input);
		chicken = new Chicken();
		camera = new MovingMap(0,0);
		renderer = new WorldRenderer();
		collision = new Collision(World.StartMap);
		
		
		collision.initCollision();
		renderer.renderWorld(World.StartMap);
		world = renderer.getImage();
		camera.setImage(world);
	}
	
	public MainPlayer getPlayer(){
		return player;
	}
	
	public Chicken getChicken(){
		return chicken;
	}
	
	public MovingMap getCamera(){
		return camera;
	}
	
	public void tick(){
		camera.setX(-1*player.getWorldX()+224);
		camera.setY(-1*player.getWorldY()+128);
		
		player.move();
		chicken.move();
		
		chicken.setX(camera.getX() + 224 + chicken.getDispositionX());
		chicken.setY(camera.getY() + 128 + chicken.getDispositionY());
	}
	
}
