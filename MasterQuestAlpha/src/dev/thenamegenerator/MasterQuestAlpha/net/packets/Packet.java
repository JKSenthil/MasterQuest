package dev.thenamegenerator.MasterQuestAlpha.net.packets;

import dev.thenamegenerator.MasterQuestAlpha.net.GameClient;
import dev.thenamegenerator.MasterQuestAlpha.net.GameServer;

public abstract class Packet {
	
	public static enum PacketTypes{
		INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02), PLAYERINFO(03), MAGICLOGIN(04), MAGICMOVE(05),
		MAGICDISCONNECT(06), DAMAGE(07), SWORD(10);
		
		private int packetId;
		private PacketTypes(int packetId){
			this.packetId = packetId;
		}
		
		public int getId(){
			return packetId;
		}
	}
	
	public byte packetId;
	
	public Packet(int packetId){
		this.packetId = (byte) packetId;
	}
	
	public abstract void writeData(GameClient client);
	public abstract void writeData(GameServer server);

	public String readData(byte[] data){
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public abstract byte[] getData();
	
	public static PacketTypes lookupPacket(String packetId){
		try{
			return lookupPacket(Integer.parseInt(packetId));
		}catch(NumberFormatException e){
			return PacketTypes.INVALID;
		}
	}
	
	public static PacketTypes lookupPacket(int id){
		for(PacketTypes p : PacketTypes.values()){
			if(p.getId() == id){
				return p;
			}
		}
		return PacketTypes.INVALID;
	}
	
}
