package dev.thenamegenerator.MasterQuestAlpha.gfx;
import java.awt.image.*;

public class Assets{  
	
	public static BufferedImage grass, water, path, dirt;
	public static BufferedImage window, wall, roof, door;
	public static BufferedImage tree, flower, bush;
	public static BufferedImage bridge;
	public static BufferedImage urPath, ulPath, drPath, dlPath;
	public static BufferedImage well;
	public static BufferedImage hFence, vFence;
	public static BufferedImage rockGrass, rockWater;
	public static BufferedImage grassTop, grassBottom, grassLeft, grassRight;
	public static BufferedImage grassDL, grassDR, grassUL, grassUR;
	public static BufferedImage proDoor;
	public static BufferedImage lightBrick, darkBrick, wood;
	public static BufferedImage brickWindowDown, brickWindowUp;
	public static BufferedImage woodWindowDown, woodWindowUp;
	public static BufferedImage orangeRoof, orangePeak, blueRoof, bluePeak, greenRoof, greenPeak;
	public static BufferedImage innSign, bootSign, armorSign, potionSign; 
	public static BufferedImage garden1, garden2, garden3, garden4;
	public static BufferedImage woodB;
	public static BufferedImage woodRoof, woodPeak;
	
	//player materials
	public static BufferedImage playerRight, playerLeft, playerUp, playerDown;
	public static BufferedImage BplayerRight, BplayerLeft, BplayerUp, BplayerDown;
	
	public static BufferedImage blackHairUp, blackHairDown, blackHairLeft, blackHairRight;
	public static BufferedImage brownHairUp, brownHairDown, brownHairLeft, brownHairRight;
	public static BufferedImage redHairUp, redHairDown, redHairLeft, redHairRight;
	public static BufferedImage pinkHairUp, pinkHairDown, pinkHairLeft, pinkHairRight;
	public static BufferedImage yellowHairUp, yellowHairDown, yellowHairLeft, yellowHairRight;
	public static BufferedImage grayHairUp, grayHairDown, grayHairLeft, grayHairRight;
	
	//armor stuff
	public static BufferedImage orangeRobeUp, orangeRobeDown, orangeRobeLeft, orangeRobeRight;
	public static BufferedImage blackRobeUp, blackRobeDown, blackRobeLeft, blackRobeRight;
	public static BufferedImage redRobeUp, redRobeDown, redRobeLeft, redRobeRight;
	public static BufferedImage pinkRobeUp, pinkRobeDown, pinkRobeLeft, pinkRobeRight;
	public static BufferedImage greenRobeUp, greenRobeDown, greenRobeLeft, greenRobeRight;
	public static BufferedImage blueRobeUp, blueRobeDown, blueRobeLeft, blueRobeRight;
   
   
   public static BufferedImage inventory;
   
   public static BufferedImage trashButton, equipButton;
   
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
   
   //array of tiles
   public static BufferedImage[] groupTiles;
   
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
   
   public static void init2(){
	    SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("tile/TileSheetgg.png"));
		grass = sheet.crop(4*32, 0, 32, 32);
		water = sheet.crop(7*32, 12*32, 32, 32);
		path = sheet.crop(0, 11*32, 32, 32);
	    dirt = sheet.crop(0, 4*32, 32, 32);
	   
	    tree = sheet.crop(0, 0, 32, 32);
		flower = sheet.crop(32, 7*32, 32, 32);
		bush = sheet.crop(7*32, 2*32, 32, 32);
		   
		bridge = sheet.crop(10*32, 11*32, 32, 32);
		
		ulPath = sheet.crop(9*32, 5*32, 32, 32);
		dlPath = sheet.crop(9*32, 9*32, 32, 32);
		urPath = sheet.crop(13*32, 5*32, 32, 32);
		drPath = sheet.crop(13*32, 9*32, 32, 32);
		
		well = sheet.crop(7*32, 0, 32, 32);
		
		hFence = sheet.crop(32, 6*32, 32, 32);
		vFence = sheet.crop(0, 6*32, 32, 32);
		
		rockGrass = sheet.crop(16*32, 7*32, 32, 32);
		rockWater = sheet.crop(19*32, 9*32, 32, 32);
		
		grassTop = sheet.crop(6*32, 13*32, 32, 32);
		grassBottom = sheet.crop(7*32, 9*32, 32, 32);
		grassLeft = sheet.crop(20*32, 11*32, 32, 32);
		grassRight = sheet.crop(18*32, 9*32, 32, 32);
		
