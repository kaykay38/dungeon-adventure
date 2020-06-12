package dungeon;

import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Title: DungeonAdventure.java
 * 
 * Documentation of Work Done:
 * 
 * We restarted from scratch from the first refactor assignment because we felt that our original refactors would not work easily with
 * new implementations.
 * 
 * Austin:
 * 	Created original Room class
 * 	Made first version of dungeon spawning
 * 	Made first version of dungeon gameplay
 * 	Implemented menus options for the hero
 * 	(Focused on getting a foundation laid out)
 * 	UML Diagram
 * 
 * Mia:
 * 	Consolidated Austin's foundational code
 * 	Made the code more efficient
 * 	Vision potion implementation
 * 	Exit game implementation
 * 	Formatted code for easy readability
 * 
 * Nick
 * 	Attack Interface and its behaviors.
 * 	Flyweight Pattern for Attack Interface using AttackFactory
 * 	Added new Heroes and Monsters and new Attack Interface Behaviors
 * 	Error checked gameplay and logic in gameplay
 *  Added function to replay the game
 *  Provided skeleton of UML diagram
 * 
 * As a group:
 * 	On 06/10/2020 all group members worked on one machine to bring together the logic from one machine onto the machine with more efficient code.
 * 	Our code versions got split and massive changes were made on two separate machines so we decided to work on one machine to avoid
 * 	furthering the divide. All group members were involved in this, taking up certain tasks on the machine. After this, we learned that we could merge
 *  code on github and not override other new code. 
 * 
 * 
 * @author Austin Lidey, Mia Hunt, Nick Savoie
 *
 */




public class DungeonAdventure {
	private static int roomEntries = 0;
	private static boolean quitGame = false;
	private static String initialDungeonMap;
	private Dungeon dungeon;
	private Hero player;
	private static boolean gameWin = false;
	private static boolean playAgain = false;
	
	public static void main(String[] args) 
	{
		
		do {
		DungeonAdventure curGame = new DungeonAdventure();
		
		/** Could we implement the save game feature here? --Austin**/
		gameIntroduction();
		Scanner kb = new Scanner(System.in);
		kb.nextLine();
		curGame.player = Dungeon.chooseHero();
		
		curGame.dungeon = new Dungeon();
		int[] entranceCoords = {0,0};
		int[] exitCoords = {0,0};
		int[] pillarCoords = new int[8];
		
		generatePortals(entranceCoords, exitCoords); //Entrances & Exits placed & values set...
		generatePillars(pillarCoords, entranceCoords, exitCoords); //Pillar Coordinates selected and set...
		generateROOMS(curGame.dungeon, pillarCoords, entranceCoords, exitCoords, curGame.player); //Begins filling rooms with appropriate GAME OBJECTS
		initialDungeonMap = curGame.dungeon.toString();
		
		/**DEBUG TESTS*/
		//curGame.dungeon.dungeonRooms[0][0].setRoomItem("v");
		//curGame.dungeon.dungeonRooms[0][2].setRoomItem("v");
		//curGame.dungeon.dungeonRooms[0][4].setRoomItem("v");
		//curGame.dungeon.dungeonRooms[1][0].setRoomItem("v");
		//curGame.dungeon.dungeonRooms[3][4].setRoomItem("v");
		//curGame.dungeon.dungeonRooms[4][0].setRoomItem("v");
		//curGame.dungeon.dungeonRooms[4][3].setRoomItem("v");
		//curGame.dungeon.dungeonRooms[4][4].setRoomItem("v");
		//curGame.dungeon.dungeonRooms[2][2].setRoomItem("v");
		//System.out.println(curGame.dungeon);
		
		boolean notMoved = true;
		
		while(curGame.player.isAlive() && !quitGame)
		{
			System.out.println(curGame.player.stats());
			
			if (notMoved)
			{
				System.out.println(curGame.dungeon.dungeonRooms[curGame.player.getRow()][curGame.player.getCol()].displayStat());
				notMoved = false;
			}
			movementCommand(curGame.dungeon, curGame.player);
			//System.out.println(curGame.dungeon.dungeonRooms[curGame.player.getRow()][curGame.player.getCol()].displayStat());	//Displays the current room of the Hero
			evaluateRoom(curGame.dungeon, curGame.player);
			
			if(!curGame.player.isAlive()) {
				System.out.println(initialDungeonMap);
				displayDeathScreen(curGame.dungeon);
				playAgain();
				if(quitGame) {
					exitGame();
				}
				else
					break;
			}
			
			if(gameWin)
			{
				System.out.println(initialDungeonMap);
				playAgain();
				if(quitGame) {
					exitGame();
				}
				else
					gameWin = false;
					break;
			}
			
		} // End gameplay while loop
		
		
		} while (playAgain);
			
	}

