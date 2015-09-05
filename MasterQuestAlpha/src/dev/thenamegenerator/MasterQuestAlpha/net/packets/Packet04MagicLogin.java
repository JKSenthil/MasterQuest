package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet04MagicLogin extends Packet{
	
	private String userName;
	
	private int worldX, worldY;
	
	private int ID;

	public Packet04MagicLogin(byte[] data) {
		super(04);
		String[] dataArray = readData(data).split(",");
		this.userName = dataArray[0];
		this.worldX = Integer.parseInt(dataArray[1]);
		this.worldY = Integer.parseInt(dataArray[2]);
		this.ID = Integer.parseInt(dataArray[3]);
	}
	
	public Packet04MagicLogin(String userName, int worldX, int worldY, int ID) {
		super(04);
		this.userName = userName;
		this.worldX = worldX;
		this.worldY = worldY;
		this.ID = ID;
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
		return ("04" + this.userName + "," + this.worldX + "," + this.worldY + "," + this.ID).getBytes();
	}

	public String getUsername() {
		return userName;
	}
	
	public int getWorldX(){
		return worldX;
	}
	
	public int getWorldY(){
		return worldY;
	}
	
	public int getID(){
		return ID;
	}
	
}