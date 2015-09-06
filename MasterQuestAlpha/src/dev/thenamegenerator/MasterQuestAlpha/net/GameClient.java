package dev.thenamegenerator.MasterQuestAlpha.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

import dev.thenamegenerator.MasterQuestAlpha.display.Board;
import dev.thenamegenerator.MasterQuestAlpha.entities.PlayerMP;
import dev.thenamegenerator.MasterQuestAlpha.magic.Firebolt;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet.PacketTypes;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet00Login;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet01Disconnect;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet02Move;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet03PlayerInfo;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet04MagicLogin;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet05MagicMove;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet06MagicDisconnect;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet07Damage;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet10Slash;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet11Dead;

public class GameClient extends Thread{
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Board board;
	
	public GameClient(Board board, String ipAddress){
		this.board = board;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}	
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
		switch(type){
		default:
		case INVALID:
			break;
		case LOGIN:
			packet = new Packet00Login(data);
			System.out.println("Client> [" + address.getHostAddress() + ": " + port + "]" + " " + ((Packet00Login)packet).getUsername() + " has joined the game...");
			PlayerMP player = new PlayerMP(((Packet00Login) packet).getUsername());
			Board.board.getManager().getEntityManager().addPlayerMP(player);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("Client> [" + address.getHostAddress() + ": " + port + "]" + " " + ((Packet01Disconnect)packet).getUsername() + " has left the world..." + "gameclient");
			Board.board.getManager().getEntityManager().removePlayers(((Packet01Disconnect)packet).getUsername());
			break;
		case MOVE:
			packet = new Packet02Move(data);
			handleMove(((Packet02Move)packet));
			break;
		case PLAYERINFO:
			packet = new Packet03PlayerInfo(data);
			this.handlePlayerInfo((Packet03PlayerInfo) packet);
			break;
		case MAGICLOGIN:
			packet = new Packet04MagicLogin(data);
			this.handleMagicLogin((Packet04MagicLogin) packet);
			break;
		case MAGICMOVE:
			packet = new Packet05MagicMove(data);
			this.handleMagicMove((Packet05MagicMove) packet);
			break;
		case MAGICDISCONNECT:
			packet = new Packet06MagicDisconnect(data);
			for(int i = 0; i < Board.board.getManager().magicMP.size(); i++){
				if(((Packet06MagicDisconnect) packet).getID() == Board.board.getManager().magicMP.get(i).ID){
					Board.board.getManager().magicMP.remove(i);
					break;
				}
			}
			break;
		case DAMAGE:
			packet = new Packet07Damage(data);
			double damage = ((Packet07Damage)packet).getDamage();
			Board.board.getManager().getPlayer().setHealth(Board.board.getManager().getPlayer().getHealth() - damage);
			break;
		case SWORD:
			packet = new Packet10Slash(data);
			this.handleSlash((Packet10Slash)packet);
			break;
		case DEATH:
			packet = new Packet11Dead(data);
			this.getPlayerMP(((Packet11Dead)packet).getUsername()).setAlive(((Packet11Dead)packet).isAlive());;
			break;
		}
	}
	
	public PlayerMP getPlayerMP(String userName){
		for(PlayerMP p : Board.board.getManager().getEntityManager().players){
			if(p.getUsername().equalsIgnoreCase(userName)){
				return p;
			}
		}
		return null;
	}

	private void handlePlayerInfo(Packet03PlayerInfo packet) {
		for(PlayerMP p : Board.board.getManager().getEntityManager().players){
			if(p.getUsername().equalsIgnoreCase(packet.getUsername())){
				p.setClassNumber(packet.getClassNumber());
				p.setStrengthStat(packet.getStrengthStat());
				p.setGuardStat(packet.getGuardStat());
				p.setLevel(packet.getLevel());
				p.setHealth(packet.getHealth());
				break;
			}
		}
	}

	private void handleSlash(Packet10Slash packet) {
		for(PlayerMP p : Board.board.getManager().getEntityManager().players){
			if(packet.getUsername().equalsIgnoreCase(p.getUsername())){
				if(packet.isAttacking()){
					p.setAttackingTrue();
				}else if(!packet.isAttacking()){
					p.setAttackingFalse();
				}
				break;
			}
		}
	}

	private void handleMove(Packet02Move packet) {
		if(!packet.getUsername().equalsIgnoreCase(Board.board.getManager().getPlayer().getUsername())){
			Board.board.getManager().getEntityManager().movePlayer(packet.getUsername(), packet.getX(), packet.getY(), packet.getDirection());
		}
	}
	
	private void handleMagicLogin(Packet04MagicLogin packet){
		Firebolt fire = new Firebolt(packet.getUsername(), packet.getWorldX(), packet.getWorldY(), packet.getID());
		Board.board.getManager().magicMP.add(fire);
	}
	
	private void handleMagicMove(Packet05MagicMove packet){
		for(Firebolt m : Board.board.getManager().magicMP){
			if(packet.getID() == m.ID){
				m.setWorldX(packet.getWorldX());
				m.setWorldY(packet.getWorldY());
				break;
			}
		}
	}

	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 25565);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}