	 /**-----------------------------------------------------------------------------------------------
	  
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void evaluateRoom(Dungeon dungeon, Hero hero) 
	 {		
		 int row = hero.getRow(), col = hero.getCol();
		 int changes = 0;
		 //System.out.println(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].displayStat());	//Displays the current room of the Hero 
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasHealthPOTION())
		 {
			System.out.println("\n********* Health potion found! *********"); 
			dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasHealthPOTION(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setHealthPotions(hero.getHealthPotions()+1);
			 changes++;
			 
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasVisionPOTION())
		 {
			 System.out.println("\n********* Vision potion found! *********");
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasVisionPOTION(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setVisionPotions(hero.getVisionPotions()+1);
			 changes++;
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasPILLAR_A())
		 {
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasPILLAR_A(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setOoPillars(hero.getOoPillars()+1);
			 System.out.println("\n****** (" + hero.getOoPillars() + "/4) Pillar of Abstraction found! ******"); 
			 changes++;
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasPILLAR_E())
		 {
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasPILLAR_E(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setOoPillars(hero.getOoPillars()+1);
			 System.out.println("\n****** (" + hero.getOoPillars() + "/4) Pillar of Encapsulation found! ******"); 
			 changes++;
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasPILLAR_I())
		 {
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasPILLAR_I(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setOoPillars(hero.getOoPillars()+1);
			 System.out.println("\n****** (" + hero.getOoPillars() + "/4) Pillar of Inheritance found! ******"); 
			 changes++;
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasPILLAR_P())
		 {
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasPILLAR_P(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setOoPillars(hero.getOoPillars()+1);
			 System.out.println("\n****** (" + hero.getOoPillars() + "/4) Pillar of Polymorphism found! ******"); 
			 changes++;
		 }
		 if(dungeon.dungeonRooms[row][col].hasPIT())
		{
			 int pitDamage = (int)((Math.random() * 20) + 1);
			 System.out.println("\nYou fell into a pit, taking " + pitDamage + " points of damage!");
			 hero.setHitPoints(hero.getHitPoints() - pitDamage);
			 if(hero.getHitPoints() < 1)
				 System.out.println(hero.getName() + " died by falling into a pit.");
		}
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasMONSTER())
		 {
			 System.out.println("\n********** MONSTER!!! **********\n");
			 System.out.println("You have stumbled upon a room with a monster, fight to the death!\n");
			 Monster theMonster = MonsterFactory.generateMonster();
			 Dungeon.battle(hero, theMonster);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasMONSTER(theMonster.isAlive());
			 if(hero.isAlive())
			 {
				 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
				 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
				 hero.setHealthPotions(hero.getHealthPotions() + theMonster.dropHealthPotion());
			 }
			 changes++;
			 
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].isEXIT())
		 {
			 roomEntries++;
			 if(roomEntries == 1) 
			 {
				 System.out.println("\nYou enter a room through a door labeled \"Portal Room\" to discover\n"
								  + "a large obsidian structure -- a dead portal. It can be restored by finding\n"
								  + "the Four Pillars of OO. This is your ticket home!\n\n");
			 //Add check for existing pillars to be placed
			 }
			 if(roomEntries > 1 && hero.getOoPillars() < 4)
			 {
				 System.out.println("\nYou re-enter the portal room, but without the pillars, you cannot use it.\n"
								  + "Tip: Did you try looking up yet?");
			 }
			 if(hero.getOoPillars() == 4)
			 {
				System.out.println("\nWoah, you found all the pillars! Good job, the portal has now been activated.\n"
								  + "\t>>" + hero.getName() + "steps into the purple glow of the obsidian portal, fading from the dungeon");
				//Game is over, need to figure out the right way to handle this
				System.out.print("\nYou beat the game!");
				setGameWin(true);
			 }
			 
		 }
		 
		 if (changes > 0)
			 System.out.println(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].displayStat());
		 
	 }
	 
	 /**-----------------------------------------------------------------------------------------------
	  
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void generatePortals(int[] entrance, int[] exit)
	 {
		 entrance[0] = 0; // Sets entrance row coordinate
		 entrance[1] = (int)(Math.random() * 5); // sets entrance col coordinate
		 exit[0] = 4; // Sets exit row coordinate
		 exit[1] = (int)(Math.random() * 5); // Sets exit col coordinate
	 }
	 
	 /** --------------------------------------------------------------------------------------------
	   Randommly generates pillar coordinates while checks to make sure it's not an entrance, exit, 
	   or an existing pillar coordinate.
	   - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020
	  -----------------------------------------------------------------------------------------------*/
	 public static void generatePillars(int[] pillarCoords, int [] entrance, int[] exit)
	{

		for(int index = 0; index < pillarCoords.length - 1; index++)
		{
			boolean ValidCoords = false;
			int pillarRow = 0, pillarCol = 0;
			while(!ValidCoords)
			{
			pillarRow = (int)(Math.random() * 5);
			pillarCol = (int)(Math.random() * 5);
			
				if(( pillarRow != entrance[0] || pillarCol != entrance[1]) && (pillarRow != exit[0] || pillarCol != exit[1]))
				{	//It's not at the entrance or exit, but is it the same spot as a previous pillar?
					if(!samePillarCoords(pillarRow, pillarCol, pillarCoords))
					{
						ValidCoords = true; 	//Valid coords to place in Pillar, becase there's no portals or Pillars :-)
					}
				}
			}	//We now have valid coordinates
			pillarCoords[index] = pillarRow;
			pillarCoords[index + 1] = pillarCol;
			index++;
		}

	}

