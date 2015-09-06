package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet11Dead extends Packet{
	
	private String userName;
	
	private boolean isAlive;

	public Packet11Dead(byte[] data) {
		super(11);
		String[] dataArray = readData(data).split(",");
		this.userName = dataArray[0];
		this.isAlive = Boolean.parseBoolean(dataArray[1]);
	}
	
	public Packet11Dead(String userName, boolean isAlive) {
		super(11);
		this.userName = userName;
		this.isAlive = isAlive;
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
		return ("11" + this.userName + "," + this.isAlive).getBytes();
	}

	public String getUsername() {
		return userName;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
}