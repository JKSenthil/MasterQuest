package dev.thenamegenerator.MasterQuestAlpha.gfx;
import java.awt.image.*;
public class Assets
{
   private static String fileName = "TileSheetg.png";
   private static String houseTile = "HouseTile.png";
   private static String PlayerRight = "player.png";
   private static String PlayerLeft = "playerLeft.png";
   
   public static BufferedImage image, image2, image3, image4;
   public static BufferedImage grass, water, path, dirt;
   public static BufferedImage window, wall, roof, door;
   public static BufferedImage playerRight, playerLeft;
   
   public static BufferedImage inventory;
   
   //entity images
   public static BufferedImage CL, CR, CD, CU;
   public static BufferedImage ER, EL;
   
   public static void init(){
	   image = ImageLoader.loadImage(fileName);
	   SpriteSheet sheet = new SpriteSheet(image);
	   grass = sheet.crop(0, 0, 32, 32);
	   water = sheet.crop(32, 0, 32, 32);
	   path = sheet.crop(0, 32, 32, 32);
	   dirt = sheet.crop(32, 32, 32, 32);
	   
	   image2 = ImageLoader.loadImage(houseTile);
	   sheet.changeImage(image2);
	   window = sheet.crop(0, 0, 32, 32);
	   wall = sheet.crop(32, 0, 32, 32);
	   roof = sheet.crop(0, 32, 32, 32);
	   door = sheet.crop(32, 32, 32, 32);
	   
	   playerRight = ImageLoader.loadImage(PlayerRight);
	   playerLeft = ImageLoader.loadImage(PlayerLeft);
	   
	   image3 = ImageLoader.loadImage("Chicken.png");
	   sheet.changeImage(image3);
	   CD = sheet.crop(0, 0, 32, 32);
	   CR = sheet.crop(32, 0, 32, 32);
	   CL = sheet.crop(32, 32, 32, 32);
	   CU = sheet.crop(0, 32, 32, 32);
	   
	   image4 = ImageLoader.loadImage("Elephant.png");
	   sheet.changeImage(image4);
	   EL = sheet.crop(0, 0, 64, 32);
	   ER = sheet.crop(0, 32, 64, 32);
	   
	   inventory = ImageLoader.loadImage("Inventory.png");
   }
   
}
