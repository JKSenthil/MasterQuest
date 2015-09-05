package dev.thenamegenerator.MasterQuestAlpha.magic;

import java.awt.Rectangle;
import java.util.Random;

import dev.thenamegenerator.MasterQuestAlpha.display.Board;
import dev.thenamegenerator.MasterQuestAlpha.entities.MainPlayer;
import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet04MagicLogin;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet05MagicMove;

public class Firebolt extends Magic{
	
	public Rectangle rectangle;
	
	public String username;
	
	public int ID;
	
	public Firebolt(int playerX, int playerY, int dx, int dy, MainPlayer player){
		icon = Assets.fireBolt;
		
		time = System.nanoTime();
		
		worldX = playerX;
		worldY = playerY;
		
		this.dx = dx;
		this.dy = dy;
	
		magicRequired = 5;
		
		username = player.getUsername();
		
		Random rand = new Random();
		ID = rand.nextInt(1000);
		
		Packet04MagicLogin packet = new Packet04MagicLogin(username, worldX, worldY, ID);
		packet.writeData(Board.board.socketClient);
	}
	
	public Firebolt(int playerX, int playerY, int dx, int dy, String username){
		icon = Assets.fireBolt;
		
		time = System.nanoTime();
		
		worldX = playerX;
		worldY = playerY;
		
		this.dx = dx;
		this.dy = dy;
	
		magicRequired = 5;
		this.username = username;
	}
	
	public Firebolt(String username,int worldX, int worldY, int ID){
		icon = Assets.fireBolt;
		
		this.username = username;
		
		this.worldX = worldX;
		this.worldY = worldY;
		
		this.ID = ID;
	}
	
	public void setRect(){
		rectangle = new Rectangle(worldX, worldY, 32, 32);
	}
	
	public void setBigRect(){
		rectangle = new Rectangle(worldX, worldY, 40, 40);
	}
	
	public Rectangle getRect(){
		return rectangle;
	}
	
	public void move(){
		endTime = System.nanoTime();
		if(endTime - time >= 45000000/5){
			worldX += dx;
    		worldY += dy;
    		time = endTime;
    		Packet05MagicMove packetM = new Packet05MagicMove(this.username, this.worldX, this.worldY, this.ID);
    		packetM.writeData(Board.board.socketClient);
		}
	}
}