	 /**-----------------------------------------------------------------------------------------------
	  Checks if randomly generated integer pillar coordinates are valid by comparing to
	  the array of already created pillar coordinates to be sure no coordinates are the same.
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	  private static boolean samePillarCoords(int pillarRow, int pillarCol, int[] pillarCoords)
	  {
		 for(int index = 0; index < pillarCoords.length - 1; index++) {    
			 if(index % 2 == 0) { // Checks for row coordinate in the even index.
				  if(pillarCoords[index] == pillarRow && pillarCoords[index + 1] == pillarCol) {
					  return true;
				  }
			 } 
		  }
		  return false;
	  }
	 
	 /**-----------------------------------------------------------------------------------------------
	  Generates dungeon rooms by placing entrance, exit, pillars of OO, and items.
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void generateROOMS(Dungeon dungeon, int[] pillarCoords, int[] entrance, int exit[], Hero hero)
	 {
		 for(int row = 0; row < 5; row++)
		 {
			 for(int col = 0; col < 5; col++)
			 {
				 if(row == entrance[0] && col == entrance[1])
				 {
				   //Then only place the entrance, nothing else!
					 dungeon.dungeonRooms[row][col] = new Room();
					 dungeon.dungeonRooms[row][col].setRoomCoords(row, col);
					 dungeon.dungeonRooms[row][col].setIsEntrance(true);
					 hero.setPosition(row, col);
					 continue;
				 }
				 if(row == exit[0] && col == exit[1])
				 {
				   //Then only place the exit, nothing else!
					 dungeon.dungeonRooms[row][col] = new Room();
					 dungeon.dungeonRooms[row][col].setRoomCoords(row, col);
					 dungeon.dungeonRooms[row][col].setIsExit(true);
					 continue;
				 }
				 else {
				   //Valid spot to place GAME OBJECTS
					 if(samePillarCoords(row, col, pillarCoords))
					 {
						 //Valid pillarCoords, therefore place pillar here!
						 dungeon.dungeonRooms[row][col] = new Room();
						 dungeon.dungeonRooms[row][col].setRoomCoords(row, col);
						 dungeon.dungeonRooms[row][col].placeRandomPillar();
						 dungeon.dungeonRooms[row][col].generateRoomItems();
						 continue;
					 }
					 else {
						 //General case for placing items -- No pillars, no entrance, no exit. Wildcard. 
					 dungeon.dungeonRooms[row][col] = new Room();
					 dungeon.dungeonRooms[row][col].setRoomCoords(row, col);
					 dungeon.dungeonRooms[row][col].generateRoomItems();
					 }
				}		
			}
		}
	 }
	 
	 /**-----------------------------------------------------------------------------------------------
	  
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void moveHero(String direction, Hero hero, Dungeon dungeon)
	 {
		 int row = hero.getRow(); int col = hero.getCol();
		 //This will change the X/Y coords of the hero
		 switch(direction.toLowerCase())
		 {
			 case "n":
				 if(row == 0)
				 {
					 System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \n\tLet's try another direction!\n");
				 }else 
				 hero.setRow(row - 1);;
				 return;
			 case "e":
				 if(col == 4)
				 {
					 System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \nLet's try another direction!\n");
				 }else 
				 hero.setCol(col + 1);
				 return;
			 case "s": 
				 if(row == 4)
				 {
					 System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \nLet's try another direction!\n");
				 }else 
				 hero.setRow(row + 1);
				 return;
			 case "w": 
				 if(col == 0)
				 {
					 System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \nLet's try another direction!\n");
				 }else 
				 hero.setCol(col - 1);
				 return;
			case "i":
				 inventoryOptions(hero, dungeon);
				 return;
			case "q":
				 System.out.print("\nAre you sure you want to quit? (y/n)\n>> ");
				 char quit = Keyboard.readChar();
				 if (quit == 'y' || quit == 'Y') {
					System.out.println(dungeon); 
					exitGame();
				 }
				 break;
			case "i am a cheater": // Prints entire dungeon map
				 System.out.println(dungeon);
				 return;
			case "loco": 
				 System.out.println("\nCurrent room: " + "(" +hero.getRow()+ "," +hero.getCol()+ ")");
				 return;
		 }
	 }
	 
	 /**-----------------------------------------------------------------------------------------------
	  
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void movementCommand(Dungeon dungeon, Hero hero)
	 {
		 String direction;
		 
		 System.out.print("(i = inventory options, n = north, s = south, e = east, w = west, q = quit)\nEnter a command:\n>> ");
		 direction = Keyboard.readString();
		 moveHero(direction, hero, dungeon);
		 System.out.println(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].displayStat());	//Displays the current room of the Hero
	 }

	/**-----------------------------------------------------------------------------------------------
	  
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 
	 public static void inventoryOptions(Hero hero, Dungeon dungeon)
	{
	System.out.print("\n1) Use Health Potion   "
					 + "2) Use Vision Potion   "
					 + "3) Continue\n>> ");
	int choice = Keyboard.readInt();
	while(choice > 1 || choice < 3) {
		switch(choice)
		{
			case 1:
				if(hero.getHealthPotions() > 0)
				{
					hero.setHealthPotions(hero.getHealthPotions()-1);
					int prevHP = hero.getHitPoints();
					hero.setHitPoints(prevHP + hero.hpPotion());
					System.out.println("\nYou wipe the red elixir from your lips, health went up " 
											+ (hero.getHitPoints() - prevHP) + " points");
					System.out.println("\n\tCurrent Hitpoints: " + hero.getHitPoints());
					return;
				}else
					System.out.println("\nYou look in your potion bag and admire the dust...\n\t>No health potions available");
					return;
				
			case 2:
				if(hero.getVisionPotions() > 0) {
					String map = dungeon.visionSurroundingRooms(hero.getRow(), hero.getCol());
					System.out.println(map);
					System.out.println("\nYou have used a vision potion, " + hero.getVisionPotions() + " remaining.");
					hero.setVisionPotions(hero.getVisionPotions()-1);
				}	
				else { System.out.println("\nYou have 0 vision potions.");
				return;
				}

			case 3:
				return;
				
			default:
				System.out.println("\nInvalid choice, try again");
				System.out.print("1) Use Health Potion   "
						 + "2) Use Vision Potion   "
						 + "3) Continue\n>> ");
				choice = Keyboard.readInt();
		}
	}
}
	 
	/** */
	 private static void displayDeathScreen(Dungeon dungeon) 
	 {
		 displayDeathImage();
		 System.out.println(initialDungeonMap);
	 }

