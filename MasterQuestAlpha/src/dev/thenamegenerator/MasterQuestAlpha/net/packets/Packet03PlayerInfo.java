package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import java.net.InetAddress;
import java.net.UnknownHostException;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet03PlayerInfo extends Packet{
	
	private String userName;
	
	private int x,y;
	
	private InetAddress ipAddress;
	private int port;

	public Packet03PlayerInfo(byte[] data) {
		super(03);
		String[] dataArray = readData(data).split(",");
		this.userName = dataArray[0];
		this.x = Integer.parseInt(dataArray[1]);
		this.y = Integer.parseInt(dataArray[2]);
		
		try {
			this.ipAddress = InetAddress.getByName(dataArray[3]);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		this.port = Integer.parseInt(dataArray[4]);
	}
	
	public Packet03PlayerInfo(String userName, int x, int y, InetAddress ipAddress, int port) {
		super(03);
		this.userName = userName;
		
		this.x = x;
		this.y = y;
		
		this.ipAddress = ipAddress;
		this.port = port;
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
		return ("03" + this.userName + "," + this.x + "," + this.y + "," + this.ipAddress + "," + this.port).getBytes();
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
	
	public InetAddress getIpAddress(){
		return ipAddress;
	}
	
	public int getPort(){
		return port;
	}
	
}
