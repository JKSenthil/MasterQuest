package dev.thenamegenerator.MasterQuestAlpha.items.armor;

import java.awt.image.BufferedImage;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.items.Armor;

public class OrangeRobe extends Armor{
	
	public OrangeRobe(){
		id = 30000;
		name = "Orange Robe";
		weight = 1;
		value = 15;
		icon = Assets.orangeRobeDown;
		description = "It's an orange robe";
		
		defense = 1;
		
		sprites = new BufferedImage[4];
		sprites[0] = Assets.orangeRobeLeft;
		sprites[1] = Assets.orangeRobeRight;
		sprites[2] = Assets.orangeRobeDown;
		sprites[3] = Assets.orangeRobeUp;
	}
	
}