	 private static void playAgain() {
		System.out.println("\nDo you want to play again? Hit 'n' to quit. Anything else to play again.\n>> ");
			char choice = Keyboard.readChar();
			if(choice == 'n' || choice == 'N')
			{
				setQuitGame(true);
			} else
				setPlayAgain(true);
	 }

	 /** */
	 private static void setPlayAgain(boolean choice)
	 {
		 playAgain = choice;
	 }

	 /** */
	private static void setGameWin(boolean gameWon)
	{
		gameWin = gameWon;
	}

	/** Game introduction Text **/
	private static void gameIntroduction() {

		System.out.println("\n\n\n------------------------------------------------------------------------------------------------");
		System.out.println("  _____                                                   _                 _                  \r\n" + 
				" |  __ \\                                         /\\      | |               | |                 \r\n" + 
				" | |  | |_   _ _ __   __ _  ___  ___  _ __      /  \\   __| |_   _____ _ __ | |_ _   _ _ __ ___ \r\n" + 
				" | |  | | | | | '_ \\ / _` |/ _ \\/ _ \\| '_ \\    / /\\ \\ / _` \\ \\ / / _ \\ '_ \\| __| | | | '__/ _ \\\r\n" + 
				" | |__| | |_| | | | | (_| |  __/ (_) | | | |  / ____ \\ (_| |\\ V /  __/ | | | |_| |_| | | |  __/\r\n" + 
				" |_____/ \\__,_|_| |_|\\__, |\\___|\\___/|_| |_| /_/    \\_\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|\r\n" + 
				"                      __/ |                                                                    \r\n" + 
				"                     |___/   "
				+ "\n------------------------------------------------------------------------------------------------\n\n\n");
		
		System.out.println("----------------------------------------------------------" + "\n\t     WELCOME TO DUNGEON ADVENTURE!\n----------------------------------------------------------\n");
		System.out.println("Your quest to seek the four pillars of O.O. has brought"+
							"\nyou to the foulest, most dankest dungeon in all the land...\n  ");
		System.out.println("To escape the dungeon, you must brave the monsters,"
						+"\ndodge the pits, and seek the four Pillars of O.O.\n");
																	//"or press L to load a saved game."
	 //DEBUG: REMOVE ONCE MAIN MENU IS DONE
		System.out.println("\n------------------------Symbol Key------------------------");
		System.out.println("   Multiple Items = M      |  Empty room = E");
		System.out.println("   Entrance = I            |  Exit = O");
		System.out.println("   Monster = X             |  Pit = P");
		System.out.println("   Healing Potion = H      |  Vision Potion = V");
		System.out.println("\n");

		System.out.println("-------------------Pillars to be Found--------------------");
		System.out.println("   Pillar Abstraction = Y  |  Pillar Encapsulation = Y");
		System.out.println("   Pillar Inheritance = Y  |  Pillar Polymorphism = Y");
		System.out.println("----------------------------------------------------------");
		System.out.println("  ");
		System.out.println("                    Example of Room");
		System.out.println("\t\t\t * - *\r\n" 
						+ "\t\t\t | P | \n\t\t\t * - *\n----------------------------------------------------------\n"
						+ "\n\t\tPress any key to continue\t\t\n"
						);
													
	 }//end of GameIntroduction

