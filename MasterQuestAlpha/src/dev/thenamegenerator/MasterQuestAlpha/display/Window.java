package dev.thenamegenerator.MasterQuestAlpha.display;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Window extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Board board = new Board();
    public Window() {
    	//GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    	//GraphicsDevice vc=ge.getDefaultScreenDevice();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //15 tiles by 11 tiles
        setSize(480, 374);
        setLocationRelativeTo(null);
        setTitle("MasterQuestAlpha");
        setResizable(false);
        //setUndecorated(true); 
        //vc.setFullScreenWindow(this);
        add(board);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
        board.start();
    }

}