		grassDL = sheet.crop(8*32, 10*32, 32, 32);
		grassDR = sheet.crop(18*32, 7*32, 32, 32);
		grassUL = sheet.crop(17*32, 6*32, 32, 32);
		grassUR = ImageLoader.loadImage("tile/grassTG.png");
		
	    sheet.changeImage(ImageLoader.loadImage("tile/HouseTile.png"));
		window = sheet.crop(0, 0, 32, 32);
		wall = sheet.crop(32, 0, 32, 32);
		roof = sheet.crop(0, 32, 32, 32);
		door = sheet.crop(32, 32, 32, 32);
		
		sheet.changeImage(ImageLoader.loadImage("tile/AnotherTile.png"));
		//grass = sheet.crop(5*32, 8*32, 32, 32);
		//well = sheet.crop(7*32, 8*32, 32, 32);
		//rockGrass = sheet.crop(7*32, 9*32, 32, 32);
		
		proDoor = sheet.crop(3*32, 3*32, 32, 32);
		lightBrick = sheet.crop(32, 3*32, 32, 32);
		darkBrick = sheet.crop(15*32, 2*32, 32, 32);
		wood = sheet.crop(19*32, 2*32, 32, 32);
		brickWindowDown = sheet.crop(13*32, 2*32, 32, 32);
		brickWindowUp = sheet.crop(13*32, 32, 32, 32);
		woodWindowDown = sheet.crop(18*32, 3*32, 32, 32);
		woodWindowUp = sheet.crop(18*32, 2*32, 32, 32);
		
		orangeRoof = sheet.crop(2*32, 32, 32, 32);
		orangePeak = sheet.crop(2*32, 0, 32, 32);
		blueRoof = sheet.crop(12*32, 17*32, 32, 32);
		bluePeak = sheet.crop(6*32, 0, 32, 32);
		greenRoof = sheet.crop(32, 11*32, 32, 32);
		greenPeak = sheet.crop(32, 10*32, 32, 32);
		
		bootSign = sheet.crop(3*32, 2*32, 32, 32);
		innSign = sheet.crop(14*32, 17*32, 32, 32);
		armorSign = sheet.crop(6*32, 32, 32, 32);
		potionSign = sheet.crop(12*32, 10*32, 32, 32); 
		
		garden1 = sheet.crop(19*32, 9*32, 32, 32);
		garden2 = sheet.crop(19*32, 10*32, 32, 32);
		garden3 = sheet.crop(20*32, 9*32, 32, 32);
		garden4 = sheet.crop(20*32, 10*32, 32, 32);
		
		woodB = sheet.crop(23*32, 3*32, 32, 32);
		
		woodRoof = sheet.crop(22*32, 8*32, 32, 32);
		woodPeak = sheet.crop(22*32, 7*32, 32, 32);
	   
	   sheet.changeImage(ImageLoader.loadImage("mobs/Chicken.png"));
	   CD = sheet.crop(0, 0, 32, 32);
	   CR = sheet.crop(32, 0, 32, 32);
	   CL = sheet.crop(32, 32, 32, 32);
	   CU = sheet.crop(0, 32, 32, 32);
	   
	   sheet.changeImage(ImageLoader.loadImage("mobs/Elephant.png"));
	   EL = sheet.crop(0, 0, 64, 32);
	   ER = sheet.crop(0, 32, 64, 32);
	   
	   inventory = ImageLoader.loadImage("inventory_sprites/Inventory.png");
	   
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
	   
	   BufferedImage[] temp = {grass, water, dirt, path, wall, door, window, roof, tree, flower, bush, bridge, ulPath, urPath, dlPath, drPath, well, hFence, vFence, rockGrass, rockWater, grassTop, grassBottom, grassLeft, grassRight, grassDL, grassDR,
				grassUL, grassUR, proDoor, lightBrick, darkBrick, wood, woodB, brickWindowUp, brickWindowDown, woodWindowUp, woodWindowDown, orangeRoof, orangePeak, blueRoof, bluePeak, greenRoof, greenPeak, woodRoof, woodPeak, innSign, bootSign, 
				armorSign, potionSign, garden1, garden2, garden3, garden4};
	   
	   groupTiles = new BufferedImage[temp.length];
	   for(int i = 0; i < groupTiles.length; i++){
		   groupTiles[i] = temp[i];
	   }
	   
