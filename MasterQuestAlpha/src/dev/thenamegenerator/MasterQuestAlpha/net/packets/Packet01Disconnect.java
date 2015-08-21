package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet01Disconnect extends Packet{
	
	private String userName;

	public Packet01Disconnect(byte[] data) {
		super(01);
		this.userName = readData(data);
	}
	
	public Packet01Disconnect(String userName) {
		super(01);
		this.userName = userName;
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
		return ("01" + this.userName).getBytes();
	}

	public String getUsername() {
		return userName;
	}
	
}
