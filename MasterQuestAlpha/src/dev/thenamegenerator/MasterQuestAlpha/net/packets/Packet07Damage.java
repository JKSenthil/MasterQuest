package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet07Damage extends Packet{
	
	private String userName;
	private double damage;

	public Packet07Damage(byte[] data) {
		super(07);
		String[] dataArray = readData(data).split(",");
		this.userName = dataArray[0];
		this.damage = Double.parseDouble(dataArray[1]);
	}
	
	public Packet07Damage(String userName, int damage) {
		super(07);
		this.userName = userName;
		this.damage = damage;
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
		return ("07" + this.userName + "," + this.damage).getBytes();
	}

	public String getUsername() {
		return userName;
	}
	
	public double getDamage(){
		return damage;
	}
	
}