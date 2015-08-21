package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.net.InetAddress;

import dev.thenamegenerator.MasterQuestAlpha.combat.Combat;
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
		setLocation(480, 480);
		setSprite(right);
		time = System.nanoTime();
		speed = 5;
		health = Combat.calcHP(25, 1);
		magic = 25;
	}
	
	public PlayerMP(String userName, InetAddress ipAddress, int port) {
		super(null, userName);
		this.ipAddress = ipAddress;
		this.port = port;
		
		initSprites(Assets.playerLeft, Assets.playerRight, Assets.playerDown, Assets.playerUp);
		setLocation(480, 480);
		setSprite(right);
	}

}