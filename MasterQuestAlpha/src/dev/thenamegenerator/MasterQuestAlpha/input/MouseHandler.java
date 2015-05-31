package dev.thenamegenerator.MasterQuestAlpha.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.thenamegenerator.MasterQuestAlpha.display.Board;

public class MouseHandler implements MouseListener{
	
	public MouseHandler(Board board){
		board.addMouseListener(this);
	}
	
	public class Mouse{
		private int numTimesPressed = 0;
        private boolean pressed = false;
        
        @SuppressWarnings("unused")
		private int x, y;

        public int getNumTimesPressed() {
            return numTimesPressed;
        }

        public boolean isPressed() {
            return pressed;
        }

        public void toggle(boolean isPressed) {
            pressed = isPressed;
            if (isPressed) {
            	numTimesPressed++;
            }
        }
	}
	
	public Mouse mouse = new Mouse();

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
		mouse.toggle(true);
	}

	public void mouseReleased(MouseEvent e) {
		mouse.toggle(false);
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}