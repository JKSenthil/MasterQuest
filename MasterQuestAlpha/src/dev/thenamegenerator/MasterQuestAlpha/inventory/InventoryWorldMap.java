package dev.thenamegenerator.MasterQuestAlpha.inventory;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.world.World;

public class InventoryWorldMap {
	
	private BufferedImage worldMap;
	private BufferedImage realWorldMap;
	
	public InventoryWorldMap(){
		worldMap = World.worldImage;
		realWorldMap = new BufferedImage(300, 224, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = realWorldMap.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(worldMap, 0, 0, 300, 224, 0, 0, worldMap.getWidth(), worldMap.getHeight(), null);
		g2d.dispose();
	}
	
	public BufferedImage getWorldMap(){
		return worldMap;
	}
	
	public BufferedImage getRealWorldMap(){
		return realWorldMap;
	}
	
}
