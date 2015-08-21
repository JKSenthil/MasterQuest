package dev.thenamegenerator.MasterQuestAlpha.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import dev.thenamegenerator.MasterQuestAlpha.display.Board;
import dev.thenamegenerator.MasterQuestAlpha.entities.PlayerMP;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet.PacketTypes;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet00Login;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet01Disconnect;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet02Move;

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
			PlayerMP player = new PlayerMP(((Packet00Login)packet).getUsername(), address, port);
			System.out.println(address + " " + port);
			Board.board.getManager().getEntityManager().players.add(player);
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("Client> [" + address.getHostAddress() + ": " + port + "]" + " " + ((Packet01Disconnect)packet).getUsername() + " has left the world..." + "gameclient");
			Board.board.getManager().getEntityManager().removePlayers(((Packet01Disconnect)packet).getUsername());
			break;
		case MOVE:
			packet = new Packet02Move(data);
			handleMove(((Packet02Move)packet));
		}
	}

	private void handleMove(Packet02Move packet) {
		if(!packet.getUsername().equalsIgnoreCase(Board.board.getManager().getPlayer().getUsername())){
			Board.board.getManager().getEntityManager().movePlayer(packet.getUsername(), packet.getX(), packet.getY());
		}
	}

	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}