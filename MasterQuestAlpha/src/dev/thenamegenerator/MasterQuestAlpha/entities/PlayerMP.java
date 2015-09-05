package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.net.InetAddress;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.input.InputHandler;

public class PlayerMP extends MainPlayer{

	public InetAddress ipAddress;
	public int port;
	
	public PlayerMP(InputHandler input, String userName, InetAddress ipAddress, int port) {
		super(input, userName);
		this.ipAddress = ipAddress;
		this.port = port;
		
		initSprites(Assets.playerLeft, Assets.playerRight, Assets.playerDown, Assets.playerUp);
		
		body[0] = left;
		body[1] = right;
		body[2] = down;
		body[3] = up;
		
		setLocation(480, 480);
		setSprite(right);
		
		direction = 1;
		
		isAttacking = false;
	}
	
	public PlayerMP(String userName){
		super(null, userName);
		
		initSprites(Assets.playerLeft, Assets.playerRight, Assets.playerDown, Assets.playerUp);
		
		body[0] = left;
		body[1] = right;
		body[2] = down;
		body[3] = up;
		
		setLocation(480, 480);
		setSprite(right);
		
		direction = 1;
		
		isAttacking = false;
	}
	
	public PlayerMP(String userName, InetAddress ipAddress, int port) {
		super(null, userName);
		this.ipAddress = ipAddress;
		this.port = port;
		
		initSprites(Assets.playerLeft, Assets.playerRight, Assets.playerDown, Assets.playerUp);
		
		body[0] = left;
		body[1] = right;
		body[2] = down;
		body[3] = up;
		
		setLocation(480, 480);
		setSprite(right);
		
		direction = 1;
		
		isAttacking = false;
	}
	
	public PlayerMP(String userName, InetAddress ipAddress, int port, int x, int y) {
		super(null, userName);
		this.ipAddress = ipAddress;
		this.port = port;
		
		initSprites(Assets.playerLeft, Assets.playerRight, Assets.playerDown, Assets.playerUp);
		
		body[0] = left;
		body[1] = right;
		body[2] = down;
		body[3] = up;
		
		setLocation(x, y);
		setSprite(right);
		
		direction = 1;
		
		isAttacking = false;
	}

}