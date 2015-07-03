package dev.thenamegenerator.MasterQuestAlpha.display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseWheelHandler;

public class Board extends Canvas implements Runnable
{

	private static final long serialVersionUID = 1L;

    private InputHandler input;
    private MouseHandler mouseHandler;
    private MouseWheelHandler mouseWheelHandler;
    
    private GameManager manager;
    
    Graphics g2d;
    
    Graphics2D pic2D;
    private BufferedImage background = new BufferedImage(480,374, BufferedImage.TYPE_BYTE_GRAY);
    boolean backgroundDo = true;
    
    public Thread thread;

    private int width = 480;
    private int height = 374;
    
    public static boolean running = false;
    
    private int frames = 0;
    private int ticks = 0;

    public Board(){
    	pic2D = background.createGraphics();
    	pic2D.setColor(Color.WHITE);
    	
    	setFocusable(true);
    	input = new InputHandler(this);
    	mouseHandler = new MouseHandler(this);
    	mouseWheelHandler = new MouseWheelHandler(this);
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
        manager = new GameManager(input, mouseHandler, mouseWheelHandler);
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
		if(!manager.getInventoryManager().onInventoryScreen()){
			g.drawImage(manager.getWorldManager().getCamera().getMapImage(), manager.getWorldManager().getCamera().getX(), manager.getWorldManager().getCamera().getY(), this);
			g.drawImage(manager.getPlayer().getImage(), manager.getPlayer().getX(), manager.getPlayer().getY(), this);
			g.drawImage(manager.getChicken().getImage(), manager.getChicken().getX(),manager.getChicken().getY(), this);			
			g.drawImage(manager.getElephant().getImage(), manager.getElephant().getX(), manager.getElephant().getY(), this);
			g.drawImage(manager.getSpider().getImage(), manager.getSpider().getX(), manager.getSpider().getY(), this);
			backgroundDo = true;
		}
		if(manager.getInventoryManager().onInventoryScreen()){
			if(backgroundDo){
				pic2D.fillRect(0, 0, 480, 374);
				pic2D.drawImage(manager.getWorldManager().getCamera().getMapImage(), manager.getWorldManager().getCamera().getX(), manager.getWorldManager().getCamera().getY(), this);
				pic2D.drawImage(manager.getPlayer().getImage(), manager.getPlayer().getX(), manager.getPlayer().getY(), this);
				pic2D.drawImage(manager.getChicken().getImage(), manager.getChicken().getX(),manager.getChicken().getY(), this);			
				pic2D.drawImage(manager.getElephant().getImage(), manager.getElephant().getX(), manager.getElephant().getY(), this);
				pic2D.drawImage(manager.getSpider().getImage(), manager.getSpider().getX(), manager.getSpider().getY(), this);
				backgroundDo = false;
			}
			g.drawImage(background, 0, 0, this);
			g.drawImage(manager.getInventoryManager().getInventory().getImage(), 32, 32, this);
			g.drawImage(manager.getInventoryManager().getInventoryScrollScreen().getScrollScreen(), 128, 83, this); //Original at 91
			if(manager.getInventoryManager().getItemSelected()){
				g.drawString(manager.getInventoryManager().getDescription(), 332, 260);
			}
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