package dev.thenamegenerator.MasterQuestAlpha.gfx;
import java.awt.image.*;
public class Assets
{  
   public static BufferedImage grass, water, path, dirt;
   public static BufferedImage window, wall, roof, door;
   public static BufferedImage playerRight, playerLeft;
   
   public static BufferedImage inventory;
   
   //entity images
   public static BufferedImage CL, CR, CD, CU;
   public static BufferedImage ER, EL;
   public static BufferedImage spider;
   
   //food icon images
   public static BufferedImage carrot;
   public static BufferedImage banana;
   public static BufferedImage watermelon;
   public static BufferedImage chicken;
   public static BufferedImage apple;
   public static BufferedImage fish;
   public static BufferedImage bread;
   
   //other stuff
   public static BufferedImage bookIcon;
   
   //weapon icon images
   public static BufferedImage ironShortSword;
   
   public static void init(){
	   SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("tile/TileSheet.png"));
	   grass = sheet.crop(0, 0, 32, 32);
	   water = sheet.crop(32, 0, 32, 32);
	   path = sheet.crop(0, 32, 32, 32);
	   dirt = sheet.crop(32, 32, 32, 32);

	   sheet.changeImage(ImageLoader.loadImage("tile/HouseTile.png"));
	   window = sheet.crop(0, 0, 32, 32);
	   wall = sheet.crop(32, 0, 32, 32);
	   roof = sheet.crop(0, 32, 32, 32);
	   door = sheet.crop(32, 32, 32, 32);
	   
	   playerRight = ImageLoader.loadImage("mobs/player.png");
	   playerLeft = ImageLoader.loadImage("mobs/playerLeft.png");
	   
	   sheet.changeImage(ImageLoader.loadImage("mobs/Chicken.png"));
	   CD = sheet.crop(0, 0, 32, 32);
	   CR = sheet.crop(32, 0, 32, 32);
	   CL = sheet.crop(32, 32, 32, 32);
	   CU = sheet.crop(0, 32, 32, 32);
	   
	   sheet.changeImage(ImageLoader.loadImage("mobs/Elephant.png"));
	   EL = sheet.crop(0, 0, 64, 32);
	   ER = sheet.crop(0, 32, 64, 32);
	   
	   inventory = ImageLoader.loadImage("Inventory.png");
	   
	   carrot = ImageLoader.loadImage("food/Carrot.png");
	   banana = ImageLoader.loadImage("food/Banana.png");
	   watermelon = ImageLoader.loadImage("food/Watermelon.png");
	   chicken = ImageLoader.loadImage("food/chicken.png");
	   apple = ImageLoader.loadImage("food/Apple.png");
	   fish = ImageLoader.loadImage("food/Fish.png");
	   bread = ImageLoader.loadImage("food/Bread.png");
	   
	   bookIcon = ImageLoader.loadImage("Book.png");
	   
	   ironShortSword = ImageLoader.loadImage("weapons/Iron Shortsword.png");
	   
	   spider = ImageLoader.loadImage("mobs/spider.png");
   }
   
}