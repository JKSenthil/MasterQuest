package dev.thenamegenerator.MasterQuestAlpha.display;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;

public class Board extends Canvas implements Runnable
{

	private static final long serialVersionUID = 1L;

    private InputHandler input;
    private MouseHandler mouseHandler;
    
    private GameManager manager;
    
    Graphics g2d;
    public Thread thread;

    private int width = 480;
    private int height = 374;
    
    public static boolean running = false;
    
    private int frames = 0;
    private int ticks = 0;
    
    private boolean cont = true;

    public Board(){
    	setFocusable(true);
    	input = new InputHandler(this);
    	mouseHandler = new MouseHandler(this);
    }
    
    public synchronized void start(){
		if(running)return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
    
	public synchronized void stop(){
		if(!running)return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    
    public void init(){
    	Assets.init();
        manager = new GameManager(input, mouseHandler);
        manager.init();
    }
    
    public void tick() {
    	manager.tick();
    }

    public void render(){
    	BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//CLEARS SCREEN
		g.clearRect(0, 0, width, height);
		//RENDER HERE
		g.drawImage(manager.getCamera().getMapImage(), manager.getCamera().getX(), manager.getCamera().getY(), this);
		g.drawImage(manager.getPlayer().getImage(), manager.getPlayer().getX(), manager.getPlayer().getY(), this);
		g.drawImage(manager.getChicken().getImage(), manager.getChicken().getX(),manager.getChicken().getY(), this);			
		g.drawImage(manager.getElephant().getImage(), manager.getElephant().getX(), manager.getElephant().getY(), this);
		if(manager.getInventoryManager().onInventoryScreen()){
			g.drawImage(manager.getInventoryManager().getInventory().getImage(), 32, 32, this);
			if(cont){
				for(int i = 0; i<manager.getInventoryManager().getPlayerItems().size(); i++){
					manager.getInventoryManager().getInventoryScrollScreen().addItem(manager.getInventoryManager().getPlayerItems().get(i));
				}
				cont = false;
			}
			g.drawImage(manager.getInventoryManager().getInventoryScrollScreen().listOfItems, 128, 91, this);
		}
		//END RENDER
		g.dispose();
		bs.show();
    }
    
    public void run(){
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60D;
		double ns = 1000000000D/amountOfTicks;
		double delta = 0;
		long lastTimer = System.currentTimeMillis();
		init();
		
		while(running){
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			
			while(delta>=1){
				ticks++;
				tick();	
				delta--;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			frames++;
			render();
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				System.out.println("Frames: " + frames + " Ticks: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
		stop();
    }
} 