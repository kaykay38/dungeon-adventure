
public class DungeonAdventure {
	private static int EntranceX, EntranceY, ExitX, ExitY, heroX, heroY, roomEntries = 0;
	private static boolean continueGame = true;
	private static boolean quitGame = false;

	private Dungeon dungeon;
	private Hero player;
	
	public static void main(String[] args) 
{
		int [] entranceCoords = {0,0}; 
		int [] exitCoords = {0,0};
		
	while(continueGame)
	{
		
			setQuitGame(false);
			int[] pillarCoordinates = new int[8];
			DungeonAdventure curGame = new DungeonAdventure();
			gameIntroduction();
			curGame.player = Dungeon.chooseHero();	//Should be prompted to pick a character
			curGame.dungeon = new Dungeon();
			
/** Dungeon generation setup **/
			generatePortals(entranceCoords, exitCoords);
			generatePillars(pillarCoordinates, entranceCoords, exitCoords);
			generateROOMS(curGame.dungeon, pillarCoordinates, entranceCoords, exitCoords); //Begins filling rooms with appropriate GAME OBJECTS
			displayROOMS(curGame.dungeon);

/** Could we implement the save game feature here? --Austin**/
		gameTutorial();
			
			while(!quitGame)
			{
				System.out.println(curGame.player.toString());
				movementCommand(curGame.dungeon, curGame.player);
				evaluateRoom(curGame.dungeon, heroX, heroY, curGame.player, quitGame);
			}
    }
}
	
	public static void gameTutorial() 
	{
System.out.println("\n");	//DEBUG: REMOVE ONCE MAIN MENU IS DONE
				
				System.out.println("|------------------Symbol Examples--------------------->");
				System.out.println("| Multiple Items = M      |  Empty room = E");
				System.out.println("| Entrance = I            |  Exit = O");
				System.out.println("| Monster = X             |  Pit = P");
				System.out.println("| Healing Potion = H      |  Vision Potion = V");
				System.out.println("|");
				System.out.println("|-----------------Pillars to be Found------------------->");
				System.out.println("| Pillar Abstraction = Y  |  Pillar Encapsulation = Y");
				System.out.println("| Pillar Inheritance = Y  |  Pillar Polymorphism = Y");
				System.out.println("| ");
				System.out.println("| ");
				System.out.println("|                  Example of Room");
				System.out.println("\t\t\t* - *\r\n" 
								+ "\t\t\t| P | \n\t\t\t* - *");
				System.out.println("________________________________________________________________");
	 }//end of GameTutorial
	
	public static void gameIntroduction()
	{
	/** Game introduction Text **/
	System.out.println("_____________________________________________________________" + "\n>WELCOME TO DUNGEON ADVENTURE!\n\n");
	System.out.println("Your quest to seek the four pillars of O.O. has brought you"
			         + "\nto the foulest, most dankest dungeon in all the land...\n  ");
	
	System.out.println("To escape the dungeon, you must brave the monsters, dodge the pits, "
					 + "\nand seek the four Pillars of O.O.\n");
	}
	
/*****dungeon Set-up methods*********************************************************************/

	public static void generatePortals(int[] entrance, int[] exit)
    {
        entrance[0] = 0; // Sets entrance row coordinate
        entrance[1] = (int)(Math.random() * 5); // sets entrance col coordinate
        exit[0] = 4; // Sets exit row coordinate
        exit[1] = (int)(Math.random() * 5); // Sets exit col coordinate
    }
	
    public static void generatePillars(int[] pillarCoords, int[] entrance, int [] exit)
    {
    	for(int index = 0; index < pillarCoords.length - 1; index++)
    	{
    		boolean ValidCoords = false;
    		int pillarX = 0, pillarY = 0;
    		while(!ValidCoords)
    		{
    		pillarX = (int)(Math.random() * 5);
    		pillarY = (int)(Math.random() * 5);
    		
    			if((entrance[0] != pillarX || entrance[1] != pillarY) && (exit[0] != pillarX || exit[1] != pillarY))
    			{	//It's not at the entrance or exit, but is it the same spot as a previous pillar?
    				if(!samePillarCoords(pillarX, pillarY, pillarCoords))
    				{
    					ValidCoords = true; 	//Valid coords to place in Pillar, becase there's no portals or Pillars :-)
    				}
    			}
    		}	//We now have valid coordinates
    		pillarCoords[index] = pillarX;
    		pillarCoords[index + 1] = pillarY;
    		index++;
    	}
    }
	
