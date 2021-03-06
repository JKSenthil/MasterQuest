package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet02Move extends Packet{
	
	private String userName;
	
	private int x,y;
	
	private int direction;

	public Packet02Move(byte[] data) {
		super(02);
		String[] dataArray = readData(data).split(",");
		this.userName = dataArray[0];
		this.x = Integer.parseInt(dataArray[1]);
		this.y = Integer.parseInt(dataArray[2]);
		this.direction = Integer.parseInt(dataArray[3]);
	}
	
	public Packet02Move(String userName, int x, int y, int direction) {
		super(02);
		this.userName = userName;
		
		this.x = x;
		this.y = y;
		
		this.direction = direction;
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData() {
		return ("02" + this.userName + "," + this.x + "," + this.y + "," + this.direction).getBytes();
	}

	public String getUsername() {
		return userName;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getDirection(){
		return direction;
	}
	
}
