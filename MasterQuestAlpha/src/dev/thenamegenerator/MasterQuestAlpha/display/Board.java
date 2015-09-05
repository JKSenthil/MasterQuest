package dev.thenamegenerator.MasterQuestAlpha.display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dev.thenamegenerator.MasterQuestAlpha.entities.PlayerMP;
import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseWheelHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.WindowHandler;
import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet00Login;

public class Board extends Canvas implements Runnable
{

	private static final long serialVersionUID = 1L;

	public static Board board;
	
    public InputHandler input;
    private MouseHandler mouseHandler;
    private MouseWheelHandler mouseWheelHandler;
    
    private GameManager manager;
    
    private Graphics2D pic2D;
    
    private BufferedImage background;
    boolean backgroundDo = true;
    
    boolean gSet = false;
    
    public Thread thread;

    private int width = 480 + 64;
    private int height = 374 + 64;
    
    private int playerX = 224 + 32, playerY = 128 + 32;
    
    public static boolean running = false;
    
    private int frames = 0;
    private int ticks = 0;
    
    public int actualFrame = 0;
    public int actualTicks = 0;
    
    public JFrame frame;

    public boolean multiplayer = false;
    public GameClient socketClient;
    public GameServer socketServer;
    
    public String userName;
    
    private Packet00Login loginPacket;
    
    public WindowHandler windowHandler;
    