    public static boolean samePillarCoords( int pillarRow, int pillarCol, int[] pillarCoords)
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
	
	private static void generateROOMS(Dungeon dungeon, int[] pillarCoordinates, int[] entrance, int[] exit)
	{
		for(int xRows = 0; xRows < 5; xRows++)
		{
			for(int yColumns = 0; yColumns < 5; yColumns++)
			{
				if(xRows == entrance[0] && yColumns == entrance[1])
				{
				  //Then only place the entrance, nothing else!
					dungeon.dungeonRooms[xRows][yColumns] = new Room();
					dungeon.dungeonRooms[xRows][yColumns].setRoomCoords(xRows, yColumns);
					dungeon.dungeonRooms[xRows][yColumns].setRoomHasEntrance(true);
					dungeon.dungeonRooms[xRows][yColumns].setRoomHasANY_ITEM(true);

					heroX = xRows;
					heroY = yColumns;
					continue;
				}
				if(xRows == exit[0] && yColumns == exit[1])
				{
				  //Then only place the exit, nothing else!
					dungeon.dungeonRooms[xRows][yColumns] = new Room();
					dungeon.dungeonRooms[xRows][yColumns].setRoomCoords(xRows, yColumns);
					dungeon.dungeonRooms[xRows][yColumns].setRoomHasExit(true);
					dungeon.dungeonRooms[xRows][yColumns].setRoomHasANY_ITEM(true);
					continue;
				}
				else
				  //Valid spot to place GAME OBJECTS
					if(samePillarCoords(xRows, yColumns, pillarCoordinates))
					{
						//Valid pillarCoords, therefore place pillar here!
						dungeon.dungeonRooms[xRows][yColumns] = new Room();
						dungeon.dungeonRooms[xRows][yColumns].setRoomCoords(xRows, yColumns);
						dungeon.dungeonRooms[xRows][yColumns].placeRandomPillar();
						dungeon.dungeonRooms[xRows][yColumns].generateRoomContents();
						continue;
					}
					else
						//General case for placing items -- No pillars, no entrance, no exit. Wildcard. 
					dungeon.dungeonRooms[xRows][yColumns] = new Room();
					dungeon.dungeonRooms[xRows][yColumns].setRoomCoords(xRows, yColumns);
					dungeon.dungeonRooms[xRows][yColumns].generateRoomContents();
			}
		}
		
	}

	
	private static void displayROOMS(Dungeon dungeon) 
	{
		for(int xRows = 0; xRows < 5; xRows++)
		{
			for(int yColumns = 0; yColumns < 5; yColumns++)
			{
				System.out.println("Room " + "(" + xRows + ", " + yColumns + ")" 
						+ dungeon.dungeonRooms[xRows][yColumns].toString() + "\n");
			}
		}
		
	}
	
/** Player movement **/
	
	private static void movementCommand(Dungeon dungeon, Hero player)
	{
		String command;
		Room.printRoom(dungeon.dungeonRooms[heroX][heroY]);	//Displays the current room of the Hero
		System.out.print("(i = inventory, n = north, s = south, e = east, w = west)\nEnter a command:\n>>");
		command = Keyboard.readString();
		moveHero(player, command, heroX, heroY);
		
	}
	
	private static void moveHero(Hero player, String command, int heroX, int heroY)
	{
		//This will change the X/Y coords of the hero
		switch(command)
		{
			case "n":
				if(heroX == 0)
				{
					System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \n\tLet's try another direction!\n");
				}else 
				setHeroX(heroX - 1);
				return;
			case "e":
				if(heroY == 4)
				{
					System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \nLet's try another direction!\n");
				}else 
				setHeroY(heroY + 1);
				return;
			case "s": 
				if(heroX == 4)
				{
					System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \nLet's try another direction!\n");
				}else 
				setHeroX(heroX + 1);
				return;
			case "w": 
				if(heroY == 0)
				{
					System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \nLet's try another direction!\n");
				}else 
				setHeroY(heroY - 1);
				return;
			case "i":
				player.playerInventory(player);
				return;
		}
	}
	
	private static void setHeroX(int moves) { heroX = moves; }

	private static void setHeroY(int moves) { heroY = moves; }