	 /** */
	 private static void exitGame() {
		System.out.println("\n\nThank you for playing."
		+"\nQuitting game...\n\n\n"
		+"\n   ---------------------------------------------------"
		+"\n                          Credits                     "
		+"\n   ---------------------------------------------------"
		+"\n"
		+"\n     Creators: Austin Lidey, Mia Hunt, Nick Savoie" 
		+"\n     Date of creation: May-June 2020\n"
		+"\n"
		+"\n       This is a final project we did for our design" 
		+"\n       patterns class at XXX University during the"
		+"\n       COVID-19 epidemic. Blood, sweat, and tears"
		+"\n       were were shed in the making of this game in"
		+"\n       the midst of such trying times of quarantine"
		+"\n       and poor 3MB/s internet."
		+"\n"
		+"\n   ---------------------------------------------------");
		System.exit(0);
	}

	private static void displayDeathImage()
	 {
		System.out.println("	\r\n\n" + 
				"                     .ed\"\"\"\" \"\"\"$$$$be.\r\n" + 
				"                   -\"           ^\"\"**$$$e.\r\n" + 
				"                 .\"                   '$$$c\r\n" + 
				"                /                      \"4$$b\r\n" + 
				"               d  3                      $$$$\r\n" + 
				"               $  *                   .$$$$$$\r\n" + 
				"              .$  ^c           $$$$$e$$$$$$$$.\r\n" + 
				"              d$L  4.         4$$$$$$$$$$$$$$b\r\n" + 
				"              $$$$b ^ceeeee.  4$$ECL.F*$$$$$$$\r\n" + 
				"  e$\"\"=.      $$$$P d$$$$F $ $$$$$$$$$- $$$$$$\r\n" + 
				" z$$b. ^c     3$$$F \"$$$$b   $\"$$$$$$$  $$$$*\"      .=\"\"$c\r\n" + 
				"4$$$$L        $$P\"  \"$$b   .$ $$$$$...e$$        .=  e$$$.\r\n" + 
				"^*$$$$$c  %..   *c    ..    $$ 3$$$$$$$$$$eF     zP  d$$$$$\r\n" + 
				"  \"**$$$ec   \"   %ce\"\"    $$$  $$$$$$$$$$*    .r\" =$$$$P\"\"\r\n" + 
				"        \"*$b.  \"c  *$e.    *** d$$$$$\"L$$    .d\"  e$$***\"\r\n" + 
				"          ^*$$c ^$c $$$      4J$$$$$% $$$ .e*\".eeP\"\r\n" + 
				"             \"$$$$$$\"'$=e....$*$$**$cz$$\" \"..d$*\"\r\n" + 
				"               \"*$$$  *=%4.$ L L$ P3$$$F $$$P\"\r\n" + 
				"                  \"$   \"%*ebJLzb$e$$$$$b $P\"\r\n" + 
				"                    %..      4$$$$$$$$$$ \"\r\n" + 
				"                     $$$e   z$$$$$$$$$$%\r\n" + 
				"                      \"*$c  \"$$$$$$$P\"\r\n" + 
				"                       .\"\"\"*$$$$$$$$bc\r\n" + 
				"                    .-\"    .$***$$$\"\"\"*e.\r\n" + 
				"                 .-\"    .e$\"     \"*$c  ^*b.\r\n" + 
				"          .=*\"\"\"\"    .e$*\"          \"*bc  \"*$e..\r\n" + 
				"        .$\"        .z*\"               ^*$e.   \"*****e.\r\n" + 
				"        $$ee$c   .d\"                     \"*$.        3.\r\n" + 
				"        ^*$E\")$..$\"                         *   .ee==d%\r\n" + 
				"           $.d$$$*                           *  J$$$e*\r\n" + 
				"            \"\"\"\"\"                              \"$$$\""
				+ "\n\t\t         YOU ARE DEAD.\n");
		try {
			TimeUnit.MILLISECONDS.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 /** */
	private static void setQuitGame(boolean quitGame1) {
		quitGame = quitGame1;
	}

 }//end of CLASS 
