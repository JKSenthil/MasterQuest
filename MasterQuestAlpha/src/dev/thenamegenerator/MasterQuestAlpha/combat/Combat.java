package dev.thenamegenerator.MasterQuestAlpha.combat;

import java.util.Random;

public class Combat {
	
	public static double calcHP(double Lvl, int Class){
        double HP=0;
        if (Class==1){
            HP = 55+5*(Lvl-1);
        }else if (Class==2){
            HP = 56+6*(Lvl-1);
        }else if (Class==3){
            HP = 54+4*(Lvl-1);
        }
        return HP;
    }
	
	public static double calcStrength(int Lvl, int Class){
        double Strength=0;
        if (Class==1){
            Strength = 27.5+2.5*(Lvl-1);
        }else if (Class==2) {
            Strength = 26.5+1.5*(Lvl-1);
        }else if (Class==3){
            Strength = 27+2*(Lvl-1);
        }
        return Strength;
    }

	public static double calculateDamage(int aLvl, int dLvl, double Strength, double Guard, double HP){
        Random randModifier = new Random();
        double RandomModifier = (double) randModifier.nextInt(7)/10;
        RandomModifier = RandomModifier+3;
        double autoLoss = (0.125+(0.005*(aLvl-dLvl)))*HP;
        if (autoLoss<0){
            autoLoss=0;
        }
        double Damage = Math.floor(Strength/Guard*RandomModifier+autoLoss);
        if (Damage<1){
            Damage=1;
        }
        return Damage;
    }
	
	public static boolean critCheck(int StrengthClass, int GuardClass, double aLvl, double dLvl){
        boolean Critical;
        double aFortune=0, dFortune=0;
        if (StrengthClass==1){
            aFortune=11.8+(aLvl-1)*1.8;
        }else if (StrengthClass==2){
            aFortune=5.9+(aLvl-1)*0.9;
        }else if (StrengthClass==3){
            aFortune=17.7+(aLvl-1)*2.7;
        }
        if (GuardClass==1){
            dFortune=11.8+(aLvl-1)*1.8;
        }else if (GuardClass==2){
            dFortune=5.9+(aLvl-1)*0.9;
        } else if (GuardClass==3){
            dFortune=17.7+(aLvl-1)*2.7;
        }
        double chance = 15+(aFortune-dFortune)/10;
        Random critInt = new Random();
        Random critDouble = new Random();
        double critExperimental = (double) critInt.nextInt(100)+critDouble.nextDouble();
        if (critExperimental<=chance){
            Critical=true;
        }else{
            Critical=false;
        }
        return Critical;
    }
	
    public static double calcMagic(int Lvl, int Class)
    {
        double Magic=0;
        if (Class==1)
        {
            Magic = 208+8*(Lvl-1);
        }
        else if (Class==2)
        {
            Magic = 212+12*(Lvl-1);
        }
        else if (Class==3)
        {
            Magic = 204+4*(Lvl-1);
        }
        return Magic;
    }
    
    public static double calcGuard(int Lvl, int Class)
    {
        double Guard=0;
        if (Class==1)
        {
            Guard = 27.5+2.5*(Lvl-1);
        }
        else if (Class==2)
        {
            Guard = 26.5+1.5*(Lvl-1);
        }
        else if (Class==3)
        {
            Guard = 27+1.5*(Lvl-1);
        }
        return Guard;
    }
    
    public static double calcMagicDamage(int aLvl, int dLvl, double Magic, double Guard, double HP)
    {
        double autoLoss = (0.125+(0.005*(aLvl-dLvl)))*HP;
        System.out.println("Autoloss: "+autoLoss);
        if (autoLoss<0)
        {
            autoLoss=0;
        }
        double Damage = Math.floor(Magic/Guard*2+autoLoss);
        if (Damage<1)
        {
            Damage=1;
        }
        return Damage;
    }

}