	   sheet.changeImage(ImageLoader.loadImage("mobs/BaseSprites.png"));
	   playerDown = sheet.crop(0, 0, 32, 32);
	   playerRight = sheet.crop(32, 0, 32, 32);
	   playerLeft = sheet.crop(64, 0, 32, 32);
	   playerUp = sheet.crop(96, 0, 32, 32);
	   
	   BplayerDown = sheet.crop(0, 32, 32, 32);
	   BplayerRight = sheet.crop(32, 32, 32, 32);
	   BplayerLeft = sheet.crop(64, 32, 32, 32);
	   BplayerUp = sheet.crop(96, 32, 32, 32);
	   
	   sheet.changeImage(ImageLoader.loadImage("clothing/Hairs2.png"));
	   blackHairDown = sheet.crop(0, 0, 14, 2);
	   blackHairRight = sheet.crop(0, 18, 14, 8);
	   blackHairLeft = sheet.crop(50, 18, 14, 8);
	   blackHairUp = sheet.crop(15,36, 14, 8);
	   
	   brownHairDown = sheet.crop(0, 3, 14, 2);
	   brownHairRight = sheet.crop(0, 27, 14, 8);
	   brownHairLeft = sheet.crop(50, 27, 14, 8);
	   brownHairUp = sheet.crop(15, 45, 14, 8);
	   
	   redHairDown = sheet.crop(0, 6, 14, 2);
	   redHairRight = sheet.crop(0, 36, 14, 8);
	   redHairLeft = sheet.crop(50, 36, 14, 8);
	   redHairUp = sheet.crop(35, 36, 14, 8);
	   
	   pinkHairDown = sheet.crop(0, 9, 14, 2);
	   pinkHairRight = sheet.crop(0, 45, 14, 8);
	   pinkHairLeft = sheet.crop(50, 45, 14, 8);
	   pinkHairUp = sheet.crop(35, 45, 14, 8);
	   
	   yellowHairDown = sheet.crop(0, 12, 14, 2);
	   yellowHairRight = sheet.crop(15, 18, 14, 8);
	   yellowHairLeft = sheet.crop(35, 18, 14, 8);
	   yellowHairUp = sheet.crop(15, 9, 14, 8);
	   
	   grayHairDown = sheet.crop(0, 15, 14, 2);
	   grayHairRight = sheet.crop(15, 27, 14, 8);
	   grayHairLeft = sheet.crop(35, 27, 14, 8);
	   grayHairUp = sheet.crop(35, 9, 14, 8);
	   
	   sheet.changeImage(ImageLoader.loadImage("clothing/Robes.png"));
	   orangeRobeUp = sheet.crop(0, 0, 32, 32);
	   orangeRobeDown = sheet.crop(0, 0, 32, 32);
	   orangeRobeRight = sheet.crop(32, 0, 32, 32);
	   orangeRobeLeft = sheet.crop(64, 0, 32, 32);
	   
	   blackRobeUp = sheet.crop(0, 32, 32, 32);
	   blackRobeDown = sheet.crop(0, 32, 32, 32);
	   blackRobeRight = sheet.crop(32, 32, 32, 32);
	   blackRobeLeft = sheet.crop(64, 32, 32, 32);
	   
	   redRobeUp = sheet.crop(0, 64, 32, 32);
	   redRobeDown = sheet.crop(0, 64, 32, 32);
	   redRobeRight = sheet.crop(32, 64, 32, 32);
	   redRobeLeft = sheet.crop(64, 64, 32, 32);
	   
	   pinkRobeUp = sheet.crop(0, 96, 32, 32);
	   pinkRobeDown = sheet.crop(0, 96, 32, 32);
	   pinkRobeRight = sheet.crop(32, 96, 32, 32);
	   pinkRobeLeft = sheet.crop(64, 96, 32, 32);
	   
	   greenRobeUp = sheet.crop(0, 128, 32, 32);
	   greenRobeDown = sheet.crop(0, 128, 32, 32);
	   greenRobeRight = sheet.crop(32, 128, 32, 32);
	   greenRobeLeft = sheet.crop(64, 128, 32, 32);
	   
	   blueRobeUp = sheet.crop(0, 160, 32, 32);
	   blueRobeDown = sheet.crop(0, 160, 32, 32);
	   blueRobeRight = sheet.crop(32, 160, 32, 32);
	   blueRobeLeft = sheet.crop(64, 160, 32, 32);
	   
	   equipButton = ImageLoader.loadImage("inventory_sprites/equip.png");
	   trashButton = ImageLoader.loadImage("inventory_sprites/trash.png");
   }
   
}