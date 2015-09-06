package dev.thenamegenerator.MasterQuestAlpha.display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.thenamegenerator.MasterQuestAlpha.combat.Combat;
import dev.thenamegenerator.MasterQuestAlpha.combat.StatusBar;
import dev.thenamegenerator.MasterQuestAlpha.entities.EntityManager;
import dev.thenamegenerator.MasterQuestAlpha.entities.MainPlayer;
import dev.thenamegenerator.MasterQuestAlpha.entities.PlayerMP;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;
import dev.thenamegenerator.MasterQuestAlpha.input.MouseWheelHandler;
import dev.thenamegenerator.MasterQuestAlpha.inventory.InventoryManager;
import dev.thenamegenerator.MasterQuestAlpha.magic.Firebolt;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet02Move;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet06MagicDisconnect;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet07Damage;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet10Slash;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet11Dead;
import dev.thenamegenerator.MasterQuestAlpha.world.WorldManager;

public class GameManager{
	
	private MainPlayer player;
	
	private InputHandler input;
	private MouseHandler mouseHandler;
	private MouseWheelHandler mouseWheelHandler;
	
	private InventoryManager inventoryManager;
	private WorldManager worldManager;
	private EntityManager entityManager;
	
	private StatusBar statusBar;
	private Dead deadScreen;
	
	private String userName;
	
	public List<Firebolt> magicMP = new ArrayList<Firebolt>();
	
	private long time;
	private long endTime;
	
	private long combatTime;
	private long combatEndTime;
	private long coolDown = 0;
	private long coolDown2 = 0;
	
	private Random rand = new Random();
	
	public List<String> names = new ArrayList<String>();
	
	public GameManager(InputHandler input, MouseHandler mouseHandler, MouseWheelHandler mouseWheelHandler, String userName){
		this.input = input;
		this.mouseHandler = mouseHandler;
		this.mouseWheelHandler = mouseWheelHandler;
		this.userName = userName;
	}
	
