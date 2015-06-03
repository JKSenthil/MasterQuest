package dev.thenamegenerator.MasterQuestAlpha.input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import dev.thenamegenerator.MasterQuestAlpha.display.Board;

public class MouseWheelHandler implements MouseWheelListener{
	
	public class Wheel{
		public int noches; 
	}
	
	public MouseWheelHandler(Board board){
		board.addMouseWheelListener(this);
	}
	
	public Wheel wheel = new Wheel();

	public void mouseWheelMoved(MouseWheelEvent e) {
		wheel.noches = e.getWheelRotation();
	}
	
}
