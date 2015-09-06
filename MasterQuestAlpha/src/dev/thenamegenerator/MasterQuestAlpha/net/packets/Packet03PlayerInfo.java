package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public class Packet03PlayerInfo extends Packet{
	
	private String userName;
	
	private int classNumber;
    private double strengthStat;
    private double guardStat;
    private int level;
    private double health;

	public Packet03PlayerInfo(byte[] data) {
		super(03);
		String[] dataArray = readData(data).split(",");
		this.userName = dataArray[0];
		this.classNumber = Integer.parseInt(dataArray[1]);
		this.strengthStat = Double.parseDouble(dataArray[2]);
		this.guardStat = Double.parseDouble(dataArray[3]);
		this.level = Integer.parseInt(dataArray[4]);
		this.health = Double.parseDouble(dataArray[5]);
	}
	
	public Packet03PlayerInfo(String userName, int classNumber, double strengthStat, double guardStat, int level, double health) {
		super(03);
		this.userName = userName;
		this.classNumber = classNumber;
		this.strengthStat = strengthStat;
		this.guardStat = guardStat;
		this.level = level;
		this.health = health;
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
		return ("03" + this.userName + "," + this.classNumber + "," + this.strengthStat + "," + this.guardStat + "," + this.level + "," + this.health).getBytes();
	}

	public String getUsername() {
		return userName;
	}

	public int getClassNumber() {
		return classNumber;
	}

	public double getStrengthStat() {
		return strengthStat;
	}

	public double getGuardStat() {
		return guardStat;
	}

	public int getLevel() {
		return level;
	}
	
	public double getHealth(){
		return health;
	}
}