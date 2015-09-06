package dev.thenamegenerator.MasterQuestAlpha.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.input.MouseHandler;

public class Dead {
	
	private BufferedImage dead;
	private Graphics2D g2d;
	
	private MouseHandler e;
	
	public boolean respawn = false;
	
	public Dead(MouseHandler e){
		dead = new BufferedImage(480+64, 374+64, BufferedImage.TYPE_INT_RGB);
		g2d = dead.createGraphics();
		this.e = e;
	}
	
	public void tick(){
		if(e.mouse.x >= 272 - 32 && e.mouse.x <= 272 + 32){
			if(e.mouse.y >= 219 - 16 && e.mouse.y <= 219 + 16){
				respawn = true;
			}
		}
	}
	
	public void draw(){
		g2d.setFont(new Font("Soup of Justice", Font.BOLD, 30));
		g2d.setColor(Color.red);
		g2d.drawString("YOU DIED", 205, 45);
		g2d.fillRect(272 - 32, 219 - 16, 64, 32);
		g2d.setFont(new Font("Soup of Justice", Font.BOLD, 12));
		g2d.setColor(Color.black);
		g2d.drawString("respawn", 264 - 15, 219);
	}
	
	public BufferedImage getImage(){
		return dead;
	}
	
	public boolean getRespawn(){
		return respawn;
	}

}
