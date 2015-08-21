package dev.thenamegenerator.MasterQuestAlpha.net;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Server extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setTitle("Alpha DevServer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsole = new JLabel("Console");
		lblConsole.setBounds(6, 6, 61, 16);
		contentPane.add(lblConsole);
		
		JButton btnAbortServer = new JButton("Abort Server");
		btnAbortServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnAbortServer.setBounds(327, 1, 117, 29);
		contentPane.add(btnAbortServer);
		
		JLabel lblPlayers = new JLabel("Players");
		lblPlayers.setBounds(189, 84, 61, 16);
		contentPane.add(lblPlayers);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(6, 36, 170, 236);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(189, 103, 134, 169);
		contentPane.add(textArea_1);
		
		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setBounds(332, 221, 61, 16);
		contentPane.add(lblServerIp);
		
		textField = new JTextField();
		textField.setBounds(327, 244, 117, 28);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
