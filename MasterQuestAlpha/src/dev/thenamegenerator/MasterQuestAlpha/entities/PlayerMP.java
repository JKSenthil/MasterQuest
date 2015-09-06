package dev.thenamegenerator.MasterQuestAlpha.entities;

import java.net.InetAddress;
import java.util.Random;

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
		
		Random rand = new Random();
		int test = rand.nextInt(2);
		if(test == 0){
			initSprites(Assets.playerLeft, Assets.playerRight, Assets.playerDown, Assets.playerUp);
		}else{
			initSprites(Assets.BplayerLeft, Assets.BplayerRight, Assets.BplayerDown, Assets.BplayerUp);
		}
		
		body[0] = left;
		body[1] = right;
		body[2] = down;
		body[3] = up;
		
		test = rand.nextInt(5);
		if(test == 0){
			armor[0] = Assets.orangeRobeLeft; armor[1] = Assets.orangeRobeRight; armor[2] = Assets.orangeRobeDown; armor[3] = Assets.orangeRobeUp;
		}else if(test == 1){
			armor[0] = Assets.blueRobeLeft; armor[1] = Assets.blueRobeRight; armor[2] = Assets.blueRobeDown; armor[3] = Assets.blueRobeUp;
		}else if(test == 2){
			armor[0] = Assets.greenRobeLeft; armor[1] = Assets.greenRobeRight; armor[2] = Assets.greenRobeDown; armor[3] = Assets.greenRobeUp;
		}else if(test == 3){
			armor[0] = Assets.redRobeLeft; armor[1] = Assets.redRobeRight; armor[2] = Assets.redRobeDown; armor[3] = Assets.redRobeUp;
		}else{
			armor[0] = Assets.blackRobeLeft; armor[1] = Assets.blackRobeRight; armor[2] = Assets.blackRobeDown; armor[3] = Assets.blackRobeUp;
		}
		
		test = rand.nextInt(4);
		if(test == 0){
			hair[0] = Assets.blackHairLeft; hair[1] = Assets.blackHairRight; hair[2] = Assets.blackHairDown; hair[3] = Assets.blackHairUp;
		}else if(test == 1){
			hair[0] = Assets.grayHairLeft; hair[1] = Assets.grayHairRight; hair[2] = Assets.grayHairDown; hair[3] = Assets.grayHairUp;
		}else if(test == 2){
			hair[0] = Assets.yellowHairLeft; hair[1] = Assets.yellowHairRight; hair[2] = Assets.yellowHairDown; hair[3] = Assets.yellowHairUp;
		}else if(test == 3){
			hair[0] = Assets.brownHairLeft; hair[1] = Assets.brownHairRight; hair[2] = Assets.brownHairDown; hair[3] = Assets.brownHairUp;
		}
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