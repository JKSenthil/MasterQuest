package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet00Login extends Packet{
	
	private String userName;

	public Packet00Login(byte[] data) {
		super(00);
		this.userName = readData(data);
	}
	
	public Packet00Login(String userName) {
		super(00);
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
		return ("00" + this.userName).getBytes();
	}

	public String getUsername() {
		return userName;
	}
	
}
