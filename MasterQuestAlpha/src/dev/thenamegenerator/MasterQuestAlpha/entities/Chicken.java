package dev.thenamegenerator.MasterQuestAlpha.entities;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;

public class Chicken extends Entity{

	public Chicken() {
		initSprites(Assets.CL, Assets.CR, Assets.CD, Assets.CU);
		setSprite(Assets.CD);
		speed = 3;
	}
	
}
