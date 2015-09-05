package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet06MagicDisconnect extends Packet{
	
	private String userName;
	
	private int ID;

	public Packet06MagicDisconnect(byte[] data) {
		super(06);
		String[] dataArray = readData(data).split(",");
		this.userName = dataArray[0];
		this.ID = Integer.parseInt(dataArray[1]);
	}
	
	public Packet06MagicDisconnect(String userName, int ID) {
		super(06);
		this.userName = userName;
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
		return ("06" + this.userName + "," + this.ID).getBytes();
	}

	public String getUsername() {
		return userName;
	}
	
	public int getID(){
		return ID;
	}
	
}