package dev.thenamegenerator.MasterQuestAlpha.items.weapons;

import dev.thenamegenerator.MasterQuestAlpha.gfx.Assets;
import dev.thenamegenerator.MasterQuestAlpha.items.Weapon;

public class IronShortSword extends Weapon{
	public IronShortSword(){
		id = 10000;
		name = "Iron Shortsword";
		weight = 10;
		value = 20;
		icon = Assets.ironShortSword;
		description = "It's a good shortsword";
	}
}