    public Board(){
    	frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(width, height);
    	frame.setLocationRelativeTo(null);
    	frame.setTitle("MasterQuestAlpha");
    	frame.setResizable(false);
    	frame.add(this);
    	frame.setVisible(true);
    	
    	background = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    	
    	pic2D = background.createGraphics();
    	
    	setFocusable(true);
    	input = new InputHandler(this);
    	mouseHandler = new MouseHandler(this);
    	mouseWheelHandler = new MouseWheelHandler(this);
    	windowHandler = new WindowHandler(this);
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
    	board = this;
    	
    	Assets.init2();
    	
    	userName = JOptionPane.showInputDialog(this, "Enter username");
    	
        manager = new GameManager(input, mouseHandler, mouseWheelHandler, userName);
        manager.init();
		
		if(JOptionPane.showConfirmDialog(this, "Would you like to run a server") == 0){
			socketServer = new GameServer(this);
			socketServer.serverOpen = true;
			socketServer.start();
	    	
	    	socketClient = new GameClient(this, "localhost");
			socketClient.start();
		}else{
			String message = JOptionPane.showInputDialog("Enter IP of server");
			socketClient = new GameClient(this, message);
			socketClient.start();
		}
        loginPacket = new Packet00Login(userName);
    	loginPacket.writeData(this.socketClient);
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
		
		g.setColor(Color.white);
		Font font = new Font("Soup of Justice", Font.BOLD, 17);
		g.setFont(font);
		
		if(!manager.getInventoryManager().onInventoryScreen()){
			g.drawImage(manager.getWorldManager().getCamera().getMapImage(), manager.getWorldManager().getCamera().getX(), manager.getWorldManager().getCamera().getY(), this);
			g.drawImage(manager.getPlayer().getImage(), playerX, playerY, this);
			if(manager.getPlayer().hasHair()){
				g.drawImage(manager.getPlayer().getHair()[manager.getPlayer().getDirection()], playerX + 9, playerY + 3, this);
			}
			if(manager.getPlayer().hasArmor()){
				g.drawImage(manager.getPlayer().getArmor()[manager.getPlayer().getDirection()], playerX, playerY, this);
			}
			if(manager.getPlayer().hasWeapon()){
				if(manager.getPlayer().getDirection() == 2){
					g.drawImage(manager.getPlayer().getWeapon()[manager.getPlayer().getDirection()], playerX - 11, playerY + 4, this);
				}else if(manager.getPlayer().getDirection() == 3){
					g.drawImage(manager.getPlayer().getWeapon()[manager.getPlayer().getDirection()], playerX - 9, playerY + 4, this);
				}else{
					g.drawImage(manager.getPlayer().getWeapon()[manager.getPlayer().getDirection()], playerX, playerY, this);
				}			
			}
			if(manager.getPlayer().isAttacking()){
				if(manager.getPlayer().getDirection() == 0){
					g.drawImage(Assets.lAttack, playerX - 32, playerY - 32, this);
				}else if(manager.getPlayer().getDirection() == 1){
					g.drawImage(Assets.rAttack, playerX + 32, playerY - 32, this);
				}else if(manager.getPlayer().getDirection() == 2){
					g.drawImage(Assets.dAttack, playerX - 32, playerY + 32, this);
				}else if(manager.getPlayer().getDirection() == 3){
					g.drawImage(Assets.uAttack, playerX - 32, playerY - 32, this);
				}
			}
			for(int i = 0; i < manager.getEntityManager().getAnimals().size(); i++){
				g.drawImage(manager.getEntityManager().getAnimals().get(i).getImage(), manager.getEntityManager().getAnimals().get(i).getX(), manager.getEntityManager().getAnimals().get(i).getY(), this);
			}
			for(int i = 0; i < manager.getEntityManager().players.size(); i++){
				g.drawImage(manager.getEntityManager().players.get(i).getImage(), manager.getEntityManager().players.get(i).getX(), manager.getEntityManager().players.get(i).getY(), this);
				g.drawString(manager.getEntityManager().players.get(i).getUsername(), manager.getEntityManager().players.get(i).getX(), manager.getEntityManager().players.get(i).getY());
			}
			g.drawImage(manager.getStatusBar().getHealth(), 10, 325+64, this);
			g.drawImage(manager.getStatusBar().getMagic(), 10, 335+64, this);
			for(int i = 0; i < manager.getMagicMP().size(); i++){
				g.drawImage(manager.getMagicMP().get(i).getIcon(), manager.getMagicMP().get(i).getX(), manager.getMagicMP().get(i).getY(), this);
			}
			for(PlayerMP p : manager.getEntityManager().players){
				if(p.isAttacking()){
					if(p.getDirection() == 0){
						g.drawImage(Assets.lAttack, p.getX() - 32, p.getY() - 32, this);
					}else if(p.getDirection() == 1){
						g.drawImage(Assets.rAttack, p.getX()+ 32, p.getY() - 32, this);
					}else if(p.getDirection() == 2){
						g.drawImage(Assets.dAttack, p.getX() - 32, p.getY() + 32, this);
					}else if(p.getDirection() == 3){
						g.drawImage(Assets.uAttack, p.getX() - 32, p.getY() - 32, this);
					}
				}
			}
			//render the usernames on the player heads
			if(manager.getPlayer().getUsername() != null){
				g.drawString(manager.getPlayer().getUsername(), playerX, playerY - 10);
			}
			backgroundDo = true;
		}
		if(manager.getInventoryManager().onInventoryScreen()){
			if(backgroundDo){
				pic2D.fillRect(0, 0, width, height);
				pic2D.drawImage(manager.getWorldManager().getCamera().getMapImage(), manager.getWorldManager().getCamera().getX(), manager.getWorldManager().getCamera().getY(), this);
				pic2D.drawImage(manager.getPlayer().getImage(), manager.getPlayer().getX(), manager.getPlayer().getY(), this);
				backgroundDo = false;
			}
			g.drawImage(background, 0, 0, this);
			g.drawImage(manager.getInventoryManager().getInventory().getImage(), 64, 64, this);
			if(manager.getInventoryManager().getInventory().inMap){
				g.drawImage(manager.getInventoryManager().getInventoryWorldMap().getRealWorldMap(), 128 + 32, 83 + 32, this);
			}else{
				g.drawImage(manager.getInventoryManager().getInventoryScrollScreen().getScrollScreen(), 128 + 32, 83 + 32, this);
				if(manager.getInventoryManager().isItemSelected()){
					g.drawString(manager.getInventoryManager().getDescription(), 332+32, 260+32);
					g.drawImage(manager.getInventoryManager().getAction().getEquip(), 342+32, 90+32, this);
					g.drawImage(manager.getInventoryManager().getAction().getTrash(), 342+32, 130+32, this);
				}
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
				actualFrame = frames;
				actualTicks = ticks;
				frame.setTitle("MasterQuestAlpha " + "FPS: " + frames + " Ticks: " + ticks);
		        socketClient.sendData("ping".getBytes());
				frames = 0;
				ticks = 0;
			}
		}
		stop();
    }
    
    public GameManager getManager(){
    	return manager;
    }

    public static void main(String[] args){
    	Board board = new Board();
    	board.start();
    }
} 