	public void init(){
		player = new PlayerMP(input, userName, null, -1);
		
		worldManager = new WorldManager(-1*player.getWorldX()+224+32, -1*player.getWorldY()+128+32);
		inventoryManager = new InventoryManager(input, mouseHandler, mouseWheelHandler);
		
		entityManager = new EntityManager();
		
		statusBar = new StatusBar(player.getHealth(), player.getMagic());
		deadScreen = new Dead(mouseHandler);
		deadScreen.draw();

		time = System.nanoTime();
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public WorldManager getWorldManager(){
		return worldManager;
	}
	
	public MainPlayer getPlayer(){
		return player;
	}
	
	public void setPlayer(MainPlayer player){
		this.player = player;
	}
	
	public InventoryManager getInventoryManager(){
		return inventoryManager;
	}
	
	public StatusBar getStatusBar(){
		return statusBar;
	}
	
	public List<Firebolt> getMagicMP(){
		return magicMP;
	}
	
	public Dead getScreen(){
		return deadScreen;
	}
	
	public void tick(){
		inventoryManager.tick();
		if(!inventoryManager.onInventoryScreen()){
			if(player.isAlive()){
				worldManager.getCamera().setX(-1*player.getWorldX()+224+32);
				worldManager.getCamera().setY(-1*player.getWorldY()+128+32);
				
				player.move();
				
				for(int i = 0; i < entityManager.getAnimals().size(); i++){
					entityManager.getAnimals().get(i).move();
					entityManager.getAnimals().get(i).setX(worldManager.getCamera().getX() + entityManager.getAnimals().get(i).getWorldX());
					entityManager.getAnimals().get(i).setY(worldManager.getCamera().getY() + entityManager.getAnimals().get(i).getWorldY());
				}
				for(int i = 0; i < entityManager.players.size(); i++){
					entityManager.players.get(i).setX(worldManager.getCamera().getX() + entityManager.players.get(i).getWorldX());
					entityManager.players.get(i).setY(worldManager.getCamera().getY() + entityManager.players.get(i).getWorldY());
				}
				//sets up magic stuff
				spacecode();
				for(Firebolt m : magicMP){
					if(m.username.equalsIgnoreCase(player.getUsername())){
						m.move();
					}
				}
				for(int i = 0; i < magicMP.size(); i++){
					if(magicMP.get(i).checkCollision(magicMP.get(i).getWorldX(), magicMP.get(i).getWorldY())){
						Packet06MagicDisconnect packet = new Packet06MagicDisconnect(player.getUsername(), magicMP.get(i).ID);
						packet.writeData(Board.board.socketClient);
						magicMP.remove(i);
						i--;
					}
				}
				//entity collision code and damage code
				for(int i = 0; i < magicMP.size(); i++){
					if(player.getUsername().equalsIgnoreCase(magicMP.get(i).username)){
						for(PlayerMP p : entityManager.players){
							p.setRect();
							magicMP.get(i).setRect();
							if(magicMP.get(i).getRect().intersects(p.getRect())){			
								//does damage
								Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calcMagicDamage(player.getLevel(), p.getLevel(), player.getMagic(), p.getGuardStat(), p.getHealth()));
								pDamage.writeData(Board.board.socketClient);
								//removes magic
								Packet06MagicDisconnect packet = new Packet06MagicDisconnect(player.getUsername(), magicMP.get(i).ID);
								packet.writeData(Board.board.socketClient);
								magicMP.remove(i);
								i--;
								break;
							}
						}
					}
				}
				
				for(int i = 0; i < magicMP.size(); i++){
					magicMP.get(i).setX(magicMP.get(i).getWorldX() + worldManager.getCamera().getX());
					magicMP.get(i).setY(magicMP.get(i).getWorldY() + worldManager.getCamera().getY());
				}
				magicUpdate();
				statusBar.updateStatusBars(player.getHealth(), player.getMagic());
				
				if(player.getHealth() <= 0){
					player.setAliveFalse();
					Packet11Dead packet = new Packet11Dead(player.getUsername(), player.isAlive());
					packet.writeData(Board.board.socketClient);
				}
			}else if(!player.isAlive()){
				deadScreen.tick();
				if(deadScreen.getRespawn()){
					int x = rand.nextInt(120);
					int y = rand.nextInt(68);
					while(player.checkCollision(x*32, y*32)){
						x = rand.nextInt(120);
						y = rand.nextInt(68);
					}
					player.setWorldX(x*32);
					player.setWorldY(y*32);
					player.setAliveTrue();
					player.setHealth(Combat.calcHP(player.getLevel(), player.getClassNumber()));
					player.setMagic(Combat.calcMagic(player.getLevel(), player.getClassNumber()));
					Packet02Move packet = new Packet02Move(player.getUsername(), player.getWorldX(), player.getWorldY(), player.getDirection());
					packet.writeData(Board.board.socketClient);
					Packet11Dead packetP = new Packet11Dead(player.getUsername(), player.isAlive());
					packetP.writeData(Board.board.socketClient);
					deadScreen.respawn = false;
				}
			}
		}
	}
	
