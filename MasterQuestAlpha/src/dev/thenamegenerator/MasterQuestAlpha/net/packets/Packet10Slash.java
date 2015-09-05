package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet10Slash extends Packet{
	
	private String userName;
	private boolean isAttacking;

	public Packet10Slash(byte[] data) {
		super(10);
		String[] dataArray = readData(data).split(",");
		this.userName = dataArray[0];
		this.isAttacking = Boolean.parseBoolean(dataArray[1]);
	}
	
	public Packet10Slash(String userName, boolean isAttacking) {
		super(10);
		this.userName = userName;
		this.isAttacking = isAttacking;
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
		return ("10" + this.userName + "," + this.isAttacking).getBytes();
	}

	public String getUsername() {
		return userName;
	}
	
	public boolean isAttacking(){
		return isAttacking;
	}
	
}