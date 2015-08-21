package dev.thenamegenerator.MasterQuestAlpha.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dev.thenamegenerator.MasterQuestAlpha.display.Board;
import dev.thenamegenerator.MasterQuestAlpha.entities.PlayerMP;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet.PacketTypes;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet00Login;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet01Disconnect;
import dev.thenamegenerator.MasterQuestAlpha.net.packets.Packet02Move;

public class GameServer extends Thread{
	
	private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	
	public boolean serverOpen = false;
	
	private DatagramSocket socket;
	private Board board;
	
	private JFrame frame = new JFrame("Dev");
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JTextArea textArea_1;
	
	private int index = 1;
	
	private boolean runOnce = false;
	
	public GameServer(Board board){
		this.board = board;
		try {
			this.socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			e.printStackTrace();
		} 
		
		frame.setTitle("Alpha DevServer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsole = new JLabel("Console");
		lblConsole.setBounds(6, 6, 61, 16);
		contentPane.add(lblConsole);
		
		JButton btnAbortServer = new JButton("Abort Server");
		btnAbortServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				serverOpen = false;
			}
		});
		btnAbortServer.setBounds(327, 1, 117, 29);
		contentPane.add(btnAbortServer);
		
		JLabel lblPlayers = new JLabel("Players");
		lblPlayers.setBounds(189, 84, 61, 16);
		contentPane.add(lblPlayers);
		
		textArea = new JTextArea();
		textArea.setBounds(6, 36, 170, 236);
		textArea.setLineWrap(true);
		contentPane.add(textArea);
		textArea.setText("Server started on " + new Date());
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(189, 103, 134, 169);
		contentPane.add(textArea_1);
		
		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setBounds(332, 221, 61, 16);
		contentPane.add(lblServerIp);
		
		textField = new JTextField();
		textField.setBounds(327, 244, 117, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		frame.setVisible(true);
	}
	
	public void run(){
		while(serverOpen){
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
			if(!runOnce){
				textField.setText(packet.getAddress().getHostAddress());
				runOnce = true;
			}
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
			PlayerMP player = new PlayerMP(null, ((Packet00Login)packet).getUsername(), address, port);
			this.addConnection(player, ((Packet00Login)packet));
			break;
		case DISCONNECT:
			packet = new Packet01Disconnect(data);
			System.out.println("Server> [" + address.getHostAddress() + ": " + port + "]" + " " + ((Packet01Disconnect)packet).getUsername() + " has left the game...");
			this.removeConnection(((Packet01Disconnect)packet));
			break;
		case MOVE:
			packet = new Packet02Move(data);
			System.out.println(((Packet02Move)packet).getUsername() + " has moved to " + ((Packet02Move)packet).getX() + ", " + ((Packet02Move)packet).getY());
			this.handleMove(((Packet02Move)packet));
		}
	}

	private void handleMove(Packet02Move packet) {
		if(getPlayerMP(packet.getUsername()) != null){
			int index = getPlayerMPIndex(packet.getUsername());
			this.connectedPlayers.get(index).setWorldX(packet.getX());
			this.connectedPlayers.get(index).setWorldY(packet.getY());
			packet.writeData(this);
		}
	}

	public void removeConnection(Packet01Disconnect packet01Disconnect) {
		this.connectedPlayers.remove(getPlayerMPIndex(packet01Disconnect.getUsername()));
		packet01Disconnect.writeData(this);
		textArea.setText(textArea.getText() + "\n\n" + packet01Disconnect.getUsername() + " has disconnected");
		
		//recreates player list
		textArea_1.setText("");
		for(PlayerMP p : this.connectedPlayers){
			textArea_1.setText(textArea_1.getText() + "\n" + p.getUsername());
		}
	}
	
	public int getPlayerMPIndex(String userName){
		int index = 0;
		for(PlayerMP p : this.connectedPlayers){
			if(p.getUsername().equalsIgnoreCase(userName)){
				break;
			}
			index++;
		}
		return index;
	}
	
	public PlayerMP getPlayerMP(String userName){
		for(PlayerMP p : this.connectedPlayers){
			if(p.getUsername().equalsIgnoreCase(userName)){
				return p;
			}
		}
		return null;
	}

	public void addConnection(PlayerMP player, Packet00Login packet) {
		boolean alreadyConnected = false;
		for(PlayerMP p : this.connectedPlayers){
			if(player.getUsername().equalsIgnoreCase(p.getUsername())){
				if(p.ipAddress == null){
					p.ipAddress = player.ipAddress;
				}
				if(p.port == -1){
					p.port = player.port;
				}
				alreadyConnected = true;
			}else{
				sendData(packet.getData(), p.ipAddress, p.port);
				System.out.println("Doing this...");
				Packet00Login packet2 = new Packet00Login(p.getUsername());
				sendData(packet2.getData(), player.ipAddress, player.port);
			}
		}
		if(!alreadyConnected){
			this.connectedPlayers.add(player);
		}
		System.out.println("Server> [" + player.ipAddress.getHostAddress() + ": " + player.port + "]" + " " + ((Packet00Login)packet).getUsername() + " has connected.");
		textArea.setText(textArea.getText() + "\n\n" + player.getUsername() + " has connected");
		textArea_1.setText(textArea_1.getText() + "\n" + player.getUsername());
	}
	
	public void sendData(byte[] data, InetAddress ipAddress, int port){
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendDataToAllClients(byte[] data) {
		for(PlayerMP p : connectedPlayers){
			sendData(data, p.ipAddress, p.port);
		}
	}
}