	public void spacecode(){
		if(input.space.isPressed()){
			if(player.getClassNumber() == 2){
				if(player.getMagic() >= 5){
					if(player.getDirection() == 0){
						magicMP.add(new Firebolt(player.getWorldX()-32, player.getWorldY(), -5, 0, player));
					}else if(player.getDirection() == 1){
						magicMP.add(new Firebolt(player.getWorldX()+32, player.getWorldY(), 5, 0, player));
					}else if(player.getDirection() == 2){
						magicMP.add(new Firebolt(player.getWorldX(), player.getWorldY()+32, 0, 5, player));
					}else if(player.getDirection() == 3){
						magicMP.add(new Firebolt(player.getWorldX(), player.getWorldY()-32, 0, -5, player));
					}
					player.setMagic(player.getMagic() - 5);
					input.space.toggle(false);
				}
			}else if(player.getClassNumber() == 1){
				coolDown2 = System.currentTimeMillis();
				if(!player.isAttacking() && coolDown2 - coolDown >= 250){
					player.setAttackingTrue();
					input.space.toggle(false);
					Packet10Slash packet = new Packet10Slash(player.getUsername(), player.isAttacking());
					packet.writeData(Board.board.socketClient);
					names.clear();
					combatTime = System.currentTimeMillis();
				}
			}
		}
		if(player.isAttacking()){
			combatEndTime = System.currentTimeMillis();
			int tempX = player.getWorldX()/32;
			int tempY = player.getWorldY()/32;
			for(PlayerMP p : entityManager.players){
				if(names.size() == 0){
					int pX = p.getWorldX()/32;
					int pY = p.getWorldY()/32;
					if(player.getDirection() == 0){
						if(tempX == pX + 1){
							if(tempY == pY || tempY == pY - 1 || tempY == pY + 1){
								Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
								pDamage.writeData(Board.board.socketClient);
								names.add(p.getUsername());
							}
						}
					}else if(player.getDirection() == 1){
						if(tempX == pX - 1){
							if(tempY == pY || tempY == pY - 1 || tempY == pY + 1){
								Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
								pDamage.writeData(Board.board.socketClient);
								names.add(p.getUsername());
							}
						}	
					}else if(player.getDirection() == 2){
						if(tempY == pY - 1){
							if(tempX == pX || tempX == pX + 1 || tempX == pX -1){
								Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
								pDamage.writeData(Board.board.socketClient);
								names.add(p.getUsername());
							}
						}
					}else if(player.getDirection() == 3){
						if(tempY == pY + 1){
							if(tempX == pX || tempX == pX + 1 || tempX == pX -1){
								Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
								pDamage.writeData(Board.board.socketClient);
								names.add(p.getUsername());
							}
						}
					}
					if(tempX == pX && tempY == pY){
						Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
						pDamage.writeData(Board.board.socketClient);
						names.add(p.getUsername());
					}
				}else{
					List<String> tempNames = new ArrayList<String>();
					boolean isTaking = false;
					for(String l : this.names){
						if(l.equalsIgnoreCase(p.getUsername())){
							isTaking = true;
							break;
						}
					}
					if(!isTaking){
						int pX = p.getWorldX()/32;
						int pY = p.getWorldY()/32;
						if(player.getDirection() == 0){
							if(tempX == pX + 1){
								if(tempY == pY || tempY == pY - 1 || tempY == pY + 1){
									Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
									pDamage.writeData(Board.board.socketClient);
									tempNames.add(p.getUsername());
								}
							}
						}else if(player.getDirection() == 1){
							if(tempX == pX - 1){
								if(tempY == pY || tempY == pY - 1 || tempY == pY + 1){
									Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
									pDamage.writeData(Board.board.socketClient);
									tempNames.add(p.getUsername());
								}								}	
						}else if(player.getDirection() == 2){
							if(tempY == pY - 1){
								if(tempX == pX || tempX == pX + 1 || tempX == pX -1){
									Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
									pDamage.writeData(Board.board.socketClient);
									tempNames.add(p.getUsername());
								}
							}
						}else if(player.getDirection() == 3){
							if(tempY == pY + 1){
								if(tempX == pX || tempX == pX + 1 || tempX == pX -1){
									Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
									pDamage.writeData(Board.board.socketClient);
									tempNames.add(p.getUsername());
								}
							}
						}
						if(tempX == pX && tempY == pY){
							Packet07Damage pDamage = new Packet07Damage(p.getUsername(), Combat.calculateDamage(player.getLevel(), p.getLevel(), player.getStrengthStat(), p.getGuardStat(), p.getHealth()));
							pDamage.writeData(Board.board.socketClient);
							tempNames.add(p.getUsername());
						}
					}
					for(String m : tempNames){
						names.add(m);
					}
				}
			}
			if(combatEndTime - combatTime >= 125){
				player.setAttackingFalse();
				Packet10Slash packet = new Packet10Slash(player.getUsername(), player.isAttacking());
				packet.writeData(Board.board.socketClient);
				coolDown = System.currentTimeMillis();
			}
		}
	}
	
	public void magicUpdate(){
		endTime = System.nanoTime();
		if(endTime - time >= 600000000){
			if(player.getMagic() != 25){
				player.setMagic(player.getMagic() + 1);
				time = endTime;
			}
		}
	}
}