package dev.thenamegenerator.MasterQuestAlpha.combat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StatusBar{
	
	private BufferedImage healthBar;
	private BufferedImage magicBar;
	
	private double fullHealth;
	private double fullMagic;
	
	Graphics2D g;
	Graphics2D g2;
	
	public StatusBar(double health, double magic){
		healthBar = new BufferedImage(100, 8, BufferedImage.TYPE_INT_RGB);
		magicBar = new BufferedImage(100, 8, BufferedImage.TYPE_INT_RGB);
		
		g = healthBar.createGraphics();
		g.setColor(Color.red);
		
		g2 = magicBar.createGraphics();
		g2.setColor(Color.blue);
		
		fullHealth = health;
		fullMagic = magic;
	}
	
	public void updateStatusBars(double health, double magic){
		double a = health/fullHealth;
		a*=100;

		double b = magic/fullMagic;
		b*=100;
		
		//clear rectangle
		g.setColor(Color.white);
		g.fillRect(0, 0, healthBar.getWidth(), healthBar.getHeight());
		g2.setColor(Color.white);
		g2.fillRect(0, 0, magicBar.getWidth(), magicBar.getHeight());
		
		//fill the status bars
		g.setColor(Color.red);
		g.fillRect(0, 0, (int) a, 8);
		g2.setColor(Color.blue);
		g2.fillRect(0, 0, (int) b, 8);
	}

	public BufferedImage getHealth() {
		return healthBar;
	}

	public BufferedImage getMagic() {
		return magicBar;
	}

}