	private static void evaluateRoom(Dungeon dungeon, int heroX2, int heroY2, Hero player, boolean quitGame) 
	{
		if(dungeon.dungeonRooms[heroX2][heroY2].roomHasHealthPOTION(true))
		{
			dungeon.dungeonRooms[heroX2][heroY2].setRoomHasHealthPOTION(false);
			player.healthPotions++;
			System.out.println("***Health potion found!***");
		}
		if(dungeon.dungeonRooms[heroX2][heroY2].roomHasVisionPOTION(true))
		{
			dungeon.dungeonRooms[heroX2][heroY2].setRoomHasVisionPOTION(false);
			player.visionPotions++;
			System.out.print("***Vision potion found!***\n");
		}
		if(dungeon.dungeonRooms[heroX2][heroY2].roomHasPILLAR_A())
		{
			dungeon.dungeonRooms[heroX2][heroY2].setRoomHasPILLAR_A(false);
			player.ooPillars++;
			System.out.print("***(" + player.ooPillars + "/4) Pillar of Abstraction found!***");

		}
		if(dungeon.dungeonRooms[heroX2][heroY2].roomHasPILLAR_E())
		{
			dungeon.dungeonRooms[heroX2][heroY2].setRoomHasPILLAR_E(false);
			player.ooPillars++;
			System.out.print("***(" + player.ooPillars + "/4) Pillar of Encapsulation found!***");
		}
		if(dungeon.dungeonRooms[heroX2][heroY2].roomHasPILLAR_I())
		{
			dungeon.dungeonRooms[heroX2][heroY2].setRoomHasPILLAR_I(false);
			player.ooPillars++;
			System.out.print("***(" + player.ooPillars + "/4) Pillar of Inheritance found!***");
		}
		if(dungeon.dungeonRooms[heroX2][heroY2].roomHasPILLAR_P())
		{
			dungeon.dungeonRooms[heroX2][heroY2].setRoomHasPILLAR_P(false);
			player.ooPillars++;
			System.out.print("***(" + player.ooPillars + "/4) Pillar of Polymorphism found!***");
		}
		if(dungeon.dungeonRooms[heroX2][heroY2].roomHasMONSTER(true))
		{
			System.out.println("\n\t        ***MONSTER!!!***\n");
			System.out.println("You have stumbled upon a monsters room, fight to the death!\n");
			Room.printRoom(dungeon.dungeonRooms[heroX][heroY]);	//Displays the current room of the Hero

			Monster theMonster;
			

			    theMonster = MonsterFactory.generateMonster();
				Dungeon.battle(player, theMonster);

			
			if(player.hitPoints <= 0)
			{
				displayDeathScreen();
			}
			
			dungeon.dungeonRooms[heroX2][heroY2].setRoomHasMonster(false);
				player.healthPotions++;
				System.out.println("Upon defeating " + theMonster.name + " a health potion falls to the floor. \n" + player.name + " adds it to the potion bag.");
		}
		if(dungeon.dungeonRooms[heroX2][heroY2].roomHasEXIT(true))
		{
			roomEntries++;
			if(roomEntries == 1) 
			{
				System.out.println("You enter a room through a door labeled \"Portal Room\" to discover\n"
						         + "a large obsidian structure -- a dead portal. It can restored by finding\n"
						         + "the Four Pillars of OO. This is your ticket home!\n\n");
			//Add check for existing pillars to be placed
			}
			if(roomEntries > 1 && player.ooPillars < 4)
			{
				System.out.println("You re-enter the portal room, but without the pillars you cannot use it.\n"
								 + "Tip: Did you try looking up yet?");
			}
			if(player.ooPillars == 4)
			{
				System.out.println("Woah, you found all the pillars! Good job, the portal has now been activated.\n"
								 + "  >>" + player.name + "steps into the purple glow of the obsidian portal, fading from the dungeon");
					//Game is over, need to figure out the right way to handle this
				System.out.print("\nYou beat the game! Want to play again(y/n)?\n>");
				char choice = Keyboard.readChar();
				if(choice == 'Y' || choice == 'y')
				{
					setQuitGame(true);
				} else
					System.exit(0);
			}
		}
		
		if(dungeon.dungeonRooms[heroX2][heroY2].roomHasPIT(true))
		{
			int pitDamage = (int)( Math.random() * 21);
			System.out.println("\nYou fell into a pit, taking " + pitDamage + " points of damage!");
			player.hitPoints -= pitDamage;
		}

	}


	private static void displayDeathScreen() 
	{
		System.out.println("Dang dude, you suck");
		System.exit(0);
	}
	
	public static void setQuitGame(boolean quitGame) {
		DungeonAdventure.quitGame = quitGame;
	}

	
}//end of